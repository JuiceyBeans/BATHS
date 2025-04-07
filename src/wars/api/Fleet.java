package wars.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Fleet class represents a collection of ships.
 * @author Christopher
 * @version 05/04/2025
 */
public class Fleet {

    private List<Ship> ships;

    /**
     * Creates an empty Fleet.
     */
    public Fleet() {
        this.ships = new ArrayList<>();
    }

    /**
     * Add a ship to the fleet.
     
     */
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    /**
     * Remove a ship from the fleet.
     */
    public void removeShip(Ship ship) {
        this.ships.remove(ship);
    }

    public Ship getShip(String name) {
        for (Ship ship : this.getShips()) {
            if (ship.getName().equalsIgnoreCase(name)) {
                return ship;
            }
        }

        return null;
        //throw new IllegalArgumentException("Ship not found");
    }

    /**
     * @return List of all ships in the fleet.
     */
    public List<Ship> getShips() {
        return new ArrayList<>(this.ships);
    }

    /**
     * Get all ships in a specific state.
     * @return List of ships in that state
     */
    public List<Ship> getShipsByState(ShipState state) {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.getState() == state) {
                result.add(ship);
            }
        }
        return result;
    }

    /**
     * Calculate the total commission fee for the fleet.
     */
    public int getTotalCommissionFee() {
        int total = 0;
        for (Ship ship : ships) {
            total += ship.getCommissionFee();
        }
        return total;
    }

    /**
     * Find all ships that can participate in a battle.
     */
    public List<Ship> getBattleReadyShips() {
        List<Ship> result = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.canBattle()) {
                result.add(ship);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fleet contains ").append(ships.size()).append(" ship(s):\n");
        for (Ship ship : ships) {
            sb.append(ship.getName()).append(" (State: ").append(ship.getState()).append(")\n");
        }
        return sb.toString();
    }
}
