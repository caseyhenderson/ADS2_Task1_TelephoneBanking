import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The telephone Banking System is an assignment for Algorithms and Data Structure 2
 * @author Jing Wang @ SHU
 * @verison 1.1
 */

public class Main {
    //State Machine parameters
    public enum State{START, STOP, WELCOME, LIST_ALL, PUSH, POP, TASK, REMOVE, NEW, SAVE, WITHDRAW, DISPLAY};
    public static State currentState = State.START;

    //Global variables
    public static Scanner inputOrder = new Scanner(System.in);
    public static Scanner inputID = new Scanner(System.in);
    public static String iID = inputID.toString();
    public static float balance = (float) 19.01;
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
            }
        }

        clear();
    }

    private static void state_start() throws FileNotFoundException {
        System.out.println("System Initialization...");
        System.out.println("Load customer database...");
        userData = new CustomerData();
        System.out.println("Initializing queue...");
        queue = new CustomerQueue(100);

        currentState = State.WELCOME;
    }

    private static void state_welcome() {
        System.out.println("\n\n\n==Telephone Banking Control Centre==");
        System.out.println("Choose the index number from following options:\n1. Get the next customer\n2. Queueing a new customer\n3. Check current queue\n4. Quit");
        if (inputOrder.hasNext("1")) currentState = State.POP;
        if (inputOrder.hasNext("2")) currentState = State.PUSH;
        if (inputOrder.hasNext("3")) currentState = State.LIST_ALL;
        if (inputOrder.hasNext("4")) currentState = State.STOP;
        inputOrder.next();
    }

    private static void state_Q_listAll() {

        queue.printItems();
        // NOT INITIALISED HERE SO WE JUST GET ZEROES, CHECK.
        // ALSO ON INITIAL PRINT IT DOESN'T PRINT THE OTHER ONE
       /* System.err.println("'List all the customers' function is not implemented");*///Delete this statement after the function is fully developed
        currentState = State.WELCOME;
    }

    private static void state_Q_push() {
        System.err.println("'Push a new customer to the queue' function is not implemented");//Delete this statement after the function is fully developed
        CustomerRequest newRequest = new CustomerRequest();
        queue.addToQueue(balance, iID);
        currentState = State.WELCOME;
    }

    private static void state_Q_pop() {
        System.err.println("'Pop a new customer from the queue' function is not implemented");//Delete this statement after the function is fully developed
        currentRequest.newRequest();//replace this statement by your Pop function
        //currentState = State.TASK;//Uncomment this statement after the Pop function is fully developed
        currentState = State.WELCOME;//Delete this statement after the function is fully developed

    }

    private static void state_task() {
        System.out.print("This customer wants to ");
        switch (currentRequest.request){
            default: break;

            case 0: System.out.println("open a new account");
                System.out.println("Please input a new account ID:");
                currentRequest.id = inputID.nextLine();
                currentState = State.NEW;
                break;
            case 1: System.out.println("close the account");
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                currentState = State.REMOVE;
                break;
            case 2: System.out.println("check balance");
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                currentState = State.DISPLAY;
                break;
            case 3: System.out.println("save £"+currentRequest.amountToChange);
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                currentState = State.SAVE;
                break;
            case 4: System.out.println("withdraw £"+currentRequest.amountToChange);
                System.out.println("Please input the account ID:");
                currentRequest.id = inputID.nextLine();
                currentState = State.WITHDRAW;
                break;

        }
    }

    private static void state_H_remove() {
        System.err.println("'Close Account' function is not implemented");//Delete this statement after the function is fully developed
        currentState = State.WELCOME;
    }

    private static void state_H_new() {
        System.err.println("'Open New Account' function is not implemented");//Delete this statement after the function is fully developed
        currentState = State.WELCOME;
    }

    private static void state_H_saveMoney() {
        System.err.println("'Save Money' function is not implemented");//Delete this statement after the function is fully developed
        currentState =  State.DISPLAY;
    }

    private static void state_H_reduceMoney() {
        System.err.println("'Withdraw Money' function is not implemented");//Delete this statement after the function is fully developed
        currentState = State.DISPLAY;

    }

    private static void state_H_display() {
        System.err.println("'Show Balance' function is not implemented");//Delete this statement after the function is fully developed
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
