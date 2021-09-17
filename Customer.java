package foodorderingsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Customer {
    
    private boolean memberStatus;
    private String name;
    //private String ic;
    private String contactNum;
    private String email;    
    private java.util.Date currentDate;

    
    public Customer()
    {
        currentDate = new java.util.Date();
    }
    
    public Customer(boolean memberStatus, String name, String contactNum, String email)
    {
        this.memberStatus = memberStatus;
        this.name = name;
        this.contactNum = contactNum;
        this.email = email;   
        currentDate = new java.util.Date();
    }

    public boolean isMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(boolean memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public java.util.Date getCurrentDate()
    {
        return currentDate;
    }

   
     @Override
    public String toString()
    {
        return String.format("%-20s |%-4s %-20s |%-4s %-20s |%-4s %-20s ",
                name, "", contactNum, " ", memberStatus, "", currentDate);
    }
        
    /* 
    name|IC|ContactNum|Email|MemberStatus
    */
    
    public String printCustomer()
    {
        return String.format("%-20s: %-30s\n%-20s: %-30s\n%-20s: %-30s\n", "Customer Name", name, "Member Status", memberStatus,"Date/Time", getCurrentDate());
        
    }
    
    /*
    Customer Name:
    Member Status:
    Date/Time:
    */
    
    

    
}
