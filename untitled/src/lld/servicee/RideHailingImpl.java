package lld.servicee;

import lld.dao.BookingEntity;
import lld.helper.UserHelper;
import lld.models.*;

import java.util.List;


public class RideHailingImpl implements RideHailing {
    private BookingEntity bookingEntity;
//    private UserHelper userHelper = new UserHelper();

    public RideHailingImpl(BookingEntity bookingEntity) {
        this.bookingEntity=bookingEntity;
    }

    @Override
    public Long registerRider(Rider rider) {
        UserHelper.validateUser(rider);
        return bookingEntity.storeRider(rider);
    }

    @Override
    public Long registerDriver(Driver driver,Vehicle vehicle)  {
        UserHelper.validateUser(driver);
        return bookingEntity.storeDriver(driver, vehicle);
    }

    @Override
    public BookingResponse bookRide(Booking booking) {
        booking.setDistance(Math.sqrt(Math.pow((booking.getEndLat()-booking.getStartLat()),2)+Math.pow((booking.getEndLong()-booking.getStartLong()),2)));
        BookingResponse bookingRes= bookingEntity.addBooking(booking);
        return bookingRes;

    }

    @Override
    public void startRide(Long bookingId) {

         bookingEntity.updateBooking(bookingId, Status.ONGOING);
    }

    @Override
    public Double completeRide(Long bookingId) {
        Double cost = bookingEntity.completeRide(bookingId);
        bookingEntity.updateBooking(bookingId, Status.COMPLETED);
        bookingEntity.updateVehicle(bookingId);
        return cost;

    }

    @Override
    public void updateAnotherVehicle(Long driverId, Vehicle vehicle) {
        bookingEntity.addOrUpdateNewVehicle(driverId,vehicle);
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
    public void updateVehicleLocation(String vehicleNumber,Double latitude, Double longitude) {
    bookingEntity.updateVehicleLocation(vehicleNumber,latitude,longitude);

    }

    @Override
    public List showRideHistoryForDriver(Long driverId) {
        return bookingEntity.getBookings(driverId,UserType.DRIVER);
    }
}
