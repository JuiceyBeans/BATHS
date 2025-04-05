package wars;

import java.util.ArrayList;
import java.util.Iterator;

public class QuartermasterGeneral {
    private ArrayList<Ship> reserveFleet;

    public QuartermasterGeneral(ArrayList<Ship> reserveFleet) {
        this.reserveFleet = reserveFleet;
    }

    public ArrayList<Ship> getReserveFleet() {
        return reserveFleet;
    }

    // Adds a new ship to the reserve fleet.
        public void acquireShip(Ship ship) {
        if (ship != null && ship.getStatus() == ShipState.RESERVE) {
            reserveFleet.add(ship);
        }
    }

    // Removes a ship from the reserve fleet.
        public void disposeUnusableShips() {
        Iterator<Ship> iterator = reserveFleet.iterator();
        while (iterator.hasNext()) {
            Ship ship = iterator.next(); 
            if (ship.getStatus() == ShipState.SUNK) {
                iterator.remove();
            }
        }
    }

    // Sells a captured enemy ship and adds value to the war chest.
        public int sellCapturedShip(Ship ship) {
        if (ship != null && ship.getStatus() == ShipState.RESERVE) {
            reserveFleet.remove(ship);
            return ship.getCost(); // Full value to be added externally to war chest
        }
        return 0;
    }

    // Repairs or restocks a ship and returns the cost to be deducted.
        public int serviceShip(Ship ship) {
            if (ship != null) {
                // Calculate 10% of the ship's cost
                return ship.getCost() / 10;
            } 
            else {
                return 0;
            }
            
}