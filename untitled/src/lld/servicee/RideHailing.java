package lld.servicee;

import lld.models.*;

import java.util.List;

public interface RideHailing {


    public Long registerRider(Rider rider) throws Exception;
    public Long registerDriver(Driver rider,Vehicle vehicle) throws Exception;
    public BookingResponse bookRide(Booking booking);
    public void startRide(Long bookingId);
    public Double completeRide(Long bookingId);
    public void updateAnotherVehicle(Long driverId,Vehicle vehicle);
    public Long registerVehicke(Vehicle rider);
    public List showRideHistoryForRider(Long riderId);
    public void updateVehicleLocation(String vehicleNumber,Double latitude, Double longitude);
    public List showRideHistoryForDriver(Long driverId);


}
