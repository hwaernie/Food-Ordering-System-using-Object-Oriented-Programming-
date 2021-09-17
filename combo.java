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
public class combo {
    
       private String comboName;
    private String comboId;

    private alaCarte[] alaCarte;
    
    private String burger;
    private String size;
    
    private String fries;
    private String topping;
    
    private String drinks;
    private String temp;
    
    private String desc;
    private double price;
    private int noOfCombo;
    
    public combo(){}; 
    
    public combo(int noOfCombo, String comboName, String comboId, alaCarte[] alaCarte, String desc, double price)
    {
        this.noOfCombo = noOfCombo;
        this.comboName = comboName;
        this.comboId = comboId;
        this.alaCarte = alaCarte;
        this.desc = desc;
        this.price = price;     
    };
    

    
    public combo(int noOfCombo, String comboId, String comboName, String burger, String size, String fries, String topping, String drinks, String temp, String desc, double price)
    {
        this.noOfCombo = noOfCombo;
        
        this.comboId = comboId;
        this.comboName = comboName;
        
        this.burger = burger;
        this.size = size; 
        
        this.fries = fries;
        this.topping = topping;
        
        this.drinks = drinks;
        this.temp = temp;
        
        this.desc = desc;
        this.price = price; 
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public alaCarte[] getAlaCarte() {
        return alaCarte;
    }

    public void setAlaCarte(alaCarte[] alaCarte) {
        this.alaCarte = alaCarte;
    }

    public String getBurger() {
        return burger;
    }

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFries() {
        return fries;
    }

    public void setFries(String fries) {
        this.fries = fries;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfCombo() {
        return noOfCombo;
    }

    public void setNoOfCombo(int noOfCombo) {
        this.noOfCombo = noOfCombo;
    }

    
    
        
    /*
    Fish Burger + Fries + Coke
    --------------------------------------------------------------
    1.| aa11   | Signature Chicken(Large) + FrenchFries(Large) + Coke(ice)| 
    */

    
    //Display the name of combo    
    @Override
    public String toString()
    {
        return String.format("%-4s | %-8s | %-10s | %-18s[%-6s]  +  %-20s[%-6s]  + %-10s[%-6s] %-4s | %-30s | RM %-5.2f", 
                noOfCombo, comboId, comboName, burger, size, fries, topping, drinks, temp , "", desc, price);
        //return String.format("%-20s: %-30s [%-10s]\n %-10s: %-5.2f ", "Combo Name", comboName, comboId, "Price", price);
    }
    
    //for order purpose
    public String printComboSet()
    {
        return String.format("%-8s(%-10s) | %-18s[%-6s]  +  %-20s[%-6s]  + %-10s[%-6s] %-2s", 
                 comboId, comboName, burger, size, fries, topping, drinks, temp, "" );
    }
    
    /*
    no| comboId| comboName| Food&Frices&Drinks| Description| Price
    --------------------------------------------------------------
    1.| aa11   | Signature Chicken(Large) + FrenchFries(Large) + Coke(ice)| 
    */
    
    /*
    
    */
    
    
    
    
    
    
    
}
