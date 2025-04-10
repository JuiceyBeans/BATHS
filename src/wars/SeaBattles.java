package wars;

import wars.api.*;
import wars.personalities.BlueAdmiral;
import wars.ships.Frigate;
import wars.ships.ManOWar;
import wars.ships.Sloop;

import java.util.*;
import java.io.*;

import static java.lang.String.valueOf;

/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS {
    // may have one HashMap and select on stat

    private BlueAdmiral admiral;


//**************** BATHS ************************** 

    /**
     * Constructor requires the name of the admiral
     *
     * @param adm the name of the admiral
     */
    public SeaBattles(String adm) {
        admiral = new BlueAdmiral(adm, new Fleet());
        setupShips();
        setupEncounters();
    }

    /**
     * Constructor requires the name of the admiral and the
     * name of the file storing encounters
     *
     * @param admir    the name of the admiral
     * @param filename name of file storing encounters
     */
    public SeaBattles(String admir, String filename)  //Task 3
    {
        setupShips();
        setupEncounters();
        // uncomment for testing Task
        //readEncounters(filename);
    }


    /**
     * Returns a String representation of the state of the game,including the name of the
     * admiral, state of the warChest,whether defeated or not, and the ships currently in
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     *
     * @return a String representation of the state of the game,including the name of the
     * admiral, state of the warChest,whether defeated or not, and the ships currently in
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString() {
        StringBuilder details = new StringBuilder();

        String name = admiral.getName();
        double balance = getWarChest();
        String isDefeated;

        if (!isDefeated()) {
            isDefeated =  "Is OK";
        } else isDefeated = "Is Defeated";

        details.append("Name: ").append(name);
        details.append("\nWar Chest balance: ").append(balance);

        details.append("\nIs defeated?: ").append(isDefeated);
        details.append("\nShips in squadron: ").append(getSquadron());
        details.append("\nShips in reserve: ").append(getReserveFleet());

        return details.toString();
    }


    /**
     * returns true if War Chest <=0 and the admiral's squadron has no ships which
     * can be retired.
     *
     * @return true if War Chest <=0 and the admiral's fleet has no ships
     * which can be retired.
     */
    public boolean isDefeated() {
        return admiral.isFired();
    }

    /**
     * returns the amount of money in the War Chest
     *
     * @return the amount of money in the War Chest
     */
    public double getWarChest() {
        return admiral.getBalance();
    }


    /**
     * Returns a String representation of all ships in the reserve fleet
     *
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet() {
        return admiral.getReserveFleet().toString();
    }

    /**
     * Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     *
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron() {
        return admiral.getSquadron().toString();
    }

    /**
     * Returns a String representation of the ships sunk (or "no ships sunk yet")
     *
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips() {
        return admiral.getSunkShips().toString();
    }

    /**
     * Returns a String representation of the all ships in the game
     * including their status
     *
     * @return a String representation of the ships in the game
     **/
    public String getAllShips() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserve fleet details: ").append(admiral.getReserveFleet().toString());
        sb.append("\nSquadron details: ").append(admiral.getSquadron().toString());
        sb.append("\nSunk details: ").append(admiral.getSunkShips().toString());

        return sb.toString();
    }


    /**
     * Returns details of any ship with the given name
     *
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme) {
        Ship ship = admiral.getShip(nme);
        if (ship == null) return "\nNo such ship";

        return ship.toString();
    }

    // ***************** Fleet Ships ************************   

    /**
     * Allows a ship to be commissioned to the admiral's squadron, if there
     * is enough money in the War Chest for the commission fee.The ship's
     * state is set to "active"
     *
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if
     * ship not found, "Not available" if ship is not in the reserve fleet, "Not
     * enough money" if not enough money in the warChest
     **/
    public String commissionShip(String nme) {
        return admiral.commissionShip(nme);
    }

    /**
     * Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     *
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme) {
        return admiral.getSquadron().getShip(nme) != null;
    }

    /**
     * Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     *
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme) {
        if (isInSquadron(nme)) {
            return admiral.decommissionShip(nme);
        } else return false;
    }


    /**
     * Restores a ship to the squadron by setting their state to ACTIVE
     *
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref) {
        admiral.restoreShip(ref);
    }

//**********************Encounters************************* 

    /**
     * returns true if the number represents a encounter
     *
     * @param num is the reference number of the encounter
     * @return true if the reference number represents a encounter, else false
     **/
    public boolean isEncounter(int num) {
        for (Encounter encounter : encounters) {
            if (encounter.getId() == num)
                return true;
        }
        return false;
    }


    /**
     * Retrieves the encounter represented by the encounter
     * number.Finds a ship from the fleet which can fight the
     * encounter.The results of fighting an encounter will be
     * one of the following:
     * 0-Encounter won by...(ship reference and name)-add prize money to War
     * Chest and set ship's state to RESTING,
     * 1-Encounter lost as no ship available - deduct prize from the War Chest,
     * 2-Encounter lost on skill level and (ship name) sunk" - deduct prize
     * from War Chest and set ship state to SUNK.
     * If an encounter is lost and admiral is completely defeated because there
     * are no ships to decommission,add "You have been defeated " to message,
     * -1 No such encounter
     * Ensure that the state of the war chest is also included in the return message.
     *
     * @param encNo is the number of the encounter
     * @return a String showing the result of fighting the encounter
     */
    public String fightEncounter(int encNo) {
        Encounter encounter = null;

        for (Encounter e : encounters) {
            if (e.getId() == encNo)
                encounter = e;
        }

        if (encounter == null) {
            return "No such encounter";
        }

        Ship ship = admiral.findSuitableShip(encounter);

        if (ship == null) {
            admiral.subtractFromWarChest(encounter.getPrizeMoney());
            return "Encounter lost as no suitable ship available";
        }

        int battleSkill = ship.getBattleSkill();
        int reqSkill = encounter.getRequiredSkill();

        if (battleSkill >= reqSkill) {
            admiral.addToWarChest(encounter.getPrizeMoney());
            ship.setState(ShipState.RESTING);
            admiral.getSquadron().removeShip(ship);
            admiral.getReserveFleet().addShip(ship);
            return "Encounter won by " + ship.getName();
        } else {
            admiral.subtractFromWarChest(encounter.getPrizeMoney());
            ship.setState(ShipState.SUNK);
            admiral.getSquadron().removeShip(ship);
            admiral.getSunkShips().addShip(ship);

            if (admiral.getBalance() < 0) {
                return "Encounter is lost and you lose your job";
            }

            return "Encounter lost on skill level";
        }
    }

    /**
     * Provides a String representation of an encounter given by
     * the encounter number
     *
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by
     * the encounter number
     **/
    public String getEncounter(int num) {
        for (Encounter encounter : encounters) {
            if (encounter.getId() == num)
                return encounter.toString();
        }
        return "\nNo such encounter";
    }

    /**
     * Provides a String representation of all encounters
     *
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters() {
        StringBuilder allEncounters = new StringBuilder();
        for (Encounter encounter : encounters) {
            allEncounters.append(encounter).append("\n");
        }
        return allEncounters.toString();
    }


    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************


    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************

    /************************ Task 3 ************************************************/

    private Fleet fleet;
    private List<Encounter> encounters;

    private void setupShips() {
        fleet = admiral.getReserveFleet();

        // Man-O-War: "Victory" with captain="Alan Aikin", battleSkill=3, marines=30, decks=3, cannons=4
        ManOWar victory = new ManOWar("Victory", "Alan Aikin", 3, 30, 3);
        fleet.addShip(victory);

        // Frigate: "Sophie" with captain="Ben Baggins", 16 cannons, battleSkill=8, hasPinnace=true
        Frigate sophie = new Frigate("Sophie", "Ben Baggins", 16, 8, true);
        fleet.addShip(sophie);

        // Man-O-War: "Endeavour" with captain="Col Cannon", battleSkill=4, marines=20, decks=2, cannons=2
        ManOWar endeavour = new ManOWar("Endeavour", "Col Cannon", 4, 20, 2);
        fleet.addShip(endeavour);

        // Sloop: "Arrow" with captain="Dan Dare", commissionFee=150, battleSkill=5 (sloops always have skill 5), hasDoctor=false, hasPinnace=true
        Sloop arrow = new Sloop("Arrow", "Dan Dare", 150, false, true);
        fleet.addShip(arrow);

        // Man-O-War: "Belerophon" with captain="Ed Evans",battleSkill=8, marines=50, decks=3, cannons=4
        ManOWar belerophon = new ManOWar("Belerophon", "Ed Evans", 8, 50, 3);
        fleet.addShip(belerophon);

        // Frigate: "Surprise" with captain="Fred Fox", 10 cannons, battleSkill=6, hasPinnace=false
        Frigate surprise = new Frigate("Surprise", "Fred Fox", 10, 6, false);
        fleet.addShip(surprise);

        // Frigate: "Jupiter" with captain="Gil Gamage", 20 cannons, battleSkill=7, hasPinnace=false
        Frigate jupiter = new Frigate("Jupiter", "Gil Gamage", 20, 7, false);
        fleet.addShip(jupiter);

        // Sloop: "Paris" with captain="Hal Henry", commissionFee=200, battleSkill=5, hasPinnace=true (using constructor without hasDoctor)
        Sloop paris = new Sloop("Paris", "Hal Henry", 200, true);
        fleet.addShip(paris);

        // Sloop: "Beast" with captain="Ian Idle", commissionFee=400, battleSkill=5, hasDoctor=false, hasPinnace=false
        Sloop beast = new Sloop("Beast", "Ian Idle", 400, false, false);
        fleet.addShip(beast);

        // Sloop: "Athena" with captain="John Jones", commissionFee=100, battleSkill=5, hasDoctor=true, hasPinnace=true
        Sloop athena = new Sloop("Athena", "John Jones", 100, true, true);
        fleet.addShip(athena);
    }


    private void setupEncounters() {
        encounters = new ArrayList<>();

        // Encounter format: id, EncounterType, location, requiredSkill, prizeMoney
        encounters.add(new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
        encounters.add(new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
        encounters.add(new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
        encounters.add(new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200));
        encounters.add(new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90));
        encounters.add(new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
        encounters.add(new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
        encounters.add(new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
        encounters.add(new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
        encounters.add(new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
    }


    //******************************** Task 3.5 **********************************

    /**
     * reads data about encounters from a text file and stores in collection of
     * encounters.Data in the file is editable
     *
     * @param filename name of the file to be read
     */

    // Reads encounter data from a text file and populates the encounters list.
    // Assumes each line is formatted as: id,type,location,requiredSkill,prizeMoney
    /*public void readEncounters(String filename) {
        // Clear any existing encounters
        encounters.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip blank lines

                // Split the line by commas
                String[] tokens = line.split(",");
                if (tokens.length < 5) {
                    System.err.println("Invalid encounter line: " + line);
                    continue;
                }

                int id = Integer.parseInt(tokens[0].trim());
                // Convert the type string to an EncounterType (e.g., BLOCKADE, BATTLE, SKIRMISH)
                EncounterType type = EncounterType.valueOf(tokens[1].trim().toUpperCase());
                String location = tokens[2].trim();
                int requiredSkill = Integer.parseInt(tokens[3].trim());
                int prizeMoney = Integer.parseInt(tokens[4].trim());

                // Create a new Encounter object and add it to the list
                Encounter encounter = new Encounter(id, type, location, requiredSkill, prizeMoney);
                encounters.add(encounter);
            }
            System.out.println("Encounters loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading encounters: " + e.getMessage());
        }
    }*/

    // ***************   file write/read  *********************

     /**
     * Writes whole game to the specified file
     *
     * @param fname name of file storing requests
     */
    // Saves the entire game state to a file using serialization.
    /*public void saveGame(String fname) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            out.writeObject(this); // Save the entire object graph
            System.out.println("Game saved successfully to " + fname);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }*/

    /**
     * reads all information about the game from the specified file
     * and returns
     *
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    // Loads the game state from a file using serialization and returns the SeaBattles object.
    /*public SeaBattles loadGame(String fname) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            SeaBattles loadedGame = (SeaBattles) in.readObject();
            System.out.println("Game loaded successfully from " + fname);
            return loadedGame;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }*/
}



