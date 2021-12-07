package com.example.geektrust;
import com.example.geekheights.Apartment;
import com.example.geekheights.ApartmentBillCalculator;
import com.example.geekheights.ApartmentController;
import com.example.geekheights.BHK;
import com.example.waterbill.Billing;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import test.TestCalculator;


public class BillingTest {
    @Test
    void testIntegerDivision(){
        Assertions.assertEquals(0, 1 /2);
        Assertions.assertEquals(0.5, (double) 1 /2);
    }

    ApartmentBillCalculator bc = new ApartmentBillCalculator();
    ApartmentController controller = new ApartmentController(new Billing("",bc));

    @Test
    void testTankerSlabs() {
        Assertions.assertAll("checkEachSlabs",
                () -> Assertions.assertEquals(800, bc.getTankerCost(400),
                        "first(500L)400L * 2"),
                () -> Assertions.assertEquals(1300, bc.getTankerCost(600),
                        "500L*2 + 100L * 3"),
                () -> Assertions.assertEquals(4500, bc.getTankerCost(1600),
                        "500L*2 + second(1000L)*3 + 100L * 5"),
                () -> Assertions.assertEquals(12300, bc.getTankerCost(3100),
                        "500L*2 + 1000L*3 + 1500L*5 + 100L * 8")
        );


    }
    @Test
    void should_not_be_10333() {
        //Apartment test_apt = new Apartment(BHK.THREE, "5:4");
        ApartmentBillCalculator bc = new ApartmentBillCalculator();
        ApartmentController controller = new ApartmentController(new Billing("",bc));
        controller.allot_water("3","5:4");
        controller.add_guests(8);
        Assertions.assertEquals(10334,Integer.parseInt(controller.bill().split(" ")[1]),
                "should not be 10333");
    }
    @Test
    void checkAccessControlOfConsumer(){
        Apartment test_apt = new Apartment(BHK.TWO,"1:3");
        TestCalculator tc = new TestCalculator();
        
        
            
    }
}
