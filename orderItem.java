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
public class orderItem {
    
    private int quantity;
    private menu menu;
    private int no;
    
    public orderItem(){}
    
    public orderItem(int no, menu menu, int quantity)
    {
        this.no = no;
        this.quantity = quantity;
        this.menu = menu;
    }   

   

    public int getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public menu getMenu(int x) 
    {
        return menu;
    }

    public void setMenu(menu menu)
    {
        this.menu = menu;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

  
    public  double getUnitPrice(int  x)
    {
        if(menu.getMenuType().equals("Ala Carte Menu"))
            return menu.getAlaCartePrice(x);
        else 
            return menu.getComboPrice(x);            
    }
    
    public double getSubTotal(int x)
    {
        return getUnitPrice(x) * quantity;
    }
  

    public String toString(int x)
    {
        if(menu.getMenuType().equals("Ala Carte Menu"))
            return String.format("%-4s | %-20s | %-20s [%-10s] %-50s | %-8d | RM%-8.2f | RM%-8.2f", no, menu.getFoodID(x), menu.getName(x), menu.getSpecial(x), "", quantity, getUnitPrice(x), getSubTotal(x));    
        else 
        return String.format("%-4s | %-30s | %-8d | RM%-8.2f | RM%-8.2f", no, menu.printComboSet(x), quantity, getUnitPrice(x), getSubTotal(x));
    }
    /*return String.format("%-8s(%-10s) | %-18s[%-6s]  +  %-20s[%-6s]  + %-10s[%-6s]", 
                 comboId, comboName, burger, size, fries, topping, drinks, temp );
    
    orderType|name|UnitPrice|Quantity|
    */
    
}
