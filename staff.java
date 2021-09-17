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
public class staff {
    
    private String staffName;
    private String staffID;
    private String password;
    
    public staff(){};
  
    public staff (String staffName, String staffID, String password) {
        this.staffName = staffName;
        this.staffID = staffID;
        this.password = password;
    }
    
    public String getStaffName() 
    {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffID() 
    {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static int validateStaffID(staff[] staff, String staffID)
    {
       // int validStaff = 1;
        int x = 0;
        int y = 0; //get which staff id
        
        while(staffID.equals(staff[x].staffID) == false) //false
        {
            x++;
            y = x-1;
            if (x == staff.length)
            {
                y = -1;
                break;
            }           
        }
        
        return y;
    }
    
    public static boolean validatePassword(int x, staff[] staff, String password){
          
        if(!staff[x].password.equals(password))
            return false;
        else 
            return true;

    }
     
 
     
   @Override
   public String toString()
   {
       return String.format("%-20s: %-30s\n %-20s: %-10s\n %-20s: %-8s\n",
                            "Staff Name", staffName, "Staff ID", staffID, "Password", password);       
   }        
}
