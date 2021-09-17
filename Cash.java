/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

import java.util.Date;

public class Cash extends Payment{
    
    private double change;
    
    public Cash() {}
    
    
    public Cash(double amountPaid, double change) 
    {
        super(amountPaid);
        this.change = change;        
    }
    
    public double getChange() {
        return change;
    }
    
    public void setChange(double change) {
        this.change = change;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%-20s: %-20s\n%-20s: %-20s\n%-20s: RM%-5.2f\n%-20s: RM%-5.2f\n",
                "Payment Method", "Cash", "Payment Date", super.getPaymentDate(), "Amount Paid", super.getAmountPaid(), "Change", change );
    }
    
    
    
    
    /*
    Payment Method: Cash
    Amount Paid:
    Change:
    */
    
}
