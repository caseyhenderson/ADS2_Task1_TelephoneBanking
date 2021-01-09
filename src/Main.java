import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The telephone Banking System is an assignment for Algorithms and Data Structure 2
 * @author Jing Wang @ SHU
 * @verison 1.1
 */

public class Main {
    private static int rear;
    private static int front;
    private static int count;

    //State Machine parameters
    public enum State{START, STOP, WELCOME, LIST_ALL, PUSH, POP, TASK, REMOVE, NEW, SAVE, WITHDRAW, DISPLAY};
    public static State currentState = State.START;

    //Global variables
    public static Scanner inputOrder = new Scanner(System.in);
    public static Scanner inputID = new Scanner(System.in);
    public static double balance = (double) 0.00;
    public static CustomerQueue queue;

    public static CustomerData userData;

    public static CustomerRequest currentRequest = new CustomerRequest();



    public static void main(String[] args) throws FileNotFoundException {
        // load the queue at some point here
        while (currentState != State.STOP){
            switch (currentState){
                default: break;
                case START:     state_start();
                    break;
                case WELCOME:   state_welcome();
                    break;
                case LIST_ALL:  state_Q_listAll();
                    break;
                case PUSH:      state_Q_push();
                    break;
                case POP:       state_Q_pop();
                    break;
                case TASK:      state_task();
                    break;
                case REMOVE:    state_H_remove();
                    break;
                case NEW:       state_H_new();
                    break;
                case SAVE:      state_H_saveMoney();
                    break;
                case WITHDRAW:  state_H_reduceMoney();
                    break;
                case DISPLAY:   state_H_display();
                    break;
            }
        }

        clear();
    }

    private static void state_start() throws FileNotFoundException {
        System.out.println("System Initialization...");
        System.out.println("Load customer database...");
        userData = new CustomerData();
        queue = userData.getDBQueue();
//        String[] qIDs = queue.getIDBody();
//        double[] Balances = queue.getBody();

        System.out.println("Initializing queue...");

        // 110 to demonstrate push/pop. this implementation should be deployed from CustomerData and will be when possible.
//        try (Scanner scanner = new Scanner(new File("BankUserDataset100.csv"))) {
//
//
//
//            scanner.useDelimiter("\r\n");
//
//            while(scanner.hasNext()){
//                String[] currentLine = scanner.next().split(",");
//                String currentID = currentLine[0];
//                double currentBalance = double.parsedouble(currentLine[1]);
//                //System.out.println("Loading..."+currentID+"\t£"+currentBalance);
//                //Load currentID and currentBalance into your underlying data structure
//                //one by one inside the loop. The loop will stop after reading the
//                //last data point in the file.
//                queue.addToQueue(currentBalance, currentID);
//                // obvs need to come back to this but should run for now
//
//            }
//            queue.printItems();
//        }
        // can do request here if needs be
        currentState = State.WELCOME;
    }

    private static void state_welcome() {
        System.out.println("\n\n\n==Telephone Banking Control Centre==");
        System.out.println("Choose the index number from following options:\n1. Pop the next customer\n2. Push a new customer to queue\n3. Check current queue\n4. Random task \n5. Quit.");
        // can alter this to a separate 'TASK' thing as OG intended but leave for now.
        if (inputOrder.hasNext("1")) currentState = State.POP;
        if (inputOrder.hasNext("2")) currentState = State.PUSH;
        if (inputOrder.hasNext("3")) currentState = State.LIST_ALL;
        if (inputOrder.hasNext("4")) currentState = State.TASK;
        if (inputOrder.hasNext("5")) currentState = State.STOP;
        inputOrder.next();
    }

    private static void state_Q_listAll() {
//        currentRequest.newRequest();
        queue.printItems();
        // NOT INITIALISED HERE SO WE JUST GET ZEROES, CHECK.
        // ALSO ON INITIAL PRINT IT DOESN'T PRINT THE OTHER ONE
       /* System.err.println("'List all the customers' function is not implemented");*///Delete this statement after the function is fully developed
        currentState = State.WELCOME;
    }

    private static void state_Q_push() {
//        System.err.println("'Push a new customer to the queue' function is not implemented");//Delete this statement after the function is fully developed
        System.out.println("Input new customer ID to push new customer to queue");
        String iID = inputID.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setBalance(balance);
        newCustomer.setID(iID);
        CustomerRequest newRequest = new CustomerRequest();
        newCustomer.setRequest(newRequest);
        queue.addCQ(newCustomer);
//        if (queue.checkFull())
//            {
//                System.out.println("The queue is full and therefore nothing can be added to it.");
//                // preventing Overflow
//                System.exit(1);
//            }
//            System.out.println("Inserting " + balance +", " + iiD);
//            rear = (rear + 1)% QCap;
//            // PROBLEM IS HERE WITH WHATEVER'S CAUSING US TO BE OUT OF BOUNDS
//            // fixed now by altering size and qcap, size is parameter QCap is defined value
//            queue[rear] = iID;
//            queue.IDBody[rear] = ID;
//            count++;
        // this should just be simple add to back of queue
        // was previously WELCOME
        currentState = State.WELCOME;
    }
    // leave weird random selection as is but also add option to choose the requests.
    // now implement that functionality

