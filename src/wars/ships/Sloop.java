package wars.ships;

import wars.api.Ship;
/**
 * Sloops can have a doctor or a pinnace
 * All sloops have a battle skill of 5
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class Sloop extends Ship {
    private boolean hasDoctor = false;
    private boolean hasPinnace = false;

    public Sloop(String name, String captain, int commissionFee, boolean hasDoctor, boolean hasPinnace) {
        super(name, captain, commissionFee, 5);
        this.hasDoctor = hasDoctor;
        this.hasPinnace = hasPinnace;
    }

    public Sloop(String name, String captain, int commissionFee, boolean hasPinnace) {
        super(name, captain, commissionFee, 5);
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
        return false;
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

        sb.append(this.getName()).append(" (Ship Type: Sloop, State: ").append(this.getState());
        sb.append(", Captain: ").append(this.getCaptain());
        sb.append(", Commission Fee: ").append(this.getCommissionFee());
        sb.append(", Battle Skill: ").append(this.getBattleSkill());
        sb.append(", Has Pinnace/Doctor: ").append(this.hasPinnaceOrDoctor()).append(")\n");

        return sb.toString();
    }
}
