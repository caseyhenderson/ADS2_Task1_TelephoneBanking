import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerData {

    final private CustomerQueue DBQueue = new CustomerQueue();

//    private Customer [] DBarr = new Customer[10001];
    // was used as part of binary search implementation


    CustomerData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("BankUserDataset10K.csv"))) {

            //Initialise your members here. After this point, you should already
            //have a ready-to-use "database" for user account information
//            final CustomerQueue DBQueue = new CustomerQueue(100);
//



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
//                DBarr[i] = newCustomer;
//                i++;
                DBQueue.pushQ(newCustomer);
//             DBQueue.addToQueue(currentBalance, currentID);

            }
            DBQueue.printItems();
//            BinarySearch(DBarr, "KE72150", 0, DBarr.length-1 );
//            int result = BinarySearch(DBarr, "KE72150", 0, DBarr.length-1 );
//            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public CustomerQueue getDBQueue()
    {
        return DBQueue;
    }
//    public Customer[] getDBarr()
//    {
//        // was used for binary search implementation
//        return DBarr;
//    }












}
