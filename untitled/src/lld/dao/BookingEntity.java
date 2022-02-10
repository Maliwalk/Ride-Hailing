package lld.dao;

import lld.models.*;

import java.util.List;

public interface BookingEntity {

    public Long storeRider(Rider rider);
    public Long storeDriver(Driver rider,Vehicle vehicle);
    public BookingResponse addBooking(Booking booking);
    public void updateBooking(Long booking, Status status);
    public void updateVehicle(Long bookingId);
    public Double completeRide(Long bookingId);
//    public Long findVehicle(Booking booking);
    public void storeVehicle(Vehicle rider);
    public List getBookings(Long userId,UserType userType);
    public void updateVehicleLocation(String vehicleNumber,Double lat, Double longitude);


    void addOrUpdateNewVehicle(Long driverId, Vehicle vehicle);
}
