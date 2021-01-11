public class CustomerQueue {

    private Customer arr[];
    int front = 0;
    int rear = 1;
    private int capacity = 100;
    private int size = 0;
    private int count = 0;


    // Default Constructor
    CustomerQueue() {
        arr = new Customer[this.capacity];

    }


    // check if we need these cause RN they increase space complexity
    public int getCount() {
        return this.count;
    }

    public Customer[] getArr() {
        return this.arr;
    }

    public Customer[] setArr(Customer[] arr) {
        arr = this.arr;
        return arr;
    }

    public void addCQ(Customer newCustomer) {
        if (checkFull()) {
            System.out.println("The queue is full and therefore nothing can be added to it. Increasing capacity now.");
            // prevent overflow
            increaseQCap();

        }
        rear++;
        if (rear >= arr.length && size != arr.length) {
            rear = 0;
        }
        arr[rear] = newCustomer;
        size++;
        System.out.println("Inserting " + newCustomer.getID() + ", " + newCustomer.getBalance() + ", " + newCustomer.getRequestNo());


    }


    public CustomerQueue popQ(Customer removeCustomer) {
        Customer[] newArr = new Customer[arr.length - 1];
        CustomerQueue newQueue = new CustomerQueue();
        if (checkEmpty()) {
            System.out.println("Underflow. Queue is empty and we cannot remove anything from it.");

        }
        front++;
        for (int i = 0, j = 0; i < size; i++) {
            if (arr[i].getID() != removeCustomer.getID()) {
                newArr[j++] = arr[i];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != null) {
                newQueue.addCQ(newArr[i]);
            }
        }
        if (front > arr.length - 1) {
            System.out.println("Removing" + removeCustomer.getID() + ", " + removeCustomer.getBalance() + ", " + removeCustomer.getRequestNo());
            front = 0;

        } else {
            System.out.println("Removing" + removeCustomer.getID() + ", " + removeCustomer.getBalance() + ", " + removeCustomer.getRequestNo());

        }
        size--;
        return newQueue;
        // seems to work now

    }

    // Again may not be needed



    public Customer getFront() {
        if (checkEmpty()) {
            System.out.println("Empty so nothing at front");
            //underflow
            System.exit(1);
        }
        return arr[front];

    }




    public boolean checkEmpty() {
        boolean empty = false;
        if (size == 0) {
            empty = true;
        }
        return empty;
        //  check this
    }

    public boolean checkFull() {
        boolean full = false;
        if (size == arr.length) {
            full = true;
        }
        return full;
    }

    public void increaseQCap() {
        int newCap = this.arr.length * 2;
        Customer[] newArray = new Customer[newCap];
        int tempFront = front;
        int index = -1;
        while (true) {
            newArray[++index] = this.arr[tempFront];
            tempFront++;
            if (tempFront == this.arr.length) {
                tempFront = 0;
            }
            if (size == index + 1) {
                break;
            }

        }
        this.arr = newArray;
        System.out.println("New queue cap: " + this.arr.length);
        this.front = 0;
        this.rear = index;
    }

    public void printItems() // can add queue as parameter, unclear if necessary or not.
    {
        for (int i = 0; i < size; i++) {
            if (this.arr[i] != null) {
                System.out.println("ID: " + arr[i].getID());
                System.out.println("Balance: " + arr[i].getBalance());
                System.out.println("Request: " + arr[i].getRequestNo());
                // something to do with this
                // all request are currently the same, annoyingly
                // problem seemingly solved

            }
            // this won't do it but might be a sticking plaster
            else {
                System.out.println("Empty space");

            }


        }
        // return to check this

    }

    public boolean searchQueue(String searchID) {
        // search algo
        for (int i = 0; i < size; i++) {
            if (this.arr[i] != null) {
                if (this.arr[i].getID().equals(searchID)) {
                    System.out.println("ID already used");
                    return true;
                }

            }
        }
        return false;


    }





}
