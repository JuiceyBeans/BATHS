package wars.api;

import java.io.Serializable;

/**
 * Encounter class represents a single encounter in the game.
 * Each encounter has a unique id, a type, a location, a required skill level,
 * and the prize money awarded for a successful engagement.
 * 
 * Example encounter line format (from file): "1,BATTLE,Trafalgar,3,300"
 * 
 */
public class Encounter implements Serializable {

    private int id;
    private EncounterType type;
    private String location;
    private int requiredSkill;
    private int prizeMoney;

    /**
     * Constructs a new Encounter with the specified details.
     *
     * @param id the unique identifier for the encounter
     * @param type the type of encounter (BLOCKADE, BATTLE, SKIRMISH)
     * @param location the location where the encounter occurs
     * @param requiredSkill the skill level required to win the encounter
     * @param prizeMoney the prize money awarded for winning the encounter
     */
    public Encounter(int id, EncounterType type, String location, int requiredSkill, int prizeMoney) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.requiredSkill = requiredSkill;
        this.prizeMoney = prizeMoney;
    }

    /**
     * Returns the encounter's unique identifier.
     * @return the encounter id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the encounter's unique identifier.
     * @param id the encounter id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the type of encounter.
     * @return the encounter type
     */
    public EncounterType getType() {
        return type;
    }

    /**
     * Sets the encounter type.
     * @param type the encounter type
     */
    public void setType(EncounterType type) {
        this.type = type;
    }

    /**
     * Returns the location of the encounter.
     * @return the encounter location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the encounter.
     * @param location the encounter location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the required skill level to win the encounter.
     * @return the required skill level
     */
    public int getRequiredSkill() {
        return requiredSkill;
    }

    /**
     * Sets the required skill level for the encounter.
     * @param requiredSkill the required skill level
     */
    public void setRequiredSkill(int requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    /**
     * Returns the prize money awarded for winning the encounter.
     * @return the prize money
     */
    public int getPrizeMoney() {
        return prizeMoney;
    }

    /**
     * Sets the prize money for the encounter.
     * @param prizeMoney the prize money
     */
    public void setPrizeMoney(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    /**
     * Returns a string representation of the encounter.
     * @return a string detailing the encounter
     */
    @Override
    public String toString() {
        return "Encounter [ID: " + id + ", Type: " + type + ", Location: " + location
                + ", Required battle skill: " + requiredSkill + ", Prize money: " + prizeMoney + "]";
    }
}
