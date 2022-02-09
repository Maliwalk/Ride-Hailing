package lld.dao;

import lld.exception.DuplicateElementException;
import lld.models.*;

import java.util.*;

public class BookingEntityImpl implements BookingEntity{
    public static final String COMPLETED = "COMPLETED";
    public static final Double MIN_COST = 50D;
    public static final List<Long> pricingKiloMeteres= Arrays.asList(new Long[]{2L, 5L, 6L});
    public static final List<Double> pricingCosts=Arrays.asList(new Double[]{10D, 8D, 5D, 5D});
    public static final Double MAX_DISTANCE  = 2D;
    public static  Long autoIncrementBookingId  = 2L;
    private Map<Long, Rider> riderStorage;
    private Map<Long, Driver> driverStorage;
    private Map<String, Long> vehicleDriverMapping;
    private Map<String, Vehicle> vehicleStorage;
    private Map<Long, Booking> bookingStorage;
//    private Map<Long, Map<Long,boolean>> userBookings;


    @Override
    public Long storeRider(Rider rider) {
        if(riderStorage.containsKey(rider.getPhoneNumber())){
            throw new DuplicateElementException("Rider already exists");
        }else{
            riderStorage.put(rider.getPhoneNumber(),rider);
            return rider.getPhoneNumber();
        }


    }

    @Override
    public Long storeDriver(Driver driver,Vehicle vehicle) {

        if(driverStorage.containsKey(driver.getPhoneNumber())){
            throw new DuplicateElementException("Rider already exists");
        }else{
            driverStorage.put(driver.getPhoneNumber(),driver);
            storeVehicle(vehicle);

            vehicleDriverMapping.put(vehicle.getVehicleNumber(),driver.getPhoneNumber());
            return driver.getPhoneNumber();

        }

    }

    @Override
    public Long addBooking(Booking booking) {
        String bookingVehicle = findVehicle(booking.getStartLat(),booking.getStartLong());
        vehicleStorage.get(bookingVehicle).setAvailable(false);
        booking.setDriverId(vehicleDriverMapping.get(bookingVehicle));
        booking.setStatus(Status.BOOKED);
        bookingStorage.put(autoIncrementBookingId++,booking);

        List<Long> driverbookings =  driverStorage.get(booking.getDriverId()).getBookings();
        if(driverbookings == null){
            driverbookings = new ArrayList<>();
        }
        driverbookings.add(booking.getBookingId());

        List<Long> riderbookings =  riderStorage.get(booking.getRiderId()).getBookings();
        if(riderbookings == null){
            riderbookings = new ArrayList<>();
        }
        riderbookings.add(booking.getBookingId());

        return booking.getBookingId();
    }

    @Override
    public void updateBooking(Long bookingId,Status status) {
        bookingStorage.get(bookingId).setStatus(status);
    }

    @Override
    public void updateVehicle(Long bookingId) {
        driverStorage.get(bookingStorage.get(bookingId).getDriverId()).getVehicle().setAvailable(true);

    }

    public Double completeRide(Long bookingId) {
        Double distance= bookingStorage.get(bookingId).getDistance();
        Double totalCost=0D;
        Long prevKilometer=0L;

        for(int kiloMeter=0;kiloMeter<pricingKiloMeteres.size();kiloMeter++){
            if(kiloMeter<distance){
                totalCost+=(pricingKiloMeteres.get(kiloMeter)-prevKilometer)* pricingCosts.get(kiloMeter);
                prevKilometer=pricingKiloMeteres.get(kiloMeter);
            }else{
                break;
            }
        }

        if(totalCost==0)totalCost=MIN_COST;
        return totalCost;





    }

    private String findVehicle(Double startLat, Double startLong) {
        TreeMap<Double, String> eligibleVehicles = new TreeMap<>();

        for(String vehicleId : this.vehicleStorage.keySet()){
            Vehicle vehicle = this.vehicleStorage.get(vehicleId);
            Double distance = Math.sqrt(Math.pow((startLat)-(vehicle.getLatitute()),2) +Math.pow((startLong)-(vehicle.getLongitude()),2));
            if(distance < MAX_DISTANCE && vehicle.isAvailable()) {
                eligibleVehicles.put(distance, vehicle.getVehicleNumber());
            }
        }
        return eligibleVehicles.pollFirstEntry().getValue();
    }

    @Override
    public void storeVehicle(Vehicle vehicle) {
        vehicle.setAvailable(true);
        vehicleStorage.put(vehicle.getVehicleNumber(),vehicle);
    }

    @Override
    public List getBookings(Long userId,String type) {

        return type==UserType.DRIVER.toString()
                ?driverStorage.get(userId).getBookings():riderStorage.get(userId).getBookings();

    }
}
