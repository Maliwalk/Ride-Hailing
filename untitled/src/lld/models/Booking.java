package lld.models;

public class Booking {
    Long bookingId;
    Long riderId;
    Long driverId;
    Long startTime;
    Long endTime;
    VehicleType preferredVehicle;
    Double startLat;
    Double endLat;
    Double startLong;
    Double distance;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", riderId=" + riderId +
                ", driverId=" + driverId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", preferredVehicle=" + preferredVehicle +
                ", startLat=" + startLat +
                ", endLat=" + endLat +
                ", startLong=" + startLong +
                ", distance=" + distance +
                ", endLong=" + endLong +
                ", status=" + status +
                ", totalCost=" + totalCost +
                '}';
    }

    Double endLong;
    Status status;

    public Double getTotalCost() {
        return totalCost;
    }

    public Booking setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    Double totalCost;

    public Double getDistance() {
        return distance;
    }

    public Booking setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public VehicleType getPreferredVehicle() {
        return preferredVehicle;
    }



    public VehicleType getPreferredVehicle(VehicleType normal) {
        return preferredVehicle;
    }

    public Booking setPreferredVehicle(VehicleType preferredVehicle) {
        this.preferredVehicle = preferredVehicle;
        return this;
    }



    public Double getStartLat() {
        return startLat;
    }

    public Booking setStartLat(Double startLat) {
        this.startLat = startLat;
        return this;
    }

    public Double getEndLat() {
        return endLat;
    }

    public Booking setEndLat(Double endLat) {
        this.endLat = endLat;
        return this;
    }

    public Double getStartLong() {
        return startLong;
    }

    public Booking setStartLong(Double startLong) {
        this.startLong = startLong;
        return this;
    }

    public Double getEndLong() {
        return endLong;
    }

    public Booking setEndLong(Double endLong) {
        this.endLong = endLong;
        return this;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Booking setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public Long getRiderId() {
        return riderId;
    }

    public Booking setRiderId(Long riderId) {
        this.riderId = riderId;
        return this;
    }

    public Long getDriverId() {
        return driverId;
    }

    public Booking setDriverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Booking setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Booking setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Booking setStatus(Status status) {
        this.status = status;
        return this;
    }
}
