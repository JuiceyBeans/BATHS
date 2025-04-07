package wars.personalities;

import wars.api.Ship;
import wars.api.ShipState;

import java.util.ArrayList;
import java.util.Iterator;

public class Quartermaster {
    private ArrayList<Ship> reserveFleet;

    public Quartermaster(ArrayList<Ship> reserveFleet) {
        this.reserveFleet = reserveFleet;
    }

    public ArrayList<Ship> getReserveFleet() {
        return reserveFleet;
    }

    // Adds a new ship to the reserve fleet.
    public void acquireShip(Ship ship) {
        if (ship != null && ship.getState() == ShipState.RESERVE) {
            reserveFleet.add(ship);
        }
    }

    // Removes a ship from the reserve fleet.
    public void disposeUnusableShips() {
        Iterator<Ship> iterator = reserveFleet.iterator();
        while (iterator.hasNext()) {
            Ship ship = iterator.next();
            if (ship.getState() == ShipState.SUNK) {
                iterator.remove();
            }
        }
    }

    // Sells a captured enemy ship and adds value to the war chest.
    public int sellCapturedShip(Ship ship) {
        if (ship != null && ship.getState() == ShipState.RESERVE) {
            reserveFleet.remove(ship);
            return ship.getCommissionFee(); // Full value to be added externally to war chest
        }
        return 0;
    }

    // Repairs or restocks a ship and returns the cost to be deducted.
    public int serviceShip(Ship ship) {
        if (ship != null) {
            // Calculate 10% of the ship's cost
            return ship.getCommissionFee() / 10;
        } else {
            return 0;
        }

    }
}