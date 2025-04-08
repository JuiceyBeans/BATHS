package wars.ships;

import wars.api.Ship;
/**
 * Frigates can have cannons, unlike other ships. Frigates can also have a doctor or a pinnace
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class Frigate extends Ship {
    private boolean hasPinnace = false;
    private boolean hasDoctor = false;

    public Frigate(String name, String captain, int cannons, int battleSkill, boolean hasDoctor, boolean hasPinnace) {
        super(name, captain, cannons * 10, battleSkill);
        this.hasDoctor = hasDoctor;
        this.hasPinnace = hasPinnace;
    }

    public Frigate(String name, String captain, int cannons, int battleSkill, boolean hasPinnace) {
        super(name, captain, cannons * 10, battleSkill);
        this.hasPinnace = hasPinnace;
    }

    public boolean hasDoctor() {
        return this.hasDoctor;
    }

    public boolean hasPinnace() {
        return this.hasPinnace;
    }

    public boolean hasPinnaceOrDoctor() {
        return hasDoctor || hasPinnace;
    }

    @Override
    public boolean canBlockade() {
        return hasPinnace();
    }

    @Override
    public boolean canBattle() {
        return true;
    }

    @Override
    public boolean canSkirmish() {
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getName()).append(" (Ship Type: Frigate, State: ").append(this.getState());
        sb.append(", Captain: ").append(this.getCaptain());
        sb.append(", Commission Fee: ").append(this.getCommissionFee());
        sb.append(", Battle Skill: ").append(this.getBattleSkill());
        sb.append(", Has Pinnace/Doctor: ").append(this.hasPinnaceOrDoctor()).append(")\n");

        return sb.toString();
    }
}
