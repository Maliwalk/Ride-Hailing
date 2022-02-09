package lld.models;

public class Driver extends User{
    Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Driver setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }
}
