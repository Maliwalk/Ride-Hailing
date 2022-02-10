package lld.dao;

import lld.exception.DuplicateElementException;
import lld.exception.ValidationException;
import lld.models.*;

import java.util.*;

public class BookingEntityImpl implements BookingEntity{
    public static final String COMPLETED = "COMPLETED";
    public static final Double MIN_COST = 50D;
    public static final List<Long> pricingKiloMeteres= Arrays.asList(new Long[]{2L, 5L, Long.MAX_VALUE});
//    public static final List<Double> normalpricingCosts=Arrays.asList(new Double[]{10D, 8D, 5D});
//    public static final List<Double> sedanpricingCosts=Arrays.asList(new Double[]{15D, 10D, 8D});
    public static final List<VehicleType> vehicleTypeList=Arrays.asList(new VehicleType[]{VehicleType.NORMAL,VehicleType.SEDAN});
    private Map<VehicleType,List<String>> vehicleTypeVehicleMap;
    public static final Double MAX_DISTANCE  = 50D;
    public static  Long autoIncrementBookingId  = 1L;
    private Map<Long, Rider> riderStorage;
    private Map<Long, Driver> driverStorage;
    private Map<String, Long> vehicleDriverMapping;
    private Map<String, Vehicle> vehicleStorage;
    private Map<Long, Booking> bookingStorage;
    VehicleFactory vehicleFactory = new VehicleFactory();
//    private Map<Long, Map<Long,boolean>> userBookings;

    public BookingEntityImpl(){
        this.riderStorage = new HashMap<>();
        this.driverStorage = new HashMap<>();
        this.vehicleStorage = new HashMap<>();
        this.bookingStorage = new HashMap<>();
        this.vehicleDriverMapping = new HashMap<>();
        this.vehicleTypeVehicleMap = new HashMap<>();
    }


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
    public Long storeDriver(Driver driver,Vehicle vehicle) throws DuplicateElementException {

        if(driverStorage.containsKey(driver.getPhoneNumber())){
            throw new DuplicateElementException("Driver already exists");
        }else{
            driver.setVehicle(vehicle.getVehicleNumber());
            driverStorage.put(driver.getPhoneNumber(),driver);
            storeVehicle(vehicle);

            vehicleDriverMapping.put(vehicle.getVehicleNumber(),driver.getPhoneNumber());



            return driver.getPhoneNumber();

        }

    }

    @Override
    public BookingResponse addBooking(Booking booking) {
        String bookingVehicle = findVehicle(booking.getStartLat(),booking.getStartLong(),booking.getPreferredVehicle());
        BookingResponse bookingResponse = new BookingResponse();
        if(bookingVehicle==null){
            bookingResponse.setBookingstatus(Status.NOTBOOKED);

        }else{
            vehicleStorage.get(bookingVehicle).setAvailable(false);
            booking.setDriverId(vehicleDriverMapping.get(bookingVehicle));
            booking.setStatus(Status.BOOKED);
            booking.setBookingId(autoIncrementBookingId);
            bookingStorage.put(autoIncrementBookingId++,booking);

            List<Long> driverbookings =  driverStorage.get(booking.getDriverId()).getBookings();
            if(driverbookings == null){
                driverbookings = new ArrayList<>();
            }
            driverbookings.add(booking.getBookingId());
            driverStorage.get(booking.getDriverId()).setBookings(driverbookings);

            List<Long> riderbookings =  riderStorage.get(booking.getRiderId()).getBookings();
            if(riderbookings == null){
                riderbookings = new ArrayList<>();
            }
            riderbookings.add(booking.getBookingId());
            riderStorage.get(booking.getRiderId()).setBookings(riderbookings);
            bookingResponse.setBookingstatus(Status.BOOKED);
            bookingResponse.setBookingId(booking.getBookingId());
            bookingResponse.setVehicle(vehicleStorage.get(bookingVehicle));
            bookingResponse.setDriver(driverStorage.get(vehicleDriverMapping.get(bookingVehicle)));
            bookingResponse.setUserId(booking.getRiderId());

        }


        return bookingResponse;
    }

    @Override
    public void updateBooking(Long bookingId,Status status) {
        bookingStorage.get(bookingId).setStatus(status);
    }

    @Override
    public void updateVehicle(Long bookingId) {
        vehicleStorage.get( driverStorage.get(bookingStorage.get(bookingId).getDriverId()).getVehicle()).setAvailable(true);

    }

