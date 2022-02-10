package lld.models;

public class Driver extends User{
    String vehicleId;

    @Override
    public String toString() {
        return "Driver{" +
                "vehicleId='" + vehicleId + '\'' +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", bookings=" + bookings +
                '}';
    }

    public String getVehicle() {
        return vehicleId;
    }

    public Driver setVehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }
}
