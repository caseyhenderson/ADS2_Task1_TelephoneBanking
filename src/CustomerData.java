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
//            quickSort(DBarr, 0, DBarr.length-1);
//            for (int X=0; X< DBarr.length-1; X++)
//            {
//                System.out.println(DBarr[X].getID());
//            }


        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public CustomerQueue getDBQueue()
    {
//        DBQueue.quickSort(DBQueue.getArr(), 0, DBQueue.getArr().length-1);
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
    public void quickSort(Customer[] arr, int begin, int end)
    {
        int i = begin;
        int j = end;

        if(j-i >=1)
        {
            String pivot = arr[i].getID();
            while (j>i)
            {
                if(arr[i] != null)
                {
                    while(arr[i].getID().compareTo(pivot) <=0 && i< end && j>1)
                    {
                        i++;
                    }
                    if(arr[j]!=null)
                    {
                        while(arr[j].getID().compareTo(pivot)>=0 && j>begin && j>=1)
                        {
                            j--;
                        }
                        if(j>i)
                        {
                            swap(arr, i, j);
                        }

                    }
                }
//                while(arr[j].getID().compareTo(pivot)>=0 && j>begin && j>=1)
//                {
//                    j--;
//                }
//                if(j>i)
//                {
//                    swap(arr, i, j);
//                }
            }
            swap(arr, begin, j);
            quickSort(arr, begin, j-1);
            quickSort(arr, j+1, end);
        }
    }
    public void swap(Customer[] arr, int i, int j)
    {
        String temp = arr[i].getID();
        arr[i].setID(arr[j].getID());
        arr[j].setID(temp);
    }


//    public void quickSort(Customer[] arr, int begin, int end) {
//        if (begin < end) {
//
//            int partIndex = partition(arr, begin, end);
//            quickSort(arr, begin, partIndex - 1);
//            quickSort(arr, partIndex + 1, end);
//        }
//        else
//        {
//            System.out.println ("An interesting error.");
//        }
//    }
//
//    private int partition(Customer[] arr, int begin, int end) {
//        int pivot = arr[end].getRequestNo();
//        int i = (begin-1);
//
//        for (int j = begin; j<end; j++)
//        {
//            if (arr[j].getRequestNo()<=pivot)
//            {
//                i++;
//                int swapTemp = arr[i].getRequestNo();
//                arr[i].setRequestNo(arr[j].getRequestNo());
//                arr[j].setRequestNo(swapTemp);
//            }
//        }
//        int swapTemp = arr[i+1].getRequestNo();
//        arr[i+1].setRequestNo(arr[end].getRequestNo());
//        arr[end].setRequestNo(swapTemp);
//        return i+1;
//    }


}
