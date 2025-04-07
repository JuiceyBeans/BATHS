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

    public int getBalance() {
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
        for (Ship ship : reserveFleet.getShipsByState(ShipState.RESERVE)) {
            if (ship.getName().equalsIgnoreCase(shipName)) {
                int cost = ship.getCommissionFee();
                if (warChest.getBalance() >= cost) {  // Check if there are enough funds in the WarChest
                    warChest.deduct(cost);  // Deduct the cost from the WarChest
                    ship.setState(ShipState.ACTIVE);
                    squadron.addShip(ship);
                    reserveFleet.removeShip(ship);

                    return "Successfully commissioned ship " + ship.getName();
                } else return "Not enough funds to commission ship " + ship.getName();
            }
        }

        return "Ship " + shipName + " not found";
    }

    public String decommissionShip(String shipName) {
        // Decommissions a ship from squadron back to reserve.
        for (Ship ship : squadron.getShips()) {
            if (ship.getName().equalsIgnoreCase(shipName)) {
                if (ship.getState() != ShipState.SUNK) {
                    int refund = ship.getCommissionFee() / 2;  // Refund is half the cost
                    warChest.add(refund);  // Add the refund to the WarChest
                    ship.setState(ShipState.RESERVE);
                    reserveFleet.addShip(ship);
                    squadron.removeShip(ship);
                    return "Successfully decommissioned ship " + ship.getName();
                }  else return "Ship " + ship.getName() + " is sunk and cannot be decommissioned";
            }
        }

        return "Ship " + shipName + " not found";
    }

    /**
     * Restores a resting ship back to active.
     * @param shipName ship's name
     * @return If ship is in a resting state, set it to active and return true
     */
    public String restoreShip(String shipName) {
        for (Ship ship : squadron.getShipsByState(ShipState.RESTING)) {
            if (ship.getName().equalsIgnoreCase(shipName)) {
                ship.setState(ShipState.ACTIVE);
                return "Ship " + ship.getName() + " successfully restored";
            }
        }
        return "Ship " + shipName + " not found";
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
        // Checks if the admiral has been fired.
        if (squadron.getShips().isEmpty()) return true; // No ships in squadron

        if (warChest.getBalance() >= 0) return false;  // Check if the war chest is still positive

        for (Ship ship : squadron.getShips()) {
            if (ship.getState() != ShipState.SUNK) {
                return false;  // Still has decommissionable ships
            }
        }
        return true;  // No money and no ships left = lose job
    }
}
