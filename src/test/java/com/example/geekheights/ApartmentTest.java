package com.example.geekheights;

import com.example.waterbill.Billing;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ApartmentTest {
    @Test
    void testBHKResidents() {
        Assertions.assertEquals(BHK.TWO.number_of_residents, 3);
        Assertions.assertEquals(BHK.THREE.number_of_residents, 5);
    }


    @Nested
    class WhenSimpleTWOBHK {
        ApartmentBillCalculator bc = new ApartmentBillCalculator();
        ApartmentController controller = new ApartmentController(new Billing("",bc));
        @Test
        void simpleTWOBHK() {
            Apartment test_apt = new Apartment(BHK.TWO, "1:2");
            Assertions.assertEquals(1200, bc.getBillAmount(test_apt),
                    "(900 * 1/3 * 1 )+(900 * 2/3 * 1.5)");
            Assertions.assertEquals(900, test_apt.getProduct());
        }

        @Test
        void simpleTWOBHKWithGuests() {
            Apartment test_apt = controller.allot_water("2", "1:2");
            controller.add_guests(5);
            Assertions.assertEquals("2400 5200",
                    controller.bill(),
                    "");
            Assertions.assertEquals(5200,bc.getBillAmount(test_apt));
            Assertions.assertEquals(2400,test_apt.getProduct());

        }
        @Test
        void ifZeroInRatio(){
            Apartment test_apt = controller.allot_water("2","1:0");
            Assertions.assertEquals(900,bc.getBillAmount(test_apt),
                    "Only corporation water");
            test_apt = controller.allot_water("2","0:1");
            Assertions.assertEquals(1350,bc.getBillAmount(test_apt));
            Assertions.assertEquals("900 1350",controller.bill(),
                    "only borewell water");
        }
        @Test
        void ifBothZeroInRatio(){
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> controller.allot_water("2","0:0"),
                    "both quota can't be zero");
        }
        @Test
        void malformedQuotaRatio(){
            Exception e = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> controller.allot_water("2","0:0"));
            Assertions.assertEquals(Apartment.quota_ratio_format_error_message, e.getMessage());

            e = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> controller.allot_water("2","22"));
            Assertions.assertEquals(Apartment.quota_ratio_format_error_message, e.getMessage());
        }
        @Test
        void apt_should_be_null_after_bill(){
            Apartment test_apt = controller.allot_water("2","0:1");
            controller.bill();
            Exception e = Assertions.assertThrows(IllegalStateException.class,
                    () -> controller.bill(),
                    "can't call bill twice");
        }

    }

}
