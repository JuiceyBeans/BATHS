package wars.ships;

import wars.api.Ship;
/**
 * Frigates can have cannons, unlike other ships. Frigates can also have a doctor or a pinnace
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class Frigate extends Ship {
    private boolean hasPinnace = false;

    public Frigate(String name, int cannons, int battleSkill, boolean hasPinnace) {
        super(name, cannons * 10, battleSkill);
        this.hasPinnace = hasPinnace;
    }

    public boolean hasPinnace() {
        return this.hasPinnace;
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
        return false;
    }
}