    public Double completeRide(Long bookingId) {
        Double distance= bookingStorage.get(bookingId).getDistance();
        Double totalCost=0D;
        Long prevKilometer=0L;
        int kiloMeter=0;

        VehicleMetaData vehicleMetaData = vehicleFactory.getVehicleMetaData(bookingStorage.get(bookingId).getPreferredVehicle().toString());
        List<Double> pricingCosts = vehicleMetaData.getpricingList();

        for(kiloMeter=0;kiloMeter<pricingKiloMeteres.size();kiloMeter++){
            if(pricingKiloMeteres.get(kiloMeter)<distance){
                totalCost+=(pricingKiloMeteres.get(kiloMeter)-prevKilometer)* pricingCosts.get(kiloMeter);
                prevKilometer=pricingKiloMeteres.get(kiloMeter);
            }else if(pricingKiloMeteres.get(kiloMeter)>distance && prevKilometer<distance){
                totalCost+=(distance-pricingKiloMeteres.get(kiloMeter-1))*pricingCosts.get(kiloMeter);

            }else{
                break;
            }
        }


        if(totalCost==0)totalCost=MIN_COST;
        totalCost=totalCost<50?MIN_COST:totalCost;
        bookingStorage.get(bookingId).setTotalCost(totalCost);
        updateVehicleLocation(driverStorage.get(bookingStorage.get(bookingId).getDriverId()).getVehicle(),bookingStorage.get(bookingId).getEndLat(),bookingStorage.get(bookingId).getEndLong());
        return totalCost;





    }

    private String findVehicle(Double startLat, Double startLong,VehicleType preferredVehicleType) {
        TreeMap<Double, String> eligibleVehicles = new TreeMap<>();

        boolean checkAvailibility = false;
        for(VehicleType vehicleType: vehicleTypeList){
            if(!checkAvailibility && vehicleType.equals(preferredVehicleType)){
                checkAvailibility=true;
            }

            if(checkAvailibility && eligibleVehicles.isEmpty() && vehicleTypeVehicleMap.get(vehicleType)!=null){
                for(String vehicleId : vehicleTypeVehicleMap.get(vehicleType)){
                    Vehicle vehicle = this.vehicleStorage.get(vehicleId);
                    Double distance = Math.sqrt(Math.pow((startLat)-(vehicle.getLatitute()),2) +Math.pow((startLong)-(vehicle.getLongitude()),2));
                    if(distance < MAX_DISTANCE && vehicle.isAvailable()) {
                        eligibleVehicles.put(distance, vehicle.getVehicleNumber());
                    }
                }
            }


        }
        return eligibleVehicles.isEmpty()?null:eligibleVehicles.pollFirstEntry().getValue();
    }

    @Override
    public void storeVehicle(Vehicle vehicle) {
        vehicle.setAvailable(true);
        if(vehicleStorage.containsKey(vehicle.getVehicleNumber())){
            throw new DuplicateElementException("Vehicle already registered with another driver");
        }
        vehicleStorage.put(vehicle.getVehicleNumber(),vehicle);

//        if(vehicleTypeVehicleMap.containsKey(vehicle.getVehicleType())){
//            vehicleTypeVehicleMap.get(vehicle.getVehicleType()).add(vehicle.getVehicleNumber());
//        }else{
//            List<String> vehicleList= new ArrayList<>();
//            vehicleList.add(vehicle.getVehicleNumber());
//            vehicleTypeVehicleMap.put(vehicle.getVehicleType(),vehicleList);
//        }
        addToVehicleMap(vehicle);
    }

    private void addToVehicleMap(Vehicle vehicle){
        if(vehicleTypeVehicleMap.containsKey(vehicle.getVehicleType())){
            vehicleTypeVehicleMap.get(vehicle.getVehicleType()).add(vehicle.getVehicleNumber());
        }else{
            List<String> vehicleList= new ArrayList<>();
            vehicleList.add(vehicle.getVehicleNumber());
            vehicleTypeVehicleMap.put(vehicle.getVehicleType(),vehicleList);
        }
    }


    @Override
    public List getBookings(Long userId,UserType type) {

        List<Long> bookingList=  type==UserType.DRIVER
                ?driverStorage.get(userId).getBookings():riderStorage.get(userId).getBookings();
        List<Booking> userBookings= new ArrayList();
        if(bookingList!=null){
            for(Long bookingId: bookingList){
                userBookings.add(bookingStorage.get(bookingId));
            }
        }

        return userBookings;

    }

    @Override
    public void updateVehicleLocation(String vehicleNumber, Double lat,Double longitude) {
        vehicleStorage.get(vehicleNumber).setLongitude(longitude);
        vehicleStorage.get(vehicleNumber).setLatitute(lat);
    }

    @Override
    public void addOrUpdateNewVehicle(Long driverId, Vehicle vehicle) {
        if(driverStorage.get(driverId).getVehicle().equals(vehicle.getVehicleNumber())){
            throw new ValidationException("Given vehicle is already the mapped vehicle");
        }
        else{
            if(vehicleStorage.containsKey(vehicle.getVehicleNumber())){
                vehicleStorage.get(vehicle.getVehicleNumber()).setAvailable(true);
            }else{
                vehicle.setAvailable(true);
                vehicleStorage.put(vehicle.getVehicleNumber(),vehicle);
            }
            vehicleStorage.get(driverStorage.get(driverId).getVehicle()).setAvailable(false);
            driverStorage.get(driverId).setVehicle(vehicle.getVehicleNumber());
            vehicleDriverMapping.put(vehicle.getVehicleNumber(),driverId);
            addToVehicleMap(vehicle);
        }

    }
}
