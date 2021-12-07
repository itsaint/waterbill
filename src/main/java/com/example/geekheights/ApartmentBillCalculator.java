package com.example.geekheights;
import com.example.waterbill.BillCalculator;
import com.example.waterbill.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ApartmentBillCalculator implements BillCalculator{
    private static final Logger LOGGER = Logger.getLogger(ApartmentBillCalculator.class.getName());
    //Corporation water rate 
    static final int C_RATE = 1;
    //Borewell water rate
    static final double B_RATE = 1.5;
    //Tanker water slab rates 
    static final List<Map<String, Integer>> tankerSlabs = List.of(
            Map.of("limit",500,"rate",2),
            Map.of("limit",1000,"rate",3),
            Map.of("limit",1500,"rate",5),
            Map.of("limit",Integer.MAX_VALUE, "rate",8)
    );

    @Override
    public int getBillAmount(Consumer consumer) {
        int bill = 0;
        Apartment client = (Apartment)consumer;
        int basequota = client.baseQuota;

        String[] quota = client.quota_ratio.split(":");

        int corporation_ratio = Integer.parseInt(quota[0]);
        int borewell_ratio = Integer.parseInt(quota[1]);
        double total_ratio = corporation_ratio + borewell_ratio;

        double corporation_water = Math.round(basequota * ((double)corporation_ratio / total_ratio));
        bill += (corporation_water * C_RATE);

        double borewell_water = basequota - corporation_water;
        bill += Math.round(borewell_water * B_RATE);
        int tanker_water = client.getProduct() - basequota;
        bill = bill + getTankerCost(tanker_water);
        return bill;
    }

    public int getTankerCost(int water){
        int cost = 0;
        for (Map<String, Integer> slab : tankerSlabs)
        {
            if(water > slab.get("limit")){
                cost += (slab.get("limit") * slab.get("rate"));
                water -= slab.get("limit");
            }else {
                return cost + (water * slab.get("rate"));
            }
        }
        return cost;
    }
}
