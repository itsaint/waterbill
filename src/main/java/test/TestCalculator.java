package test;
import com.example.waterbill.BillCalculator;
import com.example.waterbill.Consumer;
import com.example.geekheights.Apartment;
import com.example.geekheights.BHK;

public class TestCalculator implements BillCalculator{

    @Override
    public int getBillAmount(Consumer consumer){
        //sneakishly try to alter consumer properties
        
        //consumer.product = 0; good, not working
        Apartment apt = (Apartment)consumer;
        //apt.addGuests(10); //good, this also failing
        return 0;
    }
}