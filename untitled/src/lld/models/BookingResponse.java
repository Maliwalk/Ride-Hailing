package lld.models;

public class BookingResponse {
    Long bookingId;
    Status bookingstatus;

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingId=" + bookingId +
                ", bookingstatus=" + bookingstatus +
                ", driver=" + driver +
                ", userId=" + userId +
                ", vehicle=" + vehicle +
                '}';
    }

    Driver driver;

    public Long getUserId() {
        return userId;
    }

    public BookingResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    Long userId;

    public Driver getDriver() {
        return driver;
    }

    public BookingResponse setDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public BookingResponse setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    Vehicle vehicle;


    public Long getBookingId() {
        return bookingId;
    }

    public BookingResponse setBookingId(Long bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public Status getBookingstatus() {
        return bookingstatus;
    }

    public BookingResponse setBookingstatus(Status bookingstatus) {
        this.bookingstatus = bookingstatus;
        return this;
    }


}
