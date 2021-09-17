
package foodorderingsystem;

import java.util.*;
import java.lang.String;
import java.lang.Character;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException; 
import java.util.Calendar;
import java.time.LocalDate;

public class FoodOrderingSystem {

    public static void main(String[] args) throws ParseException{
               
        //Set Close time     
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        
        //Close at 8pm
        Date EndTime = dateFormat.parse("23:00");  
        
        //Get current time
        Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        

        
        int inputCus = 0; //0 = nonMember, 1 = member, -1 = dont want to continue
        boolean isMember = true; //true = member, false = nonMember
        String validIC = null;        
        int validMember = 0; //-1 = invalid
        
        char respond = 'N';  
        
        int cusArrSize = 0;
        
        int paymentChoice = 0;
        //To store the payment method
        String[] paymentMethod = new String[100];
        
        //call members
        Member[] member = storeMember();
        
        //customer array
        Customer[] customer = new Customer[100];
        for(int i=0; i<customer.length; i++)
        {
            customer[i] = new Customer(true,null,null,null);
        }        
        
        //Cash
        Cash[] cash = new Cash[50];
        for(int i = 0 ; i < cash.length; i++)
        {
            cash[i] = new Cash(0,0);
        }
        
        //Credit Card
        CreditCard[] creditCard = new CreditCard[50];
        for(int i = 0 ; i < creditCard.length; i++)
        {
            creditCard[i] = new CreditCard(0,null, null, null);
        }
       
                
        //Order
        order[] order = new order[100];
        for(int i = 0 ; i < order.length; i++)
        {
            order[i] = new order(0, null, 0, null);
        }
        //Declare alaCarte
        alaCarte[] alaCarte = alaCarte();   
        
        //Declare combo
        combo[] combo = combo(alaCarte);
        
        //Save alaCarte and combo in the menu
        menu[] alaCarteMenu = alaCarteMenu(alaCarte);
        menu[] comboMenu = comboMenu(alaCarte, combo);
                       
        
        do
        {
            //Close time 
            if (CurrentTime.after(EndTime))
            {
                System.out.println("Closed. Please come back tomorrow");
                break;
            }
        
            displayLogo(alaCarteMenu,comboMenu);
            
           
            //input is member or not
            //is member or not member
            isMember = checkMember(); //Return true or false
            System.out.println("");
            if (isMember == false) //if non member
            {
                inputCus = 0;//Go to customer input
                
            } //directly go to non member input                
            
            //is a Member
            else 
            {
                //if is then input ic //return ic number
                validIC = validateIc(); //If invalid, return "Invalid IC"

                if  (validIC.equals("Invalid IC")) //Invalid password
                    validMember = -1;
                else 
                    //check the validation of member //Member had expired or havent
                    validMember = memberValidation(member, validIC);
                
                if (validMember == -1) //if expiry or Invalid IC
                    inputCus = response();
                else
                {
                    inputCus = 1; //continue with member 
                    System.out.println("");
                    System.out.println("YOU ARE ENTITLED TO A 10% DISCOUNT !!!");
                    System.out.println("");
                }
                                
            } 
           
            double sumTotal = 0; //Sum before discount
            double grandTotal = 0; //sum after discount
            double amountPaid = 0;            
            
            if(inputCus == 0 | inputCus == 1) 
            {
                cusArrSize = inputCustomer(customer, member, inputCus, validMember, cusArrSize);    
                

                //Anymore order?
                char anyMore = 'Y';
                
                //FoodCategory for alaCarte menu (Either burger, fries or drinks)
                char foodCategory = 'B';
                //To store the food selected by customer and food quantity
                int [][] orderFood = new int[1][2]; 
                
                //Create array for orderItem
                //Max 10 orders for each customer
                orderItem[] orderItem = new orderItem[10];
                for(int x = 0; x < 10 ; x++)             
                {
                    orderItem[x] = new orderItem();
                } 
                
                //Create array For displayOrder 
                //To Store the orderItem 
                String[] displayOrderItem = new String[10];
                for(int x = 0; x < 10 ; x++)
                {
                    displayOrderItem[x] = null;
                }
                
                //1 order have how many item
                int numItem = 0;
                
                //to store sub total of each item in a order
                double[] subTotal = new double[10];
                for(int x = 0; x < 10 ; x++)
                {
                    subTotal[x] = 0;
                }
                
                do         
                {
                    //choose which menu (AlaCarte or Combo)         
                    String menuType = inputMenuType();   
                    System.out.println("");
                    
                    //If customer choose alaCarte menu
                    if (menuType.equals("Ala Carte Menu"))
                    {              
                        //Get food category (Burger or Fries or Drinks                        
                        foodCategory = inputFoodCategory(alaCarte);
                        //Display the burger menu or fries menu or drinks menu
                        displayFoodCategory(alaCarte, foodCategory);  
                        
                        //food chosen and quantity
                        orderFood = inputOrderAlaCarte(foodCategory);
                        orderItem[numItem] = new orderItem((numItem+1), alaCarteMenu[orderFood[0][0]], orderFood[0][1]);
                        
                    }
                    else if (menuType.equals("Combo Menu"))
                    {
                        //Display Combo menu
                        System.out.printf("%-4s | %-8s | %-10s | %-86s | %-30s | %-10s\n", 
                                "No", "Combo ID", "Combo Name", "Food" , "Description", "Price");
                        straightLine();                   
                        displayComboMenu(comboMenu);
                        
                        //food chosen and quantity
                        orderFood = inputOrderCombo();
                        orderItem[numItem] = new orderItem((numItem+1), comboMenu[orderFood[0][0]], orderFood[0][1]);
                    }
                    
                    //Inside the toString(hard to pass in the index number)
                    displayOrderItem[numItem]  = orderItem[numItem].toString(orderFood[0][0]);
                    
                    //store subTotal in to array to calculate 
                    subTotal[numItem] = orderItem[numItem].getSubTotal(orderFood[0][0]);     
                    
                    anyMore = anyMore();
                    numItem++;
                    
                }while(anyMore == 'Y');
                
                //return grand total
                sumTotal = calculateSumTotal(subTotal, numItem);
                order[cusArrSize-1] = new order((1000+cusArrSize), orderItem, sumTotal, customer[cusArrSize-1]);
                grandTotal = order[cusArrSize-1].getGrandTotal();
                
                //Display all your order (no customer info)
                //Just to see how much to pay
                displayOrderDetails(orderItem, order, displayOrderItem, numItem, cusArrSize);
                
                System.out.println("");
                paymentChoice = 0;            
                System.out.print("Payment method  \n");
                System.out.println("-----------------");
                System.out.print("Cash [1] \n");             
                System.out.print("Credit Card [2] \n");

                paymentChoice = inputPaymentChoice();
                
                System.out.println("");
                
                switch (paymentChoice) 
                {
                    case 1:
                        
                        amountPaid = inputAmountPaid(paymentChoice, grandTotal);                    
                        double change = change(grandTotal, amountPaid);
                        cash[cusArrSize-1] = new Cash(amountPaid, change);
                        paymentMethod[cusArrSize-1] = "Cash";
                        break;
                        
                        
                    case 2:                 
                        amountPaid = inputAmountPaid(paymentChoice, grandTotal);
                        creditCard[cusArrSize-1] = new CreditCard(amountPaid, inputBankName(), inputCardNo(), inputCVV());                   
                        paymentMethod[cusArrSize-1] = "Credit Card";
                }
                
               displayReceipt(orderItem, order, displayOrderItem, numItem, paymentChoice, cash, creditCard, cusArrSize);

            }
            System.out.println("");

            respond = nextCustomer();
            
        }while (respond == 'Y');
        
        salesReport report = new salesReport(order);

        
        staff[] staff = storeStaffData();
        
        boolean validStaff = true;
        boolean viewReport = true;
        boolean isStaff = true;

        viewReport = viewReport();
        if(viewReport == true)
        {            
            isStaff = verifyStaff();
            if (isStaff == false)
            {
                System.out.println("");
                System.out.println("You are not a staff, Access Denied.\n");
            }
                
            else
            {
                System.out.println("");
                line();
                System.out.println("Staff Login");
                validStaff = login(staff); 
                if (validStaff == true)
                {
                    displayReport(report, cusArrSize, paymentMethod);
                    displayCustomerList(customer, cusArrSize);
                    
                }  //Display customer list
                else
                    System.out.println("You have exit the program.");
            }
        }
        else            
            System.out.println("You have exit the program.");
    }

    

