package lld;

import lld.servicee.RideHailing;
import lld.servicee.RideHailingImpl;
import lld.dao.BookingEntity;
import lld.dao.BookingEntityImpl;
import lld.models.*;

import java.util.List;

public class RideHailingMain {
    private static BookingEntity bookingEntity = new BookingEntityImpl();

    private static RideHailing rideHailing = new RideHailingImpl(bookingEntity);

    public static void main(String [] args){

        /*
        Register a Rider
         */

        Rider fRider=new Rider();
        fRider.setFirstName("ABC");
        fRider.setLastName("DEF");
        fRider.setPhoneNumber(9879106337L);
        try {
            rideHailing.registerRider(fRider);
            System.out.println("Rider "+ fRider.getFirstName() + " registered successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        Rider sRider=new Rider();
        sRider.setFirstName("Khu");
        sRider.setLastName("Mal");
        sRider.setPhoneNumber(9879106332L);
        try {
            rideHailing.registerRider(sRider);
            System.out.println("Rider "+ sRider.getFirstName() + " registered successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        /*
        Register a Driver
         */
        Driver driver=new Driver();
        driver.setFirstName("XYZ");
        driver.setPhoneNumber(8234567890L);
        Vehicle vehicle = Vehicle.builder().VehicleNumber("GJ1234")
                .capcity(4)
                .latitute(10D)
                .longitude(10D)
                .vehicleType(VehicleType.NORMAL)
                .build();
//        vehicle.setVehicleNumber("GJ1234");
//        vehicle.setCapcity(4);
//        vehicle.setLatitute(10D);
//        vehicle.setLongitude(10D);
//        vehicle.setVehicleType(VehicleType.SEDAN);
        try {
            rideHailing.registerDriver(driver, vehicle);
            System.out.println("Driver "+ driver.getFirstName() + " registered successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Driver sDriver=new Driver();
        sDriver.setFirstName("XYZ");
        sDriver.setPhoneNumber(9234567890L);
        Vehicle sVehicle = Vehicle.builder().VehicleNumber("GJ123")
                .capcity(4)
                .latitute(10D)
                .longitude(10D)
                .vehicleType(VehicleType.SEDAN)
                .build();

//                new Vehicle();
//        sVehicle.setVehicleNumber("GJ123");
//        sVehicle.setCapcity(4);
//        sVehicle.setLatitute(3D);
//        sVehicle.setLongitude(3D);
//        sVehicle.setVehicleType(VehicleType.SEDAN);

        try {
            rideHailing.registerDriver(sDriver,sVehicle);
            System.out.println("Driver "+ sDriver.getFirstName() + " registered successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }



//        rideHailing.updateAnotherVehicle(8234567890L,sVehicle);


        /*
        Book a Ride
         */
        Booking booking = new Booking();
        booking.setRiderId(9879106337L);
        booking.setStartLat(1D);
        booking.setStartLong(1D);
        booking.setEndLat(20D);
        booking.setEndLong(19D);
        booking.setPreferredVehicle(VehicleType.NORMAL);
        try{
            BookingResponse bookingResponse = rideHailing.bookRide(booking);
            System.out.println(bookingResponse);
        }catch(Exception e){
            System.out.println("Some error occurred "+ e.getMessage());
        }

        Booking booking1 = new Booking();
        booking1.setRiderId(9879106332L);
        booking1.setStartLat(1D);
        booking1.setStartLong(1D);
        booking1.setEndLat(20D);
        booking1.setEndLong(19D);
        booking1.setPreferredVehicle(VehicleType.SEDAN);
        try{
            BookingResponse bookingResponse = rideHailing.bookRide(booking1);
            System.out.println(bookingResponse);
        }catch(Exception e){
            System.out.println("Some error occurred "+ e.getMessage());
        }






        rideHailing.startRide(1L);
        rideHailing.startRide(2L);

        Long bookingId=1L;
        Double costOfRide = rideHailing.completeRide(bookingId);
        System.out.printf("Cost of Rider- BookingId: "+bookingId +" Cost: "+costOfRide);
        System.out.println( );

        Double costOfRide1 = rideHailing.completeRide(2L);
        System.out.printf("Cost of Rider- BookingId: 2 Cost: "+costOfRide1);
        System.out.println( );




        rideHailing.updateVehicleLocation("GJ123",3D,3D);
        System.out.println();


        Booking booking2 = new Booking();
        booking2.setRiderId(9879106337L);
        booking2.setStartLat(1D);
        booking2.setStartLong(1D);
        booking2.setEndLat(20D);
        booking2.setEndLong(19D);
        booking2.setPreferredVehicle(VehicleType.SEDAN);
        try{
            BookingResponse bookingResponse = rideHailing.bookRide(booking2);
            System.out.println(bookingResponse);
        }catch(Exception e){
            System.out.println("Some error occurred "+ e.getMessage());
        }


        List<Booking> userBooking;
        Long userId;
        userId=9879106332L;
        userBooking = rideHailing.showRideHistoryForRider(userId);
        System.out.println("Bookings for User "+userId +" are  "+ userBooking);
        System.out.println();

        userBooking = rideHailing.showRideHistoryForRider(9879106337L);
        System.out.println("Bookings for User "+userId +" are  "+ userBooking);
        System.out.println();

        userId=9234567890L;
        userBooking = rideHailing.showRideHistoryForDriver(userId);
        System.out.println("Bookings for User "+userId +" are  "+ userBooking);
        System.out.println();







    }


//    public User getRider(){
//        Rider fRider=new Rider();
//        fRider.setFirstName("ABC");
//        fRider.setLastName("DEF");
//        fRider.setPhoneNumber(9879106337L);
//        return fRider;
//    }
}
