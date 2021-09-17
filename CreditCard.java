/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foodorderingsystem;

import java.util.Date;

public class CreditCard extends Payment {
    
   private String bankName;
    private String cardNo;
    private String CVV;
    
    
    public CreditCard(double amountPaid, String bankName, String cardNo, String CVV) 
    {
        super(amountPaid);
        this.bankName = bankName;
        this.cardNo = cardNo;
        this.CVV = CVV;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
    

    
    
    @Override
    public String toString(){
        return String.format("%-20s: %-20s\n%-20s: %-20s\n%-20s: RM%-5.2f\n%-20s: %-20s\n%-20s: %-10s\n%-20s: %-10s\n", 
                "Payment Method", "Credit Card", "Payment Date", super.getPaymentDate(), "AmountPaid", super.getAmountPaid(), "Bank Name", bankName, "Card No", cardNo, "CVV", CVV );    
    }
    
}
