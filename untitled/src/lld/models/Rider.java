package lld.models;

import lombok.Builder;
import lombok.Getter;

public class Rider extends User {
    @Override
    public String toString() {
        return "Rider{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", bookings=" + bookings +
                '}';
    }
}
