package com.example.geektrust;

import com.example.geekheights.ApartmentController;
import com.example.geekheights.ApartmentBillCalculator;
import com.example.waterbill.Billing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String filepath = args[0];
        ApartmentController geekheightsApp = new ApartmentController(new Billing("", new ApartmentBillCalculator()));
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] cmd = line.split(" ");
                switch (cmd[0]) {
                    case "ALLOT_WATER" -> geekheightsApp.allot_water(cmd[1], cmd[2]);
                    case "ADD_GUESTS" -> geekheightsApp.add_guests(Integer.parseInt(cmd[1]));
                    case "BILL" -> System.out.println(geekheightsApp.bill());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
