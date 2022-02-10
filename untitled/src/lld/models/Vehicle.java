package lld.models;

import lombok.Builder;

@Builder
public class Vehicle {
    Long vid;
    String VehicleNumber;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vid=" + vid +
                ", VehicleNumber='" + VehicleNumber + '\'' +
                ", capcity=" + capcity +
                ", isAvailable=" + isAvailable +
                ", latitute=" + latitute +
                ", longitude=" + longitude +
                ", vehicleType=" + vehicleType +
                '}';
    }

    int capcity;
    boolean isAvailable;

    public Double getLatitute() {
        return latitute;
    }

    public Vehicle setLatitute(Double latitute) {
        this.latitute = latitute;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Vehicle setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    Double latitute;
    Double longitude;



    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }



    public Long getVid() {
        return vid;
    }

    public Vehicle setVid(Long vid) {
        this.vid = vid;
        return this;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public Vehicle setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
        return this;
    }

    public int getCapcity() {
        return capcity;
    }

    public Vehicle setCapcity(int capcity) {
        this.capcity = capcity;
        return this;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    VehicleType vehicleType;

}
