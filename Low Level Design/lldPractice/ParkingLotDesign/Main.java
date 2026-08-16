package lldPractice.ParkingLotDesign;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Vehicle {

    private final String numberPlate;
    private final VehicleType vehicleType;

    protected Vehicle(String numberPlate, VehicleType vehicleType) {
        this.numberPlate = numberPlate;
        this.vehicleType = vehicleType;
    }

    // getters

    public String getNumberPlate() {
        return numberPlate;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

class Car extends Vehicle {

    public Car(String numberPlate) {
        super(numberPlate, VehicleType.CAR);
    }

}
class Motorcycle extends Vehicle{

    public Motorcycle(String numberPlate) {
        super(numberPlate, VehicleType.MOTORCYCLE);
    }

}
class Truck extends Vehicle{

    public Truck(String numberPlate) {
        super(numberPlate, VehicleType.TRUCK);
    }

}

class ParkingSpot{
    //spotId
    //spotType
    //spotStatus
    //vehicle

    private final String spotId;
    private final ParkingSpotType parkingSpotType;
    private SpotStatus spotStatus;
    private Vehicle vehicle;

    ParkingSpot(String spotId, ParkingSpotType parkingSpotType) {
            this.spotId = spotId;
            this.parkingSpotType = parkingSpotType;
            this.spotStatus = SpotStatus.AVAILABLE;
            this.vehicle = null;
    }

//    park(vehicle)
//unpark()
//reserve()
//isAvailable()

    public void park(Vehicle vehicle){

        if(this.spotStatus == SpotStatus.AVAILABLE){
            this.vehicle = vehicle;
            this.spotStatus = SpotStatus.OCCUPIED;
        }

    }

    public void unpark(){

        if(this.spotStatus == SpotStatus.OCCUPIED){
            this.vehicle = null;
            this.spotStatus = SpotStatus.RESERVED;
        }
    }

    public void reserve(Vehicle vehicle){

        if(this.spotStatus == SpotStatus.AVAILABLE){

            this.vehicle = null;
            spotStatus = SpotStatus.RESERVED;
        }
    }

    public boolean isAvailable() {
        return spotStatus == SpotStatus.AVAILABLE;
    }

    public String getSpotId() {
            return spotId;
    }
}

class ParkingFLoor{

    private final int floorNumber;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFLoor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new HashMap<>();
    }

    public void addSpot(ParkingSpot parkingSpot){

        if(!this.parkingSpots.containsKey(parkingSpot.getSpotId())){
            parkingSpots.put(parkingSpot.getSpotId(),parkingSpot);
        }
        else if(this.parkingSpots.get(parkingSpot.getSpotId()).equals(parkingSpot)){
            throw new RuntimeException("Parking Spot Already Exists");
        }
        else{
            throw new RuntimeException("You enter wrong details");
        }
    }

    public void removeSpot(String spotId){

        if(spotId.equals(this.parkingSpots.get(spotId).getSpotId())){
            this.parkingSpots.remove(spotId);
        }
        else{
            System.out.println("Parking Spot with id " + spotId + " not found");
        }

    }

    public ParkingSpot getSpot(String spotId){

        if(spotId.equals(this.parkingSpots.get(spotId).getSpotId())){
            return this.parkingSpots.get(spotId);
        }
        else{
            throw new RuntimeException("Parking Spot with id " + spotId + " not found");
        }
    }

}


public class Main {
}
