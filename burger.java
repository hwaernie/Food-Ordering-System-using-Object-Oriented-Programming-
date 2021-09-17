package foodorderingsystem;
/**
 *
 * @author Dell
 */
public class burger extends alaCarte{
    
    private String size;
    
    public burger(){}
        
    public burger(String size)
    {
        this.size = size;
    }
    
    public burger(String id, String name, String desc, double price, String size) 
    {
        super(id, name, desc, price);
        
        this.size = size;        
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    //size = medium, large
    public String getSize(){
        return size;
    }
    
    @Override
    public String getSpecial()
    {
        return size;
    }
    
    
    @Override 
    public String toString()
    {
        return String.format("%-10s | %-20s | %-20s | %-40s | RM%-5.2f\n", super.getId(), super.getName(), size, 
                super.getDesc(), super.getPrice() );
    }
    
    /*
    id|name|size|desc|price    
    */
    
    
}
