package lld.controller;

import lld.dao.BookingEntity;
import lld.models.Booking;
import lld.models.Driver;
import lld.models.Rider;
import lld.models.Vehicle;

import java.awt.print.Book;
import java.util.List;

public interface RideHailing {


    public Long registerRider(Rider rider);
    public Long registerDriver(Driver rider,Vehicle vehicle);
    public Long bookRide(Booking booking);
    public void startRide(Long bookingId);
    public void completeRide(Long bookingId);
    public Long registerVehicke(Vehicle rider);
    public List showRideHistoryForRider(Long riderId);
    public List showRideHistoryForDriver(Long driverId);


}
