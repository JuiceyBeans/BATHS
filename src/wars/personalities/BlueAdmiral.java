package wars.personalities;

import wars.api.Encounter;
import wars.api.Ship;
import wars.api.ShipState;
import wars.api.WarChest;

import java.util.ArrayList;

public class BlueAdmiral {
    private String name;
    private WarChest warChest;  
    private ArrayList<Ship> squadron;
    private ArrayList<Ship> reserveFleet;

    public BlueAdmiral(String name, ArrayList<Ship> reserveFleet) {
        this.name = name;
        this.reserveFleet = reserveFleet;
        this.squadron = new ArrayList<>();
        this.warChest = new WarChest(1000);  // Initialize WarChest with the starting balance
    }

    public String getName() {
        return name;
    }

    public int getwarChestBalance() {
        return warChest.getBalance();  // Get the balance from WarChest
    }

    public ArrayList<Ship> getSquadron() {
        return squadron;
    }

    public ArrayList<Ship> getReserveFleet() {
        return reserveFleet;
    }

    // Add funds to the WarChest
    public void addToWarChest(int amount) {
        warChest.add(amount);
    }

    // Subtract funds from the WarChest
    public void subtractFromWarChest(int amount) {
        warChest.deduct(amount);
    }

    public boolean commissionShip(String shipName) {
        // Commissions a ship from reserve into squadron.
        for (Ship ship : reserveFleet) {
            if (ship.getName().equalsIgnoreCase(shipName) && ship.getState() == ShipState.RESERVE) {
                int cost = ship.getCommissionFee();
                if (warChest.getBalance() >= cost) {  // Check if there are enough funds in the WarChest
                    warChest.deduct(cost);  // Deduct the cost from the WarChest
                    ship.setState(ShipState.ACTIVE);
                    squadron.add(ship);
                    reserveFleet.remove(ship);
                    return true;
                }
                return false; // Not enough money
            }
        }
        return false; // Ship not found or not in reserve
    }

    public boolean decommissionShip(String shipName) {
        // Decommissions a ship from squadron back to reserve.
        for (Ship ship : squadron) {
            if (ship.getName().equalsIgnoreCase(shipName) && ship.getState() != ShipState.SUNK) {
                int refund = ship.getCommissionFee() / 2;  // Refund is half the cost
                warChest.add(refund);  // Add the refund to the WarChest
                ship.setState(ShipState.RESERVE);
                reserveFleet.add(ship);
                squadron.remove(ship);
                return true;
            }
        }
        return false; // Not found or sunk
    }

    public boolean restoreShip(String shipName) {
        // Restores a resting ship back to active.
        for (Ship ship : squadron) {
            if (ship.getName().equalsIgnoreCase(shipName) && ship.getState() == ShipState.RESTING) {
                ship.setState(ShipState.ACTIVE);
                return true;
            }
        }
        return false;
    }

    public Ship findSuitableShip(Encounter encounter) {
        // Finds a suitable ship for the encounter.
        for (Ship ship : squadron) {
            if (ship.getState() == ShipState.ACTIVE && ship.canFight(encounter.getType())) {
                return ship;
            }
        }
        return null; // No suitable ship found
    }

    public boolean isFired() {
        // Checks if the admiral has been fired.
        if (squadron.isEmpty()) return true; // No ships in squadron

        if (warChest.getBalance() >= 0) return false;  // Check if the war chest is still positive

        for (Ship ship : squadron) {
            if (ship.getState() != ShipState.SUNK) {
                return false;  // Still has decommissionable ships
            }
        }
        return true;  // No money and no ships left = lose job
    }
}
