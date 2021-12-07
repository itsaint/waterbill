package com.example.geekheights;

public enum BHK {
    TWO(3) , 
    THREE(5);

    public final int number_of_residents;

    BHK(int number_of_residents){
        this.number_of_residents = number_of_residents;
    }
}
