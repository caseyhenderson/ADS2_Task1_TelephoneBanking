//import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The telephone Banking System is an assignment for Algorithms and Data Structure 2
 * @author Jing Wang @ SHU, Casey Henderson (assignment built on provided skeleton code)
 * @version 1.1
 */

public class Main {


    //State Machine parameters
    public enum State{START, STOP, WELCOME, LIST_ALL, PUSH, POP, TASK, REMOVE, NEW, SAVE, WITHDRAW, DISPLAY, SORT, SEARCH}
    public static State currentState = State.START;

    //Global variables
    public static Scanner inputOrder = new Scanner(System.in);
    public static Scanner inputID = new Scanner(System.in);
    public static double balance = 0.00;
    public static CustomerQueue queue;

    public static CustomerData userData;

    public static CustomerRequest currentRequest = new CustomerRequest();




    public static void main(String[] args) throws FileNotFoundException {

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
                case SORT:     state_Q_sort();
                    break;
                case SEARCH:
                    state_Q_search();
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


        System.out.println("Initializing queue...");


        currentState = State.WELCOME;
    }

    private static void state_welcome() {
        System.out.println("\n\n\n==Telephone Banking Control Centre==");
        System.out.println("Choose the index number from following options:\n1. Pop the next customer\n2. Push a new customer to queue\n3. Check current queue\n4. Random task \n5. Sort queue \n 6. Search queue for ID \n7. Quit");
        if (inputOrder.hasNext("1")) currentState = State.POP;
        if (inputOrder.hasNext("2")) currentState = State.PUSH;
        if (inputOrder.hasNext("3")) currentState = State.LIST_ALL;
        if (inputOrder.hasNext("4")) currentState = State.TASK;
        if(inputOrder.hasNext("5")) currentState = State.SORT;
        if(inputOrder.hasNext("6")) currentState = State.SEARCH;
        if (inputOrder.hasNext("7")) currentState = State.STOP;
        inputOrder.next();
    }

    private static void state_Q_listAll() {
        queue.printItems();
        currentState = State.WELCOME;
    }

    private static void state_Q_push() {
        System.out.println("Input new customer ID to push new customer to queue");
        String iID = inputID.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setBalance(balance); // always initialised at zero
        newCustomer.setID(iID);
        CustomerRequest newRequest = new CustomerRequest();
        newCustomer.setRequest(newRequest);
        queue.pushQ(newCustomer);

        currentState = State.WELCOME;
    }


    private static void state_Q_pop() {

        System.out.println("Input ID to remove");
//        String toRemove = inputID.nextLine();
        Customer removeCustomer;
        removeCustomer = queue.getFront();
        queue.popQ(removeCustomer);
        queue = queue.popQ(removeCustomer);

        currentState = State.WELCOME;

    }
    private static void state_Q_sort()
    {
        System.out.println("Using Insertion Sort to sort the queue alphabetically by customer ID");
        queue.getSortedAsQ();
        System.out.println("Sort complete");
        currentState = State.WELCOME;
    }
    private static void state_Q_search()
    {
        System.out.println("Search the queue to check if ID already used.");
        System.out.println("Input value to search: ");
        String searchVal= inputID.nextLine();
        queue.searchQueue(searchVal);
        System.out.println("Search complete");
        currentState = State.WELCOME;
        // attempted with binary search originally but couldn't fully implement
        //System.out.println("Search the sorted queue with binary search");
//        int first = 0;
//        Customer[] sorted = queue.getSorted();
//        int last = queue.getSorted().length-1;
//        queue.BinarySearch(sorted,searchVal, first, last);
//        int location = queue.BinarySearch(sorted,searchVal, first, last);
//        if (location == -1)
//        {
//            System.out.println("ID not found with binary search");
//        }
//        else
//        {
//            System.out.println("ID found at" + location + " in queue");
//        }
    }

    private static void state_task() {
        System.out.print("This customer wants to ");
        currentRequest.newRequest();
        switch (currentRequest.request){
            default: break;
            case 0: System.out.println("open a new account");
                System.out.println("Please input a new account ID:");
                currentRequest.id = inputID.nextLine();
                if(queue.searchQueue(currentRequest.id))
                {
                    System.out.println("ID already used");
                    currentState = State.NEW;
                    break;
                }
                else {
                    Customer newCustomer = new Customer();
                    newCustomer.setID(currentRequest.id);
                    newCustomer.setBalance(balance);
                    // again always initialised at zero balance - logic is that they add money through deposit
                    newCustomer.setRequest(currentRequest);
                    queue.pushQ(newCustomer);
                    currentState = State.NEW;
                    break;
                }
            case 1: System.out.println("close the account");
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                // takes account id from input
                Customer closeCustomer = new Customer();
                closeCustomer.setID(currentRequest.id);
                queue.popQ(closeCustomer);
                // takes customer to close account for, removes it from head of queue
                queue.popQ(queue.getFront());
                currentState = State.REMOVE;
                break;
            case 2: System.out.println("check balance");
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                Customer toCheck = queue.getFront();
                // similar logic, checks balance for front of queue
                double cBalance = toCheck.getBalance();
                System.out.println("Your balance is " + cBalance);
                currentState = State.DISPLAY;
                break;
            case 3:
                double deposit = currentRequest.amountToChange;
                System.out.println("deposit £" + String.format("%.2f", deposit));
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                Customer toAdd = queue.getFront();
                toAdd.setBalance(toAdd.getBalance()+deposit);
                // use of set and get to retrieve and alter balance for customer at front of queue
                System.out.println("Depositing £" + String.format("%.2f", deposit));
                System.out.println("New balance is " + toAdd.getBalance());
                currentState = State.SAVE;
                break;
            case 4:
                double withdrawal = currentRequest.amountToChange;
                System.out.println("withdraw £"+String.format("%.2f", withdrawal));
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                Customer toSub = queue.getFront();
                // normal and alternate cases implemented using selection
                if (toSub.getBalance() > withdrawal)
                {
                    toSub.setBalance(toSub.getBalance()-withdrawal);
                    double newValue = toSub.getBalance() - withdrawal;
                    System.out.println("Withdrawing £" + String.format("%.2f", withdrawal));
                    System.out.println("New balance is " + newValue);
                }
                else
                {
                    System.out.println("Not enough money in account to withdraw.");
                }
                //guards against negative values

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
