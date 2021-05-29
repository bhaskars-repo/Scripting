/*
 * Name:   SixthHolder
 * Author: Bhaskar S
 * Date:   05/28/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.scripting;

public class SixthHolder {
    private double amount = 0.0;
    private double rate = 0.0;
    private double fees = 0.0;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "SixthHolder{" +
                "amount=" + amount +
                ", rate=" + rate +
                ", fees=" + fees +
                '}';
    }
}
