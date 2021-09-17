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
public class fries extends alaCarte{
        //Sauce
    private String topping;
    
    public fries(String id, String name, String desc, double price, String topping)
    {
        super(id, name, desc, price);
        this.topping = topping;
    }
    
    public void setTopping(String topping) 
    {
        this.topping = topping;
    }
    
    public String getTopping()
    {
        return topping;
    }
    @Override
    public String getSpecial()
    {
        return topping;
    }
    
    @Override 
    public String toString()
    {
        return String.format("%-10s | %-20s | %-20s | %-40s | RM%-5.2f\n", super.getId(), super.getName(), topping,
                super.getDesc(), super.getPrice() );
    }
    
    
    
}
