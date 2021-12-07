package com.example.geekheights;
import com.example.waterbill.Consumer;
public class Apartment extends Consumer {

    protected final BHK type;
    protected final String quota_ratio;
    protected static final int PER_PERSON_WATER = 10;
    protected static final int DAYS_IN_MONTH = 30;
    protected static String UNIT = "L";
    protected final int baseQuota ;
    public static String quota_ratio_format_error_message =
            "Quota Ratio must be like \"1:2\"";


    public Apartment(BHK type, String quota_ratio){
        this.type = type;
        int separator_index = quota_ratio.indexOf(":");
        if(separator_index > 0){
            int corporation_ratio = Integer.parseInt(quota_ratio.substring(0,separator_index));
            int borewell_ratio = Integer.parseInt(quota_ratio.substring(separator_index+1) );
            if(corporation_ratio < 1 && borewell_ratio < 1){
                throw new IllegalArgumentException(quota_ratio_format_error_message);
            }
        }
        else {
            throw new IllegalArgumentException(quota_ratio_format_error_message);
        }
        this.quota_ratio = quota_ratio;
        this.product = type.number_of_residents * PER_PERSON_WATER * DAYS_IN_MONTH;
        this.baseQuota = this.product;
    }

    protected void addGuests(int people){
        //we are only interested in product
        this.product +=  (people * PER_PERSON_WATER * DAYS_IN_MONTH);
    }
}
