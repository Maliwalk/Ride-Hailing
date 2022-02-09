package lld.controller;

import lld.dao.BookingEntity;
import lld.models.*;

import java.util.List;


public class RideHailingImpl implements RideHailing {
    private BookingEntity bookingEntity;

    public RideHailingImpl(BookingEntity bookingEntity) {
        this.bookingEntity=bookingEntity;
    }

    @Override
    public Long registerRider(Rider rider) {
        return bookingEntity.storeRider(rider);
    }

    @Override
    public Long registerDriver(Driver driver,Vehicle vehicle) {
        return bookingEntity.storeDriver(driver,vehicle);
    }

    @Override
    public Long bookRide(Booking booking) {
        booking.setDistance(Math.sqrt(Math.pow((booking.getEndLat()-booking.getStartLat()),2)+Math.pow((booking.getEndLong()-booking.getStartLong()),2)));
        Long bookingId= bookingEntity.addBooking(booking);
        return bookingId;

    }

    @Override
    public void startRide(Long bookingId) {

         bookingEntity.updateBooking(bookingId, Status.ONGOING);
    }

    @Override
    public void completeRide(Long bookingId) {

        bookingEntity.updateBooking(bookingId, Status.COMPLETED);
        bookingEntity.updateVehicle(bookingId);

    }

    @Override
    public Long registerVehicke(Vehicle rider) {
        return null;
    }

    @Override
    public List showRideHistoryForRider(Long riderId) {
        return bookingEntity.getBookings(riderId,UserType.RIDER);
    }

    @Override
    public List showRideHistoryForDriver(Long driverId) {
        return bookingEntity.getBookings(driverId,UserType.DRIVER);
    }
}
