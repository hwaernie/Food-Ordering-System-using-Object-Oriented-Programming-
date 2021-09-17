/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

public class salesReport {
    
    private java.util.Date Date;
    private order[] order;
        
    
    public salesReport()
    {
        Date = new java.util.Date();
    }    

    public salesReport(order[] order) {
        
        //this.totalSales = totalSales;
        this.order = order;
        Date = new java.util.Date();
    }  

   
    public double getTotalSales(int x)
    {
        double total = 0;
        
        for(int i = 0; i < x ; i++)
        {
            total+= order[i].getGrandTotal();
        }
        
        return total;
        
    }

   

    public order[] getOrder() {
        return order;
    }

    public void setOrder(order[] order) {
        this.order = order;
    }


    public String toString(int x)
    {
        return order[x].displayOrder();
    }
    
    public String printTotalSales(int x)
    {
        return String.format("%1s %-88s RM%-8.2f", "Total Sales: " ," ", getTotalSales(x));
    }
    
    public String displaySubTotal(int x)
    {
        return order[x].displayGrandTotal();
    }
    
    
}
