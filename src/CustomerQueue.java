public class CustomerQueue {
    //    private double body[];
//    private String IDBody[];
    private Customer arr[];
    int front = 0;
    int rear = 1;
    private int capacity = 100;
    private int size = 0;
    private int count = 0;


    // Default Constructor
    CustomerQueue() {
//        this.size = size;
        arr = new Customer[this.capacity];
//        body = new double[size];
//        IDBody = new String[size];
//        front = 0;
//        rear = -1;
//        count = 0;
//
    }


    //    public String[] getIDBody() {
//        return this.IDBody;
//    }
//
//    public double[] getBody()
//    {
//        return this.body;
//    }
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


//    }
    // add printing all the queue
    // a lot of these methods will need shifting to Main potentially????

//    public void removeFromQueue()
//    {
//        if (checkEmpty())// same as checkEmpty() == true
//        {
//            System.out.println("The queue is empty and therefore nothing can be removed from it");
//            // Preventing Underflow
//            System.exit(1);
//            // this should do what break does
//        }
//        System.out.println("Removing" + arr[front] + "");
//        front = (front + 1) % size; // modulo stops it going over the new size (now thing has been removed)
//        count--;
//    }
    // this is basically a 'pop' from front of queue w/o display
    // for specific remove gonna need search of array

    // will need to change 'double' to whatever customer is actually stored as, fine now though
    // removeFromQueue only takes front
//
//    public void removeSpecific(String ID)
//    {
//        // yes this could be improved: sort/search
//        // proof of concept RN
//        // this is exactly why we need a LL approach
//        // unless we assume that customer at this point MUST be first in queue otherwise they wouldn't get served.
//        // in which case why do they have to put their ID in
//        for (int i = 0; i<size; i++)
//        {
//            if (IDBody[i] == ID)
//            {
//                // this is going to be a 'clear' operation. in time LL approach makes more sense
//                // however because it has to be a 'queue' we have to do it this way ffs
//                IDBody[i] = "closed";
//                body[i] = 0;
//                // by marking as closed all closed accounts can in theory be removed for more space
//                // the obvious and better way is to use an LL to reallocate nodes.
//            }
//
//        }
//    }
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


/*         rear = (rear + 1)% size;
         arr[rear] = newCustomer;
         count++*/
        // working, now for removal
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
//        front = (front + 1) % size; // modulo stops it going over the new size (now thing has been removed)
//        count--;
//        size--;


    public CustomerQueue popCQ(Customer removeCustomer) {
        // pops customer from queue (removes first one) and displays
        if (checkEmpty()) {
            System.out.println("The queue is empty and therefore nothing can be removed from it.");
            System.exit(1);
        }
        System.out.println("Removing" + removeCustomer.getID() + ", " + removeCustomer.getBalance());
        Customer[] newArr = new Customer[arr.length - 1];
//        Customer testCustomer = new Customer();
//        testCustomer.setBalance(1);
//        testCustomer.setID("testy testy boi");
        CustomerQueue newQueue = new CustomerQueue();
//        for (int i = 0, j = 0; i<newQueue.queueSize(); i++)
//        {
////            if (!arr[i].getID().equals(removeCustomer.getID()))
////            {
////                newQueue.addCQ(arr[i]);
////                System.out.println(arr[i]);
////                // returns a completely blank queue
////                // presumably is removing EVERYTHING
////                // possibly because it all equals what it thinks the ID is??
////                // or because nothing gets added to CQ??
////                // or becouse arr[i] is blank?
////            }
////            else
////            {
////                newQueue.addCQ(testCustomer);
////
////            }
////        }
        for (int i = 0, j = 0; i < queueSize(); i++) {
            if (arr[i].getID() != removeCustomer.getID()) {
                newArr[j++] = arr[i];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != null) {
                newQueue.addCQ(newArr[i]);
            }
        }
        // this way should be able to be adapted for specifics too!
        // still not quite LL but ah well, supposed to be queue

        front = (front + 1) % size; // modulo stops it going over the new size (now thing has been removed)
        count--;
        return newQueue;

    }
    // take the result of this and load it in

    // this is basically a 'pop' from front of queue w/o display
    // for specific remove gonna need search of array

    // will need to change 'double' to whatever customer is actually stored as, fine now though
    // removeFromQueue only takes front
    public CustomerQueue removeSpecific(Customer removeCustomer) {
        if (checkEmpty()) {
            System.out.println("The queue is empty and therefore nothing can be removed from it.");
            System.exit(1);
        }
        System.out.println("Removing" + removeCustomer.getID() + ", " + removeCustomer.getBalance());
        Customer[] newArr = new Customer[arr.length - 1];
        CustomerQueue newQueue = new CustomerQueue();
        for (int i = 0, j = 0; i < queueSize(); i++) {
            if (arr[i].getID() != removeCustomer.getID()) {
                newArr[j++] = arr[i];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != null) {
                newQueue.addCQ(newArr[i]);
            }
        }
        front = (front + 1) % size; // modulo stops it going over the new size (now thing has been removed)
        count--;
        return newQueue;

    }

//    public void addToQueue(double item, String ID)
//    {
//        if (checkFull())
//        {
//            System.out.println("The queue is full and therefore nothing can be added to it.");
//            // preventing Overflow
//            System.exit(1);
//        }
//        System.out.println("Inserting " + item +", " + ID);
//        rear = (rear + 1)% size;
//        // PROBLEM IS HERE WITH WHATEVER'S CAUSING US TO BE OUT OF BOUNDS
//        // fixed now by altering size and qcap, size is parameter QCap is defined value
//        body[rear] = item;
//        IDBody[rear] = ID;
//        count++;
//
//    }

//    public void addToIDQueue(String ID)
//    {
//        if (checkFull())
//        {
//            System.out.println("The queue is full and therefore nothing can be added to it.");
//            // preventing Overflow
//            System.exit(1);
//        }
//        System.out.println("Inserting " + ID);
//        rear = (rear + 1)% QCap;
//        IDBody[rear] = ID;
//        count++;
//
//    }

    public Customer getFront() {
        if (checkEmpty()) {
            System.out.println("Empty so nothing at front");
            //underflow
            System.exit(1);
        }
        return arr[front];

    }


    public int queueSize() {
        return count;
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

//            System.out.println("Item: " + body[i] );
//            System.out.println("ID: " + IDBody[i]);
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


//        int pivot;
//
//        if (arr[end]!=null) {
//            pivot = arr[end].getRequestNo();
//            int i = 0;
//
//            for (int j = begin; j < end; j++) {
//                int tempInt = 0;
//                if (this.arr[j] != null) {
//                    tempInt = this.arr[j].getRequestNo();
//                }
//
//                if (tempInt <= pivot && this.arr[i] != null && this.arr[j] != null) {
//                    i++;
//                    int swapTemp = this.arr[i].getRequestNo();
//                    arr[i].setRequestNo(this.arr[j].getRequestNo());
//                    // arr[i] = arr[j]
//                    arr[j].setRequestNo(swapTemp);
//                    // arr[j] = swapTemp
//                }
//            }
//            int swapTemp = this.arr[i + 1].getRequestNo();
//            Customer templInt;
//
//            if (this.arr[end] != null) {
//                templInt = this.arr[end];
//                this.arr[i + 1].setRequestNo(this.arr[end].getRequestNo());
//                templInt.setRequestNo(swapTemp);
//            }
//            return i + 1;
//        }
//
////        this.arr[end].setRequestNo(swapTemp);
//        else
//        {
//            return 0;
//        }
//
//
//    }


}
