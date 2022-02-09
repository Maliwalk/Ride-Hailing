package lld;

import lld.controller.RideHailing;
import lld.controller.RideHailingImpl;
import lld.dao.BookingEntity;
import lld.dao.BookingEntityImpl;
import lld.models.*;

public class RideHailingMain {
    private static BookingEntity bookingEntity = new BookingEntityImpl();

    private static RideHailing rideHailing = new RideHailingImpl(bookingEntity);

    public static void main(String [] args){

        /*
        Register a Rider
         */
        Rider rider=new Rider();
        rider.setFirstName("ABC");
        rider.setLastName("DEF");
        rider.setPhoneNumber(9879106337L);
        rideHailing.registerRider(rider);

        /*
        Register a Driver
         */
        Driver driver=new Driver();
        driver.setFirstName("XYZ");
        driver.setPhoneNumber(1234567890L);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("GJ123");
        vehicle.setCapcity(4);
        vehicle.setVehicleType(VehicleType.NORMAL);
        driver.setVehicle(vehicle);
        rideHailing.registerDriver(driver,vehicle);

        /*
        Book a Ride
         */
        Booking booking = new Booking();
        booking.setRiderId(9879106337L);
        booking.setStartLat(1D);
        booking.setStartLong(1D);
        booking.setEndLat(3D);
        booking.setEndLong(2D);
        booking.setPreferredVehicle(VehicleType.NORMAL);
        Long bookingId = rideHailing.bookRide(booking);


        rideHailing.startRide(1L);

        rideHailing.completeRide(1L);


        rideHailing.showRideHistoryForRider(9879106337L);
        rideHailing.showRideHistoryForRider(1234567890L);







    }
}
