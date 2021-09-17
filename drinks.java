/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

/**
 *
 * @author Dell
 */
public class drinks extends alaCarte{
       
    private String temp; //ice or no ice
        
    public drinks(){};
    
    public drinks(String id, String name, String desc, double price, String temp) 
    {
        super(id, name, desc, price);
        this.temp = temp;
    }
    
    public void setTemp(String temp) 
    {
        this.temp = temp;
    }
    
    public String getTemp()
    {
        return temp;
    }
    
    @Override
    public String getSpecial()
    {
        return temp;
    }

    @Override 
    public String toString()
    {
        return String.format("%-10s | %-20s | %-20s | %-40s | RM%-5.2f\n", super.getId(), super.getName(), temp, 
                super.getDesc(), super.getPrice() );
        //return String.format("%-190s |%-4s %-10s\n", super.toString(),"", temp);
    }
    
    /* 
    id|name|temp|desc|price
    */
    
    
}
