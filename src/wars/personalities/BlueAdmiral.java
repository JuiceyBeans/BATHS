package wars.personalities;

import wars.api.*;

public class BlueAdmiral {
    private String name;
    private WarChest warChest;
    private Fleet squadron;
    private Fleet reserveFleet;
    private Fleet sunkShips;

    public BlueAdmiral(String name, Fleet reserveFleet) {
        this.name = name;
        this.reserveFleet = reserveFleet;
        this.squadron = new Fleet();
        this.sunkShips = new Fleet();
        this.warChest = new WarChest(1000);  // Initialize WarChest with the starting balance
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return warChest.getBalance();  // Get the balance from WarChest
    }

    public Fleet getSquadron() {
        return squadron;
    }

    public Fleet getReserveFleet() {
        return reserveFleet;
    }

    //todo implement adding ships to sunk if battle is lost
    public Fleet getSunkShips() {
        return sunkShips;
    }

    public Ship getShip(String name) {
        Ship ship;

        ship = getSquadron().getShip(name);
        if (ship != null) return ship;

        ship = getReserveFleet().getShip(name);
        if (ship != null) return ship;

        ship = getSunkShips().getShip(name);
        return ship;
    }

    // Add funds to the WarChest
    public void addToWarChest(int amount) {
        warChest.add(amount);
    }

    // Subtract funds from the WarChest
    public void subtractFromWarChest(int amount) {
        warChest.deduct(amount);
    }

    /**
     * Commissions a ship from reserve into squadron.
     * @param shipName Name of ship
     * @return Success/fail message + reason if fail
     */
    public String commissionShip(String shipName) {
        Ship ship = reserveFleet.getShip(shipName);

        if (ship == null) {
            return "Not found";
        }

        if (ship.getState() == ShipState.RESERVE) {
            int cost = ship.getCommissionFee();
            if (warChest.getBalance() >= cost) {  // Check if there are enough funds in the WarChest
                warChest.deduct(cost);  // Deduct the cost from the WarChest
                ship.setState(ShipState.ACTIVE);
                squadron.addShip(ship);
                reserveFleet.removeShip(ship);

                return "Ship commissioned";
            } else return "Not enough money";
        } else return "Not available";
    }


    /**
     * Decommissions a ship from squadron back to reserve.
     * @param shipName Name of ship
     * @return true if the ship with the name is in the admiral's squadron, false otherwise
     */
    public boolean decommissionShip(String shipName) {
        Ship ship = squadron.getShip(shipName);

        if (ship.getState() != ShipState.SUNK) {
            int refund = ship.getCommissionFee() / 2;  // Refund is half the cost
            warChest.add(refund);  // Add the refund to the WarChest
            ship.setState(ShipState.RESERVE);
            reserveFleet.addShip(ship);
            squadron.removeShip(ship);
            return true;
        }  else return false;
    }

    /**
     * Restores a resting ship back to active.
     *
     * @param shipName ship's name
     */
    public void restoreShip(String shipName) {
        Ship ship = squadron.getShip(shipName);

        if (ship == null) return;

        if (ship.getState() == ShipState.RESTING) {
            ship.setState(ShipState.ACTIVE);
        }
    }

    /**
     * Finds a suitable ship for the encounter.
     * @param encounter Encounter
     * @return A suitable ship to fight supplied encounter
     */
    public Ship findSuitableShip(Encounter encounter) {
        for (Ship ship : squadron.getShipsByState(ShipState.ACTIVE)) {
            if (ship.canFight(encounter.getType())) {
                return ship;
            }
        }
        return null; // No suitable ship found
    }

    public boolean isFired() {
        if (warChest.getBalance() >= 0) return false;  // Check if the war chest is still positive

        // Checks if the admiral has been fired.
        if (squadron.getShips().isEmpty()) return true; // No ships in squadron

        for (Ship ship : squadron.getShips()) {
            if (ship.getState() != ShipState.SUNK) {
                return false;  // Still has decommissionable ships
            }
        }
        return true;  // No money and no ships left = lose job
    }
}