    public static void displayLogo(menu[] alaCarteMenu, menu[] comboMenu)
    {   
        System.out.println("");
        System.out.print(" ------------------------  \n"); 
        System.out.print("| WELCOME TO WEEDONALD'S | \n");
        System.out.print(" ------------------------  \n");     
        System.out.println("");
        straightLine(); //***
        
        System.out.printf("%-10s | %-20s | %-20s | %-40s | %-8s\n", "Food ID", "Food Name", "Remark",
                "Description", "Unit Price");
        straightLine();
        
        displayAlaCarteMenu(alaCarteMenu);

        System.out.printf("%-4s | %-8s | %-10s | %-86s | %-30s | %-10s\n", 
                                "No", "Combo ID", "Combo Name", "Food" , "Description", "Price");
        straightLine();
        displayComboMenu(comboMenu);
        line();
        
        System.out.println("");
    }
    
    public static Member[] storeMember()
    {
        
        Member[] member = {
            new Member(true, "Lee Shu Ern", "weeAA00011", "000901-14-1426", "012-985 2633", "shuern2633@gmail.com", "01-09-2019", "31-08-2021"), 
            new Member(true, "Chew Hwa Ern", "weeAA00022", "990812-14-1111", "017-206 9778", "hwaern99@gmail.com", "12-08-2018", "11-08-2020"), 
            new Member(true, "Hee Sze Wei", "weeAA00033", "000212-02-2222", "016-691 8841", "szewei00@gmail.com", "12-02-2018", "11-08-2020"),
            new Member(true, "Serene Hee", "weeAA00055", "000112-14-3333", "016-771 2633", "serene11@gmail.com", "12-02-2018", "11-10-2020"),
            new Member(true, "Joanna Lee", "weeAA00066", "000328-06-5555", "019-988 2633", "joanna22@gmail.com", "12-06-2019", "11-06-2021"),
            new Member(true, "Chan Mei Hui", "weeAA00077", "000218-02-6666", "016-246 1756", "meihui33@gmail.com", "18-02-2017", "17-02-2019"),
            new Member(true, "Hong Wei Hao", "weeAA00088", "000918-14-7777", "013-340 2803", "weihao55@gmail.com", "18-09-2019", "17-09-2021"),
            new Member(true, "Dylon Tan", "weeAA00099", "001027-14-8888", "010-300 6566", "dylon66@gmail.com", "27-10-2019", "27-10-2021")}; 
                //get size of array of member
        
        return member;
    }
        
