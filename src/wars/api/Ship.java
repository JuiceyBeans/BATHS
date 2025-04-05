package wars.api;

/**
 * Ship class. Idk fill this out later
 * @author Karthi (Ayush) Suresh
 * @version 27/03/2025
 */
public class Ship {

    // todo uncomment all lines related to captain once implemented

    private String name;
    //    private BlueAdmiral captain;
    private int commissionFee;
    private final int battleSkill;
    private ShipState state;

    public Ship(String name, /*BlueAdmiral captain,*/ int commissionFee, int battleSkill) {
        this.name = name;
//        this.captain = captain;
        this.commissionFee = commissionFee;

        if (battleSkill < 0 || battleSkill > 10) {
            throw new IllegalArgumentException("Battle skill must be between 0 and 10");
        }

        this.battleSkill = battleSkill;
        this.state = ShipState.RESERVE;
    }

    // Fight checks

    /**
     * Whether the ship can fight blockade encounters
     * @return default: false
     */
    public boolean canBlockade() {
        return false;
    }

    /**
     * Whether the ship can fight battle encounters
     * @return default: false
     */
    public boolean canBattle() {
        return false;
    }

    /**
     * Whether the ship can fight skirmish encounters
     * @return default: false
     */
    public boolean canSkirmish() {
        return false;
    }

    // Getters and Setters
    /**
     * @return Name of the ship
     */
    public String getName() {
        return name;
    }

    /**
     * Change name of the ship
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

//    public BlueAdmiral getCaptain() {
//        return captain;
//    }

//    public void setCaptain(BlueAdmiral captain) {
//        this.captain = captain;
//    }

    /**
     * @return Name of the ship
     */
    public int getCommissionFee() {
        return commissionFee;
    }

    /**
     * @return Battle skill of the ship
     */
    public int getBattleSkill() {
        return battleSkill;
    }

    /**
     * @return State of the ship (reserve, acting, resting, sunk)
     */
    public ShipState getState() {
        return state;
    }

    /**
     * Set state of the ship
     * @param state State of the ship (reserve, acting, resting, sunk)
     */
    public void setState(ShipState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ship details:" +
                "name='" + name + '\'' +
//                "captain='" + BlueAdmiral + '\' +
                ", commissionFee=" + commissionFee +
                ", battleSkill=" + battleSkill +
                ", state=" + state +
                '}';
    }
}