    private static void state_Q_pop() {
        // get front and remove from front, not back
        // getFront()
        // and then remove front
        // possible change to LL needed
//        System.err.println("'Pop a new customer from the queue' function is not implemented");//Delete this statement after the function is fully developed
//        System.out.println("Remove from Queue");
        System.out.println("Input ID to remove");
        String toRemove = inputID.nextLine();
        Customer removeCustomer;
        removeCustomer = queue.getFront();
        queue.popCQ(removeCustomer);
        queue = queue.popCQ(removeCustomer);
        // no methods of removal/replacement are working rn
        // seemingly this is what isn't working as add to queue DOES
//        currentRequest.newRequest();//replace this statement by your Pop function
        // implement the pop display here???
        currentState = State.TASK;//Uncomment this statement after the Pop function is fully developed

    }

    private static void state_task() {
        System.out.print("This customer wants to ");
        currentRequest.newRequest();
        switch (currentRequest.request){
            default: break;
            //this is always case 0?
            case 0: System.out.println("open a new account");
                System.out.println("Please input a new account ID:");
                // and starting balance?????? amount to change. how to implement???
                currentRequest.id = inputID.nextLine();
                Customer newCustomer = new Customer();
                newCustomer.setID(currentRequest.id);
                newCustomer.setBalance(balance);// needs fixing to set balance
//                newCustomer.setRequest(currentRequest);
                // request can also go here if needs be
                // presumably this gets added to the queue, in specifics. should also be initialised with a balance
                // can we add to file? idk? do we need to
                queue.addCQ(newCustomer);
                currentState = State.NEW;
                break;
            case 1: System.out.println("close the account");
                System.out.println("Please input the account ID:");
                // close SPECIFIC account, need to alter the remove function to take ID and search for
                currentRequest.id = inputID.nextLine();
                Customer closeCustomer = new Customer();
                closeCustomer.setID(currentRequest.id);
//                closeCustomer.setRequest(currentRequest);
                queue.popCQ(closeCustomer);
                // in theory this might work without specific method but we will see
                // alter here for specifics
//                queue.popCQ(queue.getFront());
                currentState = State.REMOVE;
                break;
            case 2: System.out.println("check balance");
                // search the queue for the ID
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                Customer toCheck = queue.getFront();
                // this would only work for front if it does work
                double cBalance = toCheck.getBalance();
                System.out.println("Balance is " + cBalance);
                currentState = State.DISPLAY;
                break;
            case 3: System.out.println("save £"+currentRequest.amountToChange);
                System.out.println("Please input the account ID:");
                // likewise, search and add the money to the corresponding balance
                // binary search on q? would need to be sorted.
                currentRequest.id = inputID.nextLine();
                Customer toAdd = queue.getFront();
                double amount = currentRequest.amountToChange;
                toAdd.setBalance(toAdd.getBalance()+amount);
                double newBalance = toAdd.getBalance()+amount;
                System.out.println(toAdd.getBalance());
                currentState = State.SAVE;
                break;
            case 4: System.out.println("withdraw £"+currentRequest.amountToChange);
                System.out.println("Please input the account ID:");
                // search and subtract
                currentRequest.id = inputID.nextLine();
                Customer toSub = queue.getFront();
                double withdrawal = currentRequest.amountToChange;
                if (toSub.getBalance() > withdrawal)
                {
                    toSub.setBalance(toSub.getBalance()-withdrawal);
                    double newValue = toSub.getBalance() - withdrawal;
                    System.out.println(newValue);
                }
                else
                {
                    System.out.println("Not enough money in account to withdraw.");
                }
                //guard this

                currentState = State.WITHDRAW;
                break;

        }
    }

    private static void state_H_remove() {
        currentState = State.WELCOME;
    }

    private static void state_H_new() {
        currentState = State.WELCOME;
    }

    private static void state_H_saveMoney() {
        currentState =  State.DISPLAY;
    }

    private static void state_H_reduceMoney() {
        currentState = State.DISPLAY;

    }

    private static void state_H_display() {
//        System.err.println("'Show Balance' function is not implemented");//Delete this statement after the function is fully developed
        currentState = State.WELCOME;
    }

    private static void clear() {
        System.out.println("Clearing data...");
        //Deleted by the Garbage collector.
        queue = null;
        userData = null;
        System.out.println("===See you next time===");
    }

}
