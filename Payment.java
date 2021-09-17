/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class Payment {

    private double amountPaid;
    private java.util.Date paymentDate;
    
    public Payment()
    {
        paymentDate = new java.util.Date();
    }
    
    public Payment(double amountPaid)
    {
        this.amountPaid = amountPaid;
       this.paymentDate = new java.util.Date(); 
    }

  

    
    public double getAmountPaid() 
    {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    
    
    @Override
    public String toString()
    {
        return String.format("%-20s \n %-20s: RM%-5.2f", paymentDate, "Amount Paid", amountPaid);
    }
    
    /*
    Date 
    Amount Paid : 
    */
}
    
