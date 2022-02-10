package lld.models;

import java.util.Arrays;
import java.util.List;

public class NormalVehicleMetaData implements VehicleMetaData{
    public static final List<Double> pricingCosts= Arrays.asList(new Double[]{10D, 8D, 5D});

    @Override
    public List getpricingList() {
        return pricingCosts;
    }
}
