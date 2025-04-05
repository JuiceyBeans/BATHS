package wars.ships;

import wars.api.Ship;
/**
 * Sloops can have a doctor or a pinnace
 * All s    loops have a battle skill of 5
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class Sloop extends Ship {
    private boolean hasDoctor = false;
    private boolean hasPinnace = false;

    public Sloop(String name, int commissionFee, boolean hasDoctor, boolean hasPinnace) {
        super(name, commissionFee, 5);
        this.hasDoctor = hasDoctor;
        this.hasPinnace = hasPinnace;
    }

    public Sloop(String name, int commissionFee, boolean hasPinnace) {
        super(name, commissionFee, 5);
        this.hasPinnace = hasPinnace;
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
    public boolean hasDoctor() {
        return this.hasDoctor;
    }

    @Override
    public boolean hasPinnace() {
        return this.hasPinnace;
    }
}
