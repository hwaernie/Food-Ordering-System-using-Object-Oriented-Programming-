/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingsystem;



import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Member extends Customer {

    private String memberID;
    private String ic;
    private String registerDate;
    private String expiryDate;
    
    public Member(){};
    
    //Only member need to store their ic
    public Member(boolean memberStatus, String name, String memberID, String ic,String contactNum, String email, String registerDate, String expiryDate)
    {
        super(memberStatus, name, contactNum, email);
        this.memberID = memberID;
        this.ic = ic;
        this.registerDate = registerDate;
        this.expiryDate = expiryDate;
        
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getIC() {
        return ic;
    }

    public void setIC(String ic) {
        this.ic = ic;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    
    public static int validateMemberID(Member[] member, String ic) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        int x = 0;
        int validMember = 1; 

        while(member[x].ic.equals(ic) == false) //false
        {
            x++;
            if (x == member.length)
            {
                validMember = -1;
                break;
            }           

        }
        
        if (validMember == 1) //true
            try {  
                Date expDate = formatter.parse(member[x].getExpiryDate());
                Calendar currentDate = Calendar.getInstance();
                formatter = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println("");
                if(expDate.after(currentDate.getTime()))
                {
                    System.out.println("Valid Member");
                    validMember = x;
                }
                else
                {
                    System.out.println("Member Expired");
                    validMember = -1;
                }
                
            }catch (ParseException e) 
            { 
                e.printStackTrace();  
            }  


        return validMember;
    }
   
    
    @Override
    public String toString()
    {
        return String.format("%-20s: %-30s\n %-20s: %-20s\n %-20s: %-20s\n"
               + "%-20s: %-20s\n %-20s: %20s\n %-20s: %-20s\n %-20s: %-20s\n",
               "Member Name", super.getName(), "Member ID", memberID, "IC no", ic, "Contact Number", super.getContactNum(),
               "Email", super.getEmail(), "Register Date", registerDate, "Expiry Date", expiryDate);
    }
    
        /*
   Name:  LLLL
   ID: 00000000
   IC: 
   ContactNumber: 0129852633
   Email: 
   RegDate:
   ExipryDate
    
   */
    
    
}
