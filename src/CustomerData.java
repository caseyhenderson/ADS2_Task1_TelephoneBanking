import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerData {

    private CustomerQueue DBQueue = new CustomerQueue();
    CustomerQueue SQueue = new CustomerQueue();

    private Customer [] DBarr = new Customer[10001];


    CustomerData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("BankUserDataset10K.csv"))) {

            //Initialise your members here. After this point, you should already
            //have a ready-to-use "database" for user account information
//            final CustomerQueue DBQueue = new CustomerQueue(100);
//


            // issue of ACCESS: how do we access the individual iDs, for purpose of checking the balance??
            // there needs to be correspondence: ID would be same 'entry' as balance but still needs to be separate
            // 2d array


            // for sorting purposes would probably put them into something before queue

            scanner.useDelimiter("\r\n");
            int i = 0;
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
                DBarr[i] = newCustomer;
                i++;
                DBQueue.addCQ(newCustomer);
//                DBQueue.addToQueue(currentBalance, currentID);
                // obvs need to come back to this but should run for now

            }

//            DBQueue.printItems();

            InsertionSort(DBarr);
            Customer sorted[] = InsertionSort(DBarr);

            for (int X = 0; X< sorted.length-1; X++)
            {
                System.out.println(sorted[X].getID());
                SQueue.addCQ(sorted[X]);
            }


        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public CustomerQueue getDBQueue()
    {
        return DBQueue;
    }
    public CustomerQueue getSQueue()
    {
        return SQueue;
    }
    public Customer[] InsertionSort(Customer[] arr)
    {
        int n = arr.length;
        String temp;

        for (int i = 0; i<n; ++i)
        {
            if(arr[i] != null)
            {
                for(int j = 0; j<n; j++)
                {
                    if(arr[j] != null)
                    {
                        if(arr[i].getID().compareToIgnoreCase(arr[j].getID())<0)
                        {
                            temp = arr[i].getID();
                            arr[i].setID(arr[j].getID());
                            arr[j].setID(temp);
                        }

                    }

                }

            }

        }
        return arr;
    }






}
