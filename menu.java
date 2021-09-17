/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;

/*
 *
 * @author Dell
 */

public class menu {
    
    private String menuType;
    private alaCarte[] alaCarte;
    private combo[] combo;
    
    //private combo[] combo;
    
    public menu(){}
    
    public menu(String menuType, alaCarte[] alaCarte)
    {
        this.menuType = menuType;
        this.alaCarte = alaCarte;
    }
    
    public menu(String menuType, combo[] combo)
    {
        this.menuType = menuType;
        this.combo = combo;
    }
    

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public alaCarte[] getAlaCarte() {
        return alaCarte;
    }

    public void setAlaCarte(alaCarte[] alaCarte) {
        this.alaCarte = alaCarte;
    }

    public combo[] getCombo() {
        return combo;
    }

    public void setCombo(combo[] combo) {
        this.combo = combo;
    }
    
    public double getAlaCartePrice(int x)
    {
        return alaCarte[x].getPrice();
    }
    
    //For AlaCarte
    public String getFoodID(int x)
    {        
        return alaCarte[x].getId();
    }
    
    public String getName(int x)
    {
        return alaCarte[x].getName();
    }
    
    public String getSpecial(int x)
    {
        return alaCarte[x].getSpecial();
    }
    
    //For Combo
    public double getComboPrice(int x)
    {
        return combo[x].getPrice();
    }
    
    
    public String printComboSet(int x)
    {
        return combo[x].printComboSet();
    }
    
    @Override
    public String toString()
    {
        return String.format("%-20s\n", menuType);          
    }
    
    public String displayComboMenu(int x)
    {
        return combo[x].toString();
        
    }
    
    public String displayAlaCarteMenu(int x)
    {
        return alaCarte[x].toString();
        
    }
    
    
}
    
