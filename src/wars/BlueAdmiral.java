// package wars;

// import java.util.ArrayList;

// import wars.api.ShipState;

// public class BlueAdmiral {
//     private String name;
//     private int warChest;
//     private ArrayList<Ship> squadron;
//     private ArrayList<Ship> reserveFleet;

//     public BlueAdmiral(String name, ArrayList<Ship> reserveFleet) {
//         this.name = name;
//         this.reserveFleet = reserveFleet;
//         this.squadron = new ArrayList<>();
//         this.warChest = 1000; // Initial starting money
//     }

//     // Getters and Setters
//     public String getName() {
//         return name;
//     }

//     public int getWarChest() {
//         return warChest;
//     }

//     public ArrayList<Ship> getSquadron() {
//         return squadron;
//     }

//     public ArrayList<Ship> getReserveFleet() {
//         return reserveFleet;
//     }

//     public void addToWarChest(int amount) {
//         this.warChest += amount;
//     }

//     public void subtractFromWarChest(int amount) {
//         this.warChest -= amount;
//     }

    
    
//         public boolean commissionShip(String shipName) {
//             // Commissions a ship from reserve into squadron.
//         for (Ship ship : reserveFleet) {
//             if (ship.getName().equalsIgnoreCase(shipName)
//                 && ship.getStatus() == ShipState.RESERVE) {

//                 int cost = ship.getCost();
//                 if (warChest >= cost) {
//                     warChest -= cost;
//                     ship.setStatus(ShipState.ACTIVE);
//                     squadron.add(ship);
//                     reserveFleet.remove(ship);
//                     return true;
//                 }
//                 return false; // Not enough money
//             }
//         }
//         return false; // Ship not found or not in reserve
//     }

    
     
//     public boolean decommissionShip(String shipName) {
//         // Decommissions a ship from squadron back to reserve.
//         for (Ship ship : squadron) {
//             if (ship.getName().equalsIgnoreCase(shipName)
//                 && ship.getStatus() != ShipState.SUNK) {

//                 int refund = ship.getCost() / 2;
//                 warChest += refund;
//                 ship.setStatus(ShipState.RESERVE);
//                 reserveFleet.add(ship);
//                 squadron.remove(ship);
//                 return true;
//             }
//         }
//         return false; // Not found or sunk
//     }

    
     
//     public boolean restoreShip(String shipName) {
//         // Restores a resting ship back to active.
//         for (Ship ship : squadron) {
//             if (ship.getName().equalsIgnoreCase(shipName)
//                 && ship.getStatus() == ShipState.RESTING) {
//                 ship.setStatus(ShipStatus.ACTIVE);
//                 return true;
//             }
//         }
//         return false;
//     }

    
    
//     public Ship findSuitableShip(Encounter encounter) {
//         // Finds a suitable ship for the encounter.
//         for (Ship ship : squadron) {
//             if (ship.getStatus() == ShipState.ACTIVE
//                 && ship.canFight(encounter.getType())) {
//                 return ship;
//             }
//         }
//         return null; // No suitable ship found
//     }

    
     
//     public boolean isFired() {
//         // Checks if the admiral has been fired.
//         if (squadron.isEmpty()) return true; // No ships in squadron

//         if (warChest >= 0) return false;

//         for (Ship ship : squadron) {
//             if (ship.getStatus() != ShipState.SUNK) {
//                 return false; // Still has decommissionable ships
//             }
//         }
//         return true; // No money and no ships left = lose job
//     }
// }