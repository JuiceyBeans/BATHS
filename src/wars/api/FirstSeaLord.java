package wars.personalities;

import java.util.ArrayList;

/**
 * Represents the First Sea Lord, responsible for creating and managing upcoming sea encounters.
 */
public class FirstSeaLord {
    private ArrayList<Encounter> encounters;
    private int encounterCounter = 1;

    public FirstSeaLord() {
        this.encounters = new ArrayList<>();
    }

    /**
     * Returns the list of upcoming encounters.
     */
    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }

    /**
     * Creates a new encounter and adds it to the list.
     * @param type the type of encounter (e.g. BATTLE, BLOCKADE)
     * @param location where it will take place
     * @param requiredSkill minimum skill needed to participate
     * @param prizeMoney reward if the encounter is won
     * @return the created Encounter
     */
    public Encounter createEncounter(EncounterType type, String location, int requiredSkill, int prizeMoney) {
        Encounter encounter = new Encounter(encounterCounter++, type, location, requiredSkill, prizeMoney);
        encounters.add(encounter);
        return encounter;
    }

    /**
     * Retrieves an encounter by its unique ID.
     * @param id the encounter ID
     * @return the matching Encounter or null if not found
     */
    public Encounter getEncounterById(int id) {
        for (Encounter e : encounters) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * Removes an encounter once it is completed or resolved.
     * @param encounter the encounter to remove
     * @return true if successfully removed
     */
    public boolean removeEncounter(Encounter encounter) {
        return encounters.remove(encounter);
    }

    /**
     * Returns the IDs of all upcoming encounters.
     * @return list of encounter IDs
     */
    public ArrayList<Integer> getEncounterIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Encounter e : encounters) {
            ids.add(e.getId());
        }
        return ids;
    }
}