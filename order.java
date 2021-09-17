

package foodorderingsystem;

public class order {
                
    private Customer customer;
    private orderItem[] orderItem;
    private Payment payment;
    private double sumTotal; //Total price for item 
    //this use for report(?
    private  int orderNum ;
    private final double MEMBER_DIS = 0.1;
    
    
    public order(){};
    
    public order(int orderNum, orderItem[] orderItem, double sumTotal, Customer customer)
    {
        this.orderNum = orderNum;
        this.orderItem = orderItem;
        this.sumTotal = sumTotal;
        this.customer = customer;
    }
    

    
    public order(orderItem[] orderItem, Payment payment, double sumTotal, Customer customer)
    {
        this.orderItem = orderItem;
        this.payment = payment;
        this.sumTotal = sumTotal;
        this.customer = customer;
    }

    public double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(double sumTotal) {
        this.sumTotal = sumTotal;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    public orderItem[] getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(orderItem[] orderItem) {
        this.orderItem = orderItem;
    }

    public Payment getPayment()
    {
        return payment;
    }
    
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    //Get customer info and Print it out
    public String printCustomer()
    {
        return String.format("%-20s: %-30s\n%-20s: %-30s\n%-20s: %-30s\n%-20s: %-10s\n", "Customer Name", 
                customer.getName(), "Member Status", customer.isMemberStatus(),
                "Date/Time", customer.getCurrentDate(), "OrderNo", orderNum);        
    }
    
    //get total amount that need to  be deduct
    public  double getTotalDis()
    {
        if (customer.isMemberStatus() == true)
            return sumTotal*MEMBER_DIS;
        else
            return 0;
    }
    

    public double getGrandTotal() {
        
        return sumTotal-getTotalDis();
        
    }
        
    //Display
    public  String printDis()
    {
        return String.format("%-140s RM%-8.2f", "Member Discount: " , getTotalDis());
    
    }
    
    @Override
    public String toString()
    {
        return String.format("%-140s RM%-8.2f", "Total Price: " , getGrandTotal());

    }
    
    //For resit
    public String displayResit()
    {
        return String.format("%-100s\n%-20s: %-10s\n%-20s: RM%-5.2f",customer.printCustomer(), "Order Num", getOrderNum(), "Total Price: " , getGrandTotal());    
    }
    
    
    //For report
    //Get customer info and Print it out
    public String displayOrder()
    {
        return String.format("%-10s | %-20s | %-20s | RM%-18.2f", getOrderNum(), customer.getName(),customer.isMemberStatus(), getTotalDis());    
    }
    
    //orderNo| Customer Name| Member Status| Discount Apllied| Sub Total
    
    public String displayGrandTotal()
    {
        return String.format( "RM%-8.2f |", getGrandTotal());
    }
    
}
