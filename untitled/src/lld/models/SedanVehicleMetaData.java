package lld.models;

import java.util.Arrays;
import java.util.List;

public class SedanVehicleMetaData implements VehicleMetaData{
    public static final List<Double> pricingCosts=Arrays.asList(new Double[]{15D, 10D, 8D});

    @Override
    public List getpricingList() {
        return pricingCosts;
    }
}
