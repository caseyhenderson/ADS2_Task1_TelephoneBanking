import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerData {
    private String tID;
    private double tBalance;
    private CustomerQueue DBQueue = new CustomerQueue(110);

    CustomerData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("BankUserDataset100.csv"))) {

            //Initialise your members here. After this point, you should already
            //have a ready-to-use "database" for user account information
//            final CustomerQueue DBQueue = new CustomerQueue(100);
//


            // issue of ACCESS: how do we access the individual iDs, for purpose of checking the balance??
            // there needs to be correspondence: ID would be same 'entry' as balance but still needs to be separate
            // 2d array

            scanner.useDelimiter("\r\n");
            while (scanner.hasNext()) {
                String[] currentLine = scanner.next().split(",");
                String currentID = currentLine[0];
                double currentBalance = Double.parseDouble(currentLine[1]);
                CustomerRequest currentRequest = new CustomerRequest();
                //System.out.println("Loading..."+currentID+"\t£"+currentBalance);
                //Load currentID and currentBalance into your underlying data structure
                //one by one inside the loop. The loop will stop after reading the
                //last data point in the file.
                Customer newCustomer = new Customer();
                newCustomer.setBalance(currentBalance);
                newCustomer.setID(currentID);
//                currentRequest.newRequest();
                newCustomer.setRequest(currentRequest);
                DBQueue.addCQ(newCustomer);
//                DBQueue.addToQueue(currentBalance, currentID);
                // obvs need to come back to this but should run for now

            }
            DBQueue.printItems();
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public CustomerQueue getDBQueue()
    {
        return DBQueue;
    }


}
