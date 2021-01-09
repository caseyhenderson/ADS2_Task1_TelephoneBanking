public class Customer {
    private String ID;
    private double balance;
    private CustomerRequest request;


    public String getID()
    {
        return this.ID;
    }
    public void setID(String newID)
    {
        ID = newID;
    }

    public double getBalance()
    {
        return this.balance;
    }
    public void setBalance(double newBalance)
    {
        balance = newBalance;
    }
    public CustomerRequest getRequest()
    {
        return this.request;
    }
    public int getRequestNo()
    {
        return this.request.request;
    }

    public void setRequest(CustomerRequest request) {
        this.request = request;
    }
}
