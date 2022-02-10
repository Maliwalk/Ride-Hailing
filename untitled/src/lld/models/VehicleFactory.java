package lld.models;

public class VehicleFactory {

    //use getShape method to get object of type shape 
    public VehicleMetaData getVehicleMetaData(String vehicleType){
        if(vehicleType == null){
            return null;
        }
        if(vehicleType.equalsIgnoreCase(VehicleType.NORMAL.toString())){
            return new NormalVehicleMetaData();

        } else if(vehicleType.equalsIgnoreCase(VehicleType.SEDAN.toString())){
            return new SedanVehicleMetaData();

        }

        return null;
    }
}