package wars.ships;

import wars.api.Ship;
/**
 * Man o wars have multiple decks and can hold a number of marines, unlike other ships
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class ManOWar extends Ship {
    private int marines;
    private int decks;

    public ManOWar(String name, String captain, int battleSkill, int marines, int decks) {
        super(name, captain, decks <= 2 ? 300 : 500, battleSkill);

        this.marines = marines;
    }

    /**
     * @return Number of marines on the ship
     */
    public int getMarines() {
        return this.marines;
    }

    /**
     * Set number of marines on the ship
     * @param marines Number of marines
     */
    public void setMarines(int marines) {
        this.marines = marines;
    }

    /**
     * @return Number of decks on the ship
     */
    public int getDecks() {
        return this.decks;
    }

    /**
     * Set number of decks on the ship
     * @param decks Number of decks
     */
    public void setDecks(int decks) {
        this.decks = decks;
    }

    @Override
    public boolean canBlockade() {
        return true;
    }

    @Override
    public boolean canBattle() {
        return true;
    }

    @Override
    public boolean canSkirmish() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getName()).append(" (Ship Type: ManOWar, State: ").append(this.getState());
        sb.append(", Captain: ").append(this.getCaptain());
        sb.append(", Commission Fee: ").append(this.getCommissionFee());
        sb.append(", Battle Skill: ").append(this.getBattleSkill());
        sb.append(", Marines: ").append(this.getMarines()).append(")\n");

        return sb.toString();
    }
}
