import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerData {

    CustomerData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("BankUserDataset100.csv"))) {

            //Initialise your members here. After this point, you should already
            //have a ready-to-use "database" for user account information
            scanner.useDelimiter("\r\n");
            CustomerQueue BalanceQueue = new CustomerQueue(100);
            while(scanner.hasNext()){
                String[] currentLine = scanner.next().split(",");
                String currentID = currentLine[0];
                float currentBalance = Float.parseFloat(currentLine[1]);
                //System.out.println("Loading..."+currentID+"\t£"+currentBalance);
                //Load currentID and currentBalance into your underlying data structure
                //one by one inside the loop. The loop will stop after reading the
                //last data point in the file.

                BalanceQueue.addToQueue(currentBalance, currentID);
                // obvs need to come back to this but should run for now

            }
            BalanceQueue.printItems();
        }
        catch (FileNotFoundException e){
            System.err.println(e.getLocalizedMessage());
        }
    }


}
