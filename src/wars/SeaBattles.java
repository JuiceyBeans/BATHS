package wars;

import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS 
{
    // may have one HashMap and select on stat

    private String admiral;
    private double warChest;


//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param adm the name of the admiral
     */  
    public SeaBattles(String adm)
    {
      
        
       setupShips();
       setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admir the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
      
        
       setupShips();
       // setupEncounters();
       // uncomment for testing Task 
       // readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        
        return "null";
    }
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @return true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        return false;
    }
    
    /** returns the amount of money in the War Chest
     * @return the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return 0;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
       
        return "No ships";
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
   
        
        return "No ships";
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
       
        return "No ships";
    }
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
  
        
        return "No ships";
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
 
        
        
        return "\nNo such ship";
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be commissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the reserve fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        
        return "- Ship not found";
    }
        
    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        return false;
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
        return false;
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
  
        
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @return true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         return false;
     }
     
     
/** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter.The results of fighting an encounter will be 
      * one of the following: 
      * 0-Encounter won by...(ship reference and name)-add prize money to War 
      * Chest and set ship's state to RESTING,  
      * 1-Encounter lost as no ship available - deduct prize from the War Chest,
      * 2-Encounter lost on battle skill and (ship name) sunk" - deduct prize 
      * from War Chest and set ship state to SUNK.
      * If an encounter is lost and admiral is completely defeated because there 
      * are no ships to decommission,add "You have been defeated " to message, 
      * -1 No such encounter
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
       
            
        return "Not done";
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        
        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
 
        return "No encounters";
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
   
        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/
    
     // In SeaBattles class, add these fields at the class level:
    private Fleet fleet;                   // to store all ships
    private List<Encounter> encounters;    // to store all encounters

    private void setupShips() {
    // Instantiate the fleet
    fleet = new Fleet();

    // Create sample ships and add them to the fleet.
    // Note: Adjust the parameters as appropriate for your design.
    
    // Man-O-War: "Victory" with battleSkill=3, marines=30, decks=3, cannons=4 
    ManOWar victory = new ManOWar("Victory", 3, 30, 3, 4);
    victory.setMarines(30);
    victory.setDecks(3);
    fleet.addShip(victory);

    // Frigate: "Sophie" with 16 cannons, battleSkill=8, hasPinnace=true
    Frigate sophie = new Frigate("Sophie", 16, 8, true);
    fleet.addShip(sophie);

    // Man-O-War: "Endeavour" with battleSkill=4, marines=20, decks=2, cannons=2
    ManOWar endeavour = new ManOWar("Endeavour", 4, 20, 2, 2);
    endeavour.setMarines(20);
    endeavour.setDecks(2);
    fleet.addShip(endeavour);

    // Sloop: "Arrow" with commissionFee=150, battleSkill=5 (sloops always have skill 5), hasDoctor=false, hasPinnace=true
    Sloop arrow = new Sloop("Arrow", 150, false, true);
    fleet.addShip(arrow);

    // Man-O-War: "Belerophon" with battleSkill=8, marines=50, decks=3, cannons=4
    ManOWar belerophon = new ManOWar("Belerophon", 8, 50, 3, 4);
    belerophon.setMarines(50);
    belerophon.setDecks(3);
    fleet.addShip(belerophon);

    // Frigate: "Surprise" with 10 cannons, battleSkill=6, hasPinnace=false
    Frigate surprise = new Frigate("Surprise", 10, 6, false);
    fleet.addShip(surprise);

    // Frigate: "Jupiter" with 20 cannons, battleSkill=7, hasPinnace=false
    Frigate jupiter = new Frigate("Jupiter", 20, 7, false);
    fleet.addShip(jupiter);

    // Sloop: "Paris" with commissionFee=200, battleSkill=5, hasPinnace=true (using constructor without hasDoctor)
    Sloop paris = new Sloop("Paris", 200, true);
    fleet.addShip(paris);

    // Sloop: "Beast" with commissionFee=400, battleSkill=5, hasDoctor=false, hasPinnace=false
    Sloop beast = new Sloop("Beast", 400, false, false);
    fleet.addShip(beast);

    // Sloop: "Athena" with commissionFee=100, battleSkill=5, hasDoctor=true, hasPinnace=true
    Sloop athena = new Sloop("Athena", 100, true, true);
    fleet.addShip(athena);
}

     
    private void setupEncounters() {
    // Instantiate the encounters list
    encounters = new ArrayList<>();

    // Add sample encounters:
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
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param filename name of the file to be read
     */
    private List<Encounter> encounters = new ArrayList<>();
    
    // Reads encounter data from a text file and populates the encounters list.
    // Assumes each line is formatted as: id,type,location,requiredSkill,prizeMoney
    public void readEncounters(String filename) {
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
}

    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    // Saves the entire game state (this SeaBattles object) to a file using serialization.
    public void saveGame(String fname) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
        out.writeObject(this); // Save the entire object graph
        System.out.println("Game saved successfully to " + fname);
    } catch (IOException e) {
        System.err.println("Error saving game: " + e.getMessage());
    }
}

    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
     // Loads the game state from a file using serialization and returns the SeaBattles object.
     public SeaBattles loadGame(String fname) {
     try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
        SeaBattles loadedGame = (SeaBattles) in.readObject();
        System.out.println("Game loaded successfully from " + fname);
        return loadedGame;
     } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading game: " + e.getMessage());
        return null;
     }
}



