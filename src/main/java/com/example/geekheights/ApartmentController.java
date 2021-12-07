package com.example.geekheights;

import com.example.waterbill.Billing;
import java.util.Map;

/**
 *
 */
public class ApartmentController {

    private Billing billing;
    private Apartment apt ;
    private Map<String,BHK> typestr = Map.of("2",BHK.TWO,"3",BHK.THREE);
    public ApartmentController(Billing b){
        this.billing = b;
    }

    public String bill(){
        if( this.apt == null ){
            throw new IllegalStateException("Allot water quota to an apartment before generating bill ");
        }
        String bill = ""+this.billing.getBill(this.apt);
        this.apt = null;
        return bill;
    }

    public void add_guests(int guests){
        this.apt.addGuests(guests);
    }

    /*
    * Creates, registers and return a reference of an @see Apartment object of
    * for further operations like @see add_guests and @see bill
    * @param type 2 or 3
    * @see BHK, same time allotting quota of Corporation and Borewell water
    * in the ratio
    * @param quota_ratio
    * */
    public Apartment allot_water(String type, String quota_ratio){
        this.apt = new Apartment(typestr.get(type),quota_ratio);
        return this.apt;
    }
}
