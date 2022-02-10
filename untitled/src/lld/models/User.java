package lld.models;

import com.sun.istack.internal.NotNull;
import lombok.Builder;

import java.util.List;
public class User {
    Long userId;

    public List<Long> getBookings() {
        return bookings;
    }

    String firstName;
    String lastName;

    public User setBookings(List<Long> bookings) {
        this.bookings = bookings;
        return this;
    }

    @NotNull
    Long phoneNumber;
    List<Long> bookings;

    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }


}