    //return is Member or not (true or false)
    public static boolean checkMember()
    {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        boolean isMember = true;
        char inputMember = 'Y';
        
        do 
        {
            try{                
                System.out.print("Is this customer a member? Yes[Y]/No[N]: ");
                inputMember = input.next("[y|Y|N|n]").charAt(0); //Makesure customer only enter y or n (1 letter)    
                inputMember = Character.toUpperCase(inputMember);                
                
                continueInput = false;
                
            }catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again.");
                System.out.println("");
                continueInput = true;
                input.nextLine();
            }
            
        }while(continueInput);

        if (inputMember == 'N')
            isMember =  false; //Directly go enter details
        else if (inputMember == 'Y')
            isMember = true;
        
        return isMember;
        
    }
    
    //Check IC Format
    //return IC number 9if invalid IC, return "Invalid IC"
    public static String validateIc()
    {
        Scanner input = new Scanner(System.in);

        String ic = null;
        int i = 0;
                
        //Have 3 chances to enter IC Number
        for( i = 0; i < 3; i++)
        {
            try           
            {
                //Must enter valid ic number with the format/pattern
                System.out.print("Enter IC number[000000-00-0000]: ");
                ic = input.nextLine();
                
                if(i == 3)
                {
                    System.out.println("3 Invalid Inputs. Please try again from the beginning.");
                    ic = "Invalid IC";
                    break;
                }
                
                if (ic.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}"))           
                    break;            
                else
                    throw new InputMismatchException();
            }catch(InputMismatchException e)
            {
                System.out.println("Invalid IC Number");
                System.out.println("Please enter a valid IC number. You have " + (2-i) + "more attempt(s)");
                System.out.println("");
            }
        }
        
        return ic;
    }
        
    //Check Member expire or not
    public static int memberValidation(Member[] member , String ic) throws ParseException
    {       
        return Member.validateMemberID(member, ic);
        
    }
    
    //For expired member and customer who enter invalid password
    public  static int response()
    {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        int inputCus = 1;
        char respond = 'Y';
        do{
            try
            {
                System.out.print("Do you want to continue to place order at normal price? Yes[Y]/No[N]");
                respond = input.next("[y|Y|n|N]").charAt(0);
                respond = Character.toUpperCase(respond); 
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid response! Please enter valid response. Yes[Y]/ No[N]");
                System.out.println("");
                continueInput = true;
                input.nextLine();
            }
            
        }while(continueInput);
        
        if (respond == 'Y') //false = continue with non member
            inputCus = 0; 
        else //Dont wan to continue
            inputCus = -1; 
        
        return inputCus;
        
    }
    
    //Input Customer info //Return cusArrSize
    /**if he or she is member of our shop, then he/her no need to input,
            their info because will get their info from member class and automatically save in customer array*/
    public static int inputCustomer(Customer[] customer, Member[] member, int inputCus, int validMember, int c)
    {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        
        if (inputCus == 1) //Member
            customer[c] = new  Customer(true, member[validMember].getName(),member[validMember].getContactNum(), member[validMember].getEmail());
        else
        {
            System.out.print("Enter customer name: ");
            String name = input.nextLine();
            
            do
            {
                System.out.print("Enter contact number[123-4567890]: ");
                String contact = input.nextLine();
                
                if (contact.matches("[\\d]{3}-[\\d]{7}"))     
                {
                    System.out.print("Enter email address: ");
                    String email = input.nextLine();
                    customer[c] = new Customer(false, name, contact, email);
                    continueInput = false;
                    
                }
                else
                {
                    System.out.println("Invalid Contact Number.");
                    System.out.println("Please enter a valid contact number.");
                    continueInput = true;            
                }
            }while(continueInput);
      
        }

        c++;
        return c;
        
    }
    
    //nextCustomer
    public static char nextCustomer()
    {
        Scanner input = new Scanner(System.in);
        char respond = 'Y';
        boolean continueInput = true;
        
        do{
            try
            {
                System.out.print("Next Customer? Yes[Y]/No[N]:");
                respond = input.next("[y|Y|n|N]").charAt(0);
                respond = Character.toUpperCase(respond);              
               
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid response! Please enter valid response. Yes[Y]/ No[N]");
                System.out.println("");
                continueInput = true;
                input.nextLine();
            }
            
        }while(continueInput);
     
        return respond;
        
    }
    
    public static void displayCustomerList(Customer[] customer, int cusArray)    
    {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
        Date dateobj = new Date();
       
         //display report title and date
        System.out.println("\n\n");
        line();
        System.out.println("---------- Customer List " + df.format(dateobj)+ " ----------");
        line();
        System.out.println("Total Customer: " + cusArray);
        System.out.printf("%-20s |%-4s %-20s |%-4s %-20s |%-4s %-20s\n", "Name", "", "Contact Number","", "Member Status","", "Date Created");
        line();
        for(int i = 0; i < cusArray; i++)
        {
            System.out.println(customer[i].toString());
        }
        
        for(int i = cusArray; i < customer.length; i++)
        {
            System.out.print("");
        }
        System.out.println("");
    }
               
    //Create object, store alaCArte
    public static alaCarte[] alaCarte()
    {
        alaCarte[] alaCarte = {
            new burger ("B[01]", "Signature Chicken", "Grilled Chicken with Signature Sauce", 12, "Large"),
            new burger ("B[02]", "Signature Chicken", "Grilled Chicken with Signature Sauce", 10, "Medium"),
            new burger ("B[03]",  "Signature Beef", "Smoked Grilled Beef with Signature Sauce", 15, "Large"),
            new burger ("B[04]",  "Signature Beef", "Smoked Grilled Beef with Signature Sauce", 13, "Medium"),
            new burger ("B[05]", "Fish Fillet", "Fillet with Signature Sauce", 14, "Large"),
            new burger ("B[06]", "Fish Fillet", "Fillet with Signature Sauce", 12, "Medium"),
            new fries ("F[01]", "French Fries", "Fresh Cut Potato Fries", 6, "Salted Egg"),
            new fries ("F[02]", "French Fries", "Fresh Cut Potato Fries", 4, "Cheese"),
            new fries ("F[03]", "Sweet Potato Fries", "Fresh Cut Sweet Potato Fries", 6, "Salted Egg"),
            new fries ("F[04]", "Sweet Potato Fries", "Fresh Cut Sweet Potato Fries", 4, "Cheese"),
            new fries ("F[05]", "Curly Fries", "Fresh Cut Curly Potato Fries", 6, "Salty Egg"),
            new fries ("F[06]", "Curly Fries", "Fresh Cut Curly Potato Fries", 4, "Cheese"),
            new drinks ("D[01]", "Coke", "Soft Drink", 4, "Ice"),
            new drinks ("D[02]", "Coke", "Soft Drink", 2, "No Ice"),
            new drinks ("D[03]", "Sprite", "Soft Drink", 4, "Ice"),
            new drinks ("D[04]", "Sprite", "Soft Drink", 2, "No Ice"),
            new drinks ("D[05]", "Lemon Tea", "Soft Drink", 4, "Ice"),
            new drinks ("D[06]", "Lemon Tea", "Soft Drink", 2, "No Ice")};
        
        return alaCarte;
     }  
    
     
    //Create object, store combo set
    //Take food from child classes
    public static combo[] combo(alaCarte[] alaCarte)
    {
        String[] id = new String[alaCarte.length];
        String[] name = new String[alaCarte.length];
        String[] special = new String[alaCarte.length];
        
        for(int i = 0; i < alaCarte.length; i++)
        {
           id[i] = alaCarte[i].getId();
           name[i] = alaCarte[i].getName();
           special[i] = alaCarte[i].getSpecial();            
        }

        //everything in combo is not allow to change
        //Only do for large burger 0,2, 4
        //And fries with cheese topping only  7, 9, 11
        // Only for lemon tea 17, 18
        //Only ice
        combo[] comboMenu = { new combo(1, "C01", "Combo [1]", name[0], special[0],name[7], special[7], name[16], special[16], "Hwa Ern's PICK!", 18),
            new combo(2, "C02", "Combo [2]", name[0], special[0], name[9], special[9], name[16], special[16], "Shu Ern went FARMING!", 18 ),
            new combo(3, "C03", "Combo [3]", name[0], special[0], name[11], special[11], name[16], special[16], "Serene's homegrown POTATO!", 18 ),
            new combo(4, "C04", "Combo [4]", name[2], special[2],name[7], special[7], name[16], special[16], "Serene's PICK!", 18),
            new combo(5, "C05", "Combo [5]", name[2], special[2], name[9], special[9], name[16], special[16], "Hwa Ern's FAV!", 18 ),
            new combo(6, "C06", "Combo [6]", name[2], special[2], name[11], special[11], name[16], special[16], "Shu Ern' PICK!", 18 ),
            new combo(7, "C07", "Combo [7]", name[4], special[4],name[7], special[7], name[16], special[16], "Hwa Ern went FISHING!", 18),
            new combo(8, "C08", "Combo [8]", name[4], special[4], name[9], special[9], name[16], special[16], "Serene's FAV!", 18 ),
            new combo(9, "C09", "Combo [9]", name[4], special[4], name[11], special[11], name[16], special[16], "Shu Ern's CURLYHAIR!", 18)};
        
        return comboMenu;
    }
     
    //Create object menu
    public static menu[] alaCarteMenu(alaCarte[] alaCarte)
    {      
        menu[] alaCarteMenu = new menu[alaCarte.length];         
        for(int x = 0; x < alaCarte.length;x++)
        {
            alaCarteMenu[x] = new menu("Ala Carte Menu", alaCarte);
            //System.out.println(alaCarteMenu[x].toString());
        }

        return alaCarteMenu;         
    }
    
    //Create object menu
    public static menu[] comboMenu(alaCarte[] alaCarte, combo[] combo)
    {
        menu[] comboMenu = new menu[combo.length];
        for(int x = 0; x < combo.length;x++)
        {
            comboMenu[x] = new menu("Combo Menu", combo);
            //System.out.println(comboMenu[x].toString());
        }        
        return comboMenu;
    }    
    
    //Display AlaCarte Menu
    public static void displayAlaCarteMenu(menu[] alaCarteMenu)
    {
        System.out.println(alaCarteMenu[0].toString());
        
        for(int i=0; i < alaCarteMenu.length; i++)
        {
            System.out.println(alaCarteMenu[i].displayAlaCarteMenu(i));
        }
        System.out.println("");
    }
    
    //Display Combo Menu
    public static void displayComboMenu(menu[] comboMenu)
    {
        System.out.println(comboMenu[comboMenu.length-1].toString());
        
        for(int i=0; i < comboMenu.length; i++)
        {
            System.out.println(comboMenu[i].displayComboMenu(i));
        }
        System.out.println("");
    }
    
    //Input which menu customer wan to choose
    public static String inputMenuType()
    {
        Scanner input = new Scanner(System.in);
        char menuType = 'A';
        String menu = null;
        boolean continueInput = true;
        
        do{
            try
            {
                System.out.print("Please select menu type: AlaCate[A]/Combo[C]: ");
                menuType = input.next("[a|A|c|C]").charAt(0);
                menuType = Character.toUpperCase(menuType);              
                
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again.");
                System.out.println("");
                continueInput = true;               
                input.nextLine();
            }
            
        }while(continueInput);
        
        if (menuType == 'A')
            menu = "Ala Carte Menu";        
        else if (menuType == 'C')
            menu = "Combo Menu";         
        
        return menu;
                 
    }    
        
    //For alaCarte
    //return which category customer had choose
    public static char inputFoodCategory(alaCarte[] alaCarte)
    {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        char c = 'B';
        
        
        do{
            try
            {
                System.out.print("Choose a food category. Burger[B]/Fries[F]/Drinks[D]:");
                
                c = input.next("[b|B|f|F|d|D]").charAt(0);
                c = Character.toUpperCase(c);
                
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again.");
                System.out.println("");
                continueInput = true;               
                input.nextLine();
            }
            
        }while(continueInput);
        
        System.out.println("");
        if (c == 'B')
        {
            System.out.println("BURGER");
        }
        else if (c == 'F')
        {
            System.out.println("FRIES");
        }
        else if (c == 'D')
        {
            System.out.println("DRINKS");
            
        }
        
        System.out.printf("%-10s | %-20s | %-20s | %-40s | %-8s\n", "Food ID", "Food Name", "Remarks",
                "Description", "Unit Price");
        
        straightLine();  
        
        //displayFoodCategory(alaCarte, c);  
        
        return c;
    }
    
    //For AlaCarte
    //Display based on category (For Ala Carte)
    public static void displayFoodCategory(alaCarte[] alaCarte, char foodCategory)
    {        
        if (foodCategory == 'B')
        {
            for(int x=0; x < alaCarte.length; x++)
            {
                if(alaCarte[x] instanceof burger)
                    System.out.println(((burger)alaCarte[x]).toString());          
            }   
        }
        else if (foodCategory == 'F')
        {
            for(int x=0; x < alaCarte.length; x++)
            {
                if(alaCarte[x] instanceof fries)
                    System.out.println(((fries)alaCarte[x]).toString());       
            }   
        }    
        else if (foodCategory == 'D')
        {
            for(int x=0; x < alaCarte.length; x++)                 
            {
                if(alaCarte[x] instanceof drinks)
                    System.out.println(((drinks)alaCarte[x]).toString());                  
            }   
        }         
        
    }
       
    //return quantity and food selected
    public static int[][] inputOrderAlaCarte(char foodCategory)           
    {
        Scanner input = new Scanner(System.in); 
        //r0, c0 = item 
        //r0, c1 = quantity//food|quantity                                  
        int[][] foodSelected = new int[1][2];
        boolean continueInput = true;
        
        //Food selected
        int item = 1;
        int quantity = 0;
        do
        {
            try
            {
                if(foodCategory == 'B')
                {
                    System.out.print("Select item [1|2|3|4|5|6]: ");
                    item = input.nextInt();
                    if (item < 1 | item > 6)             
                    {
                        throw new IllegalArgumentException();
                    }
                }
                else if(foodCategory == 'F')
                {
                    System.out.print("Select item [1|2|3|4|5|6]: ");
                    item = input.nextInt();
                    if (item < 1 | item > 6)             
                        throw new IllegalArgumentException();

                    switch(item)
                    {
                        case 1:
                            item = 7;
                            break;
                        case 2:
                            item = 8;
                            break;
                        case 3:
                            item = 9;
                            break;
                        case 4:
                            item = 10;
                            break;
                        case 5:
                            item = 11;
                            break;
                        case 6:
                            item = 12;                                
                    }
                }
                else if(foodCategory == 'D')     
                {
                    System.out.print("Select item [1|2|3|4|5|6]: ");
                    item = input.nextInt();
                    if (item < 1 | item > 6)             
                    {
                        throw new IllegalArgumentException();
                    }
                    switch(item)
                    {
                        case 1:
                            
                            item = 13;
                            break;
                        case 2:
                            item = 14;
                            break;
                        case 3:
                            item = 15;
                            break;
                        case 4:
                            item = 16;
                            break;
                        case 5:
                            item = 17;
                            break;
                        case 6:
                            item = 18;
                    }
                }
                input.nextLine();     
                
                System.out.print("Quantity: ");
                quantity = input.nextInt();
                
                if (quantity < 0)
                    throw new IllegalArgumentException();
                else
                {
                    continueInput = false;
                }         
                
                //store the item selected and also quantity
                foodSelected[0][0] = (item-1);                      
                foodSelected[0][1] = quantity; 
                input.nextLine();
            }catch (IllegalArgumentException e)
            {
                System.out.println("Invalid Input.");
                System.out.println("Please enter [1|2|3|4|5|6] to select.");
                continueInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again");
                continueInput = true;
            }
            
        }while(continueInput);
        
        return foodSelected;                  
        
    }
    
    //Input if customer choose combo
    public static int[][] inputOrderCombo()
    { 
        Scanner input = new Scanner(System.in);

        //r0, c0 = item 
        //r0, c1 = quantity//food|quantity                                  
        int[][] foodSelected = new int[1][2];
        boolean continueInput = true;
        
        //Food selected
        int item = 1;
        int quantity = 0;
        do
        {
            try
            {
                System.out.print("Select a Combo[1|2|3|4|5|6|7|8|9]: ");
                item = input.nextInt();
                input.nextLine();
                

                if (item < 0 || item > 9)
                    throw new IllegalArgumentException();

                
                System.out.print("Quantity: ");
                quantity = input.nextInt();
                input.nextLine();
                
                if (quantity <= 0)
                    throw new IllegalArgumentException();
                
                foodSelected[0][0] = item-1;
                foodSelected[0][1] = quantity;
                continueInput = false;
                
                
            }catch(IllegalArgumentException e)
            {
                System.out.println("Invalid Input.");
                System.out.println("Please enter integer to represent a combo.");
                continueInput = true;
            }
        }while(continueInput);
        
        return foodSelected;
    }
    
    //addOrderItem
    public static char anyMore()
    {      
        Scanner input = new Scanner(System.in);
        char anyMore = 'Y';
        boolean continueInput = true;
       
        do{
            try
            {
                System.out.println("");
                System.out.print("Add Order? Yes[Y]/No[N] ");
                anyMore = input.next("[y|Y|n|N]").charAt(0);
                anyMore = Character.toUpperCase(anyMore);
                
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again.");
                System.out.println("");
                continueInput = true;               
                input.nextLine();
            }
            
        }while(continueInput);

        System.out.println("");
        
        return anyMore;
    }
    
    //Calculate sum Total of the order haven include discount yet
    public  static double calculateSumTotal(double[] subTotal, int numItem)             
    {
        double sumTotal = 0;
        for(int i = 0; i< numItem;i++)
        {
            sumTotal += subTotal[i];
        }
        
        return sumTotal;
    }    
    
    //Display Order
    public static void displayOrderDetails(orderItem[] orderItem, order[] order, String[] displayOrderItem, int numItem, int cusArrSize)
    {
        //Display Customer info
        //System.out.println(order[cusArrSize-1].printCustomer());
        
        line();
        System.out.println("Order Details");
        System.out.println("Total Item:" + orderItem[numItem-1].getNo());
        line();
        
        System.out.printf("%-4s | %-20s | %-84s | %-4s | %-10s | %-10s\n", 
                "No", "Food ID", "Food Name", "Quantity", "Unit Price", "Sub Total");
        straightLine();
        
        //Display Item 
        for(int i = 0; i< numItem;i++)
        {
            System.out.println(displayOrderItem[i]);
        }
        System.out.println("");
        System.out.println(order[cusArrSize-1].printDis());
        
        //Display Grand Total
        equalLine();
        System.out.println(order[cusArrSize-1].toString());
        equalLine();
        line();
    }
  
    
    //Payment
    
    //Return payment method
    //Return int (1 - cash || 2- Credit Card)
    public static int inputPaymentChoice(){
        
        Scanner input = new Scanner(System.in);
        int paymentChoice = 1;
        boolean continueInput = true;
        do
        {
            System.out.print("Enter your payment choice: ");
            paymentChoice = input.nextInt();
            
            
            if (paymentChoice != 1 & paymentChoice != 2)
            {
                
                System.out.println("Invalid Input.");
                System.out.println("Please enter again.");
                continueInput = true;  
                
            }
            else 
                continueInput = false;                    
            
        }while(continueInput);
        
        return paymentChoice;
    }
    
    public static double inputAmountPaid(int paymentChoice, double grandTotal)
    {        
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        double amountPaid = 0;
        
        if (paymentChoice == 1)
            do
            {
                System.out.printf("%-10s: RM ", "Enter amount paid");
                amountPaid = input.nextDouble();
                
                if(amountPaid <= 0 | amountPaid < grandTotal)
                {
                    System.out.println("Invalid Payment Amount");
                    continueInput = true;
                }
                else
                    continueInput = false;
            }while(continueInput);
        
        else if (paymentChoice == 2)
        {
            amountPaid = grandTotal;
            //System.out.printf("%-10s: RM%-5.2f ", "Amount Paid", amountPaid);
            //System.out.println("");
        }
                
        
        return amountPaid;
        
    }
        
    //If cash is selected  
    //Calculate Change
    public static double change(double grandTotal, double amountPaid)
    {
        
        double change = 0;
        change = amountPaid-grandTotal;        
      
        return change;
    }
       
    //if credit card is selected
    public static String inputBankName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the bank name of the card : ");       
        String bankName = input.nextLine();
        return bankName;
    }
    
    public static String inputCardNo(){
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        String cardNo = null;
        do
            {
                System.out.print("Enter the card number [1234567890] : ");
                cardNo = input.nextLine(); 
                
                if (cardNo.matches("[\\d]{10}"))     
                {
                    continueInput = false;
                    
                }
                else
                {
                    System.out.println("Invalid Card Number");
                    System.out.println("Please enter the right card number.");
                    continueInput = true;            
                }
            }while(continueInput);
        
        return cardNo;
    }
    
    public static String inputCVV(){
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        String CVV = null;
        do
            {
                System.out.print("Enter the cvv of the card [123] : ");
                CVV = input.nextLine(); 
                
                if (CVV.matches("[\\d]{3}"))     
                {
                    continueInput = false;
                    
                }
                else
                {
                    System.out.println("Invalid CVV");
                    System.out.println("Please enter the right CVV.");
                    continueInput = true;            
                }
            }while(continueInput);
        
        return CVV;
    }
    
    public static void displayPayment(int paymentChoice, Cash[] cash, CreditCard[] creditCard, int cusArrSize)
    {
        switch(paymentChoice)
        {
            case 1:
                System.out.println((cash[cusArrSize-1]).toString());
                break;
            case 2:
                System.out.println((creditCard[cusArrSize-1]).toString());
        }
    }

           
    
    public static void displayReceipt(orderItem[] orderItem, order[] order, String[] displayOrderItem, int numItem,
                                     int paymentChoice, Cash[] cash, CreditCard[] creditCard, int cusArrSize)
    {
        System.out.println(" ");
        System.out.println(" ");
        line();
        System.out.println("");
        System.out.print(" -------------   \n"); 
        System.out.print("| WEEDONALD'S |  \n");
        System.out.print(" -------------   \n");
        System.out.print(" [ Receipt ]");
        System.out.println(" ");
        
        System.out.println(order[cusArrSize-1].displayResit());

        displayPayment(paymentChoice, cash, creditCard, cusArrSize);
        System.out.println(" ");        
        System.out.println("Thank you");
        System.out.println("See you again !");
        System.out.println("");
        line();
    }
    
    
    //Staff    
    public static staff[] storeStaffData(){
        
        staff[] Staff = {
            new staff("Nicholas Sia", "1901234" , "SIA01234"), 
            new staff("Joey Hee","1902468", "HEE02468"), 
            new staff("Nelson Tan", "1903579", "TAN03579"),
            new staff("Jason Ong", "1903927", "ONG03927"),
            new staff("Shen Lee", "1901248", "LEE01248")
        };           
        
        return Staff;
    }
    
     //Continue Login
    public static char continueLogin()
    {      
        Scanner input = new Scanner(System.in);
        char respond = 'Y';
        boolean continueInput = true;
        
        
        do{
            try
            {
                System.out.println("");
                System.out.print("Do you want to continue login? ");
                respond = input.next("[y|Y|n|N]").charAt(0);
                respond = Character.toUpperCase(respond);
                
                continueInput = false;
                
            }catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please enter again.");
                System.out.println("");
                continueInput = true;               
                input.nextLine();
            }
            
        }while(continueInput);

        System.out.println("");
        
        return respond;
    }
    
    public static boolean viewReport(){

        //to login to view report
        boolean viewReport = true; 
        boolean tryAgain = true;
        char resp = 'Y';  
        
        Scanner input = new Scanner(System.in);
                
        do{
            try{           
                System.out.println("");
                System.out.print("Access Report? (Yes[Y]/No[N])   :");
                resp = input.next("[y|Y|n|N]").charAt(0);
                resp = Character.toUpperCase(resp);
                
                tryAgain = false;
              
                }catch (InputMismatchException e)
                {
                    System.out.println("Invalid Input.");
                    System.out.println("Please enter again.");
                    tryAgain = true;
                    input.nextLine();
                }
        }while(tryAgain);
        
        if(resp == 'Y')
            viewReport = true;
        else if (resp == 'N')
            viewReport = false;

        return viewReport;
        
    }
    
    //Ask user is staff or not
    public static boolean verifyStaff(){
        
        boolean continueInput = true;
        char resp = 'Y';
      
        Scanner input = new Scanner(System.in);
              
        do{
            try{
                System.out.println("");
                System.out.print("Are You a Staff? (Yes[Y]/No[N])    :");
                resp = input.next("[y|Y|n|N]").charAt(0);
                resp = Character.toUpperCase(resp);
                
                continueInput = false;
                
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid Input");
                System.out.println("Please Enter (Yes[Y]/No[N])" );
                continueInput = true;
                input.nextLine();
            }
            
        }while(continueInput); 
        
        if (resp == 'Y')
            return true;
        else 
            return false;
        
    } 
    
    public static boolean login(staff[] Staff){
               
        boolean tryAgain = true; 
        boolean continueInput = true;
        int validID = 0;  //ValidID = 0 (Valid ID), ValidID = 1 (Invalid ID)
        boolean validLogin = true;  //check password = id
        boolean login = true; 
        String staffID = null;
        String password = null;
     
        char respond = 'Y';
        Scanner input = new Scanner(System.in);
                
        do
        {
            do
            {
                System.out.print("Staff ID: ");
                staffID = input.nextLine().trim();
                
                //Check id with password
                validID = staff.validateStaffID(Staff, staffID);
                
                if (validID != -1)
                {
                    tryAgain = true;
                    continueInput = false; 
                    
                }
                else if (validID == -1)
                {
                     System.out.println("Invalid Staff ID");
                     respond = continueLogin();
                     
                     if (respond == 'Y')
                     {
                         continueInput = true;
                         tryAgain = true;  //try to login one more time
                     }
                     else if (respond == 'N')
                     {
                         continueInput = false; 
                         tryAgain = false;
                         break;
                     }
                }
            }while(continueInput);
            
            //tryAgain 
            if(tryAgain == false)
            {
                login = false;
                continueInput = false;
                break;
            }
            else
            {
                //If pattern of staff id correct
                System.out.print("Password: ");
                password = input.nextLine().trim();
                
                validLogin = staff.validatePassword(validID, Staff, password);
               
                if (validLogin == false)
                {
                    System.out.println("Invalid Password. Please Try Again.\n");
                    respond = continueLogin();
                    if (respond == 'Y')
                        continueInput = true;
                    else if (respond == 'N')
                    {
                        continueInput = false;
                        login = false;
                        break;
                    }
                }
                else 
                {
                    System.out.println("\n------Logged in Successfully.------\n");
                    login = true;
                    continueInput = false;
                    
                }  
            }
        }while(continueInput);
        

        return login;
    }
    
    public static void displayReport(salesReport report,int cusArrSize, String[] paymentMethod){
            
                
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:MM");
        Date dateobj = new Date();
       
         //display report title and date
        System.out.println("\n\n");
        line();
        System.out.println("---------- WEEDONALD'S SALES REPORT AS AT " + df.format(dateobj)+ " ----------");
        line();
            
        System.out.printf("%-10s | %-20s | %-20s | %-20s | %-20s|%-10s|\n",  "Order No.", "Customer Name", "Member Status", "Discount Applied", "Payment Method", "Sub Total");
        straightLine();
        
        //totalItem = order[cusArrSize-1].getTotalItem();
       
        //display sales 
        for(int i = 0; i< cusArrSize; i++)
        {
            System.out.print(report.toString(i));
            System.out.printf(" | %-20s|", paymentMethod[i]);
            System.out.println(report.displaySubTotal(i));
            
        }

        System.out.printf("%-102s %-10s\n", "", "----------");
        System.out.println(report.printTotalSales(cusArrSize));
        System.out.printf("%-102s %-10s\n", "", "----------");
        line();
        System.out.println("");
        
        
       
        
    }
   
    
    
    
    
    
    
    
    
    
    
    //Design
    public static void line()
    {
        for(int i=0; i < 200; i ++)
        {
            System.out.print("*");
        }
        System.out.println("");
    }
    
    public static void straightLine()
    {
        for(int i=0; i < 200; i ++)
        {
            System.out.print("-");
        }
        System.out.println("");
    }
    
    public static void equalLine()
    {
        System.out.printf("%-140s %-10s\n", "", "----------");
    }
    
        // TODO code application logic here
    
}


