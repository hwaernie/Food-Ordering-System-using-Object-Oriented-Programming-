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
public abstract class alaCarte {
    
    private String id;
    private String name;
    private String desc;
    private double price;
    
    public alaCarte(){}
    
    public alaCarte(String id, String name, String desc, double price)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;                
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice()
    {
        return price;
    }
    
    public void setPrice(double price) 
    {
        this.price = price;
    }
    
    //Abstract Method
    //Special = size, sauce or ice
    public abstract String getSpecial();
    
    @Override
    public String toString()
    {
        return String.format("%-10s | %-10s | %-20s | %-60s | RM%-5.2f\n", id, name, getSpecial(), desc, price );
    }
    
    /*
    id | name | desc | price
    */
}
