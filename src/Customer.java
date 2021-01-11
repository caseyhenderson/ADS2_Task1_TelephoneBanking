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
        balance = balance *100;
        balance = Math.round(balance);
        balance = balance/100;
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
    public void setRequestNo(int requestNo)
    {
        this.request.request = requestNo;
    }

    public void setRequest(CustomerRequest request) {
        this.request = request;
    }
}
