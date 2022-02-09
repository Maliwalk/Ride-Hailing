package lld.dao;

import lld.models.*;

import java.util.List;

public interface BookingEntity {

    public Long storeRider(Rider rider);
    public Long storeDriver(Driver rider,Vehicle vehicle);
    public Long addBooking(Booking booking);
    public void updateBooking(Long booking, Status status);
    public void updateVehicle(Long bookingId);
//    public Long findVehicle(Booking booking);
    public void storeVehicle(Vehicle rider);
    public List getBookings(Long userId,String userType);



}
