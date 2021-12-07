package com.example.waterbill;

/**
 * supply a formula in function getBillAmount
 */
public interface BillCalculator {
    int getBillAmount(Consumer consumerCopy);
}
