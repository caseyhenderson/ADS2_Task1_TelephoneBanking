public class CustomerQueue {

    private Customer[] arr;
    int front = 0;
    int rear = 1;
    private int capacity = 100;
    private int size = 0;

//    CustomerQueue SQueue = new CustomerQueue();

    // Default Constructor
    CustomerQueue() {
        arr = new Customer[this.capacity];

    }


    // previously used helper methods
//    public int getCount() {
//        return this.count;
//    }
//
//    public Customer[] getArr() {
//        return this.arr;
//    }
//
//    public Customer[] setArr(Customer[] arr) {
//        arr = this.arr;
//        return arr;
//    }

    public void pushQ(Customer newCustomer) {

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
        //guarded against empty
        front++;
        for (int i = 0, j = 0; i < size; i++) {
            if (arr[i].getID() != removeCustomer.getID()) {
                newArr[j++] = arr[i];
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != null) {
                newQueue.pushQ(newArr[i]);
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

    }




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
        // expands by factor of 2 each time ensuring fewer function calls
    }

    public void printItems()
    {
        for (int i = 0; i < size; i++) {
            if (this.arr[i] != null) {
                System.out.println("ID: " + arr[i].getID());
                System.out.println("Balance: " + arr[i].getBalance());
                System.out.println("Request: " + arr[i].getRequestNo());
                // standard outputting of 3 key attributes
            }
            else {
                // handles null occurrences
                System.out.println("Empty space");

            }


        }

    }

    public boolean searchQueue(String searchID) {
        // search algorithm, linear. binary was attempted but couldn't be implemented fully in time.
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

    // binary search implementation

//    public int BinarySearch(Customer[] arr, String searchValue, int first, int last)
//    {
//        int l = 0, r = arr.length - 1;
//        while (l <= r) {
//            int mid = 1 + (r - l) / 2;
//
//            int val = searchValue.compareToIgnoreCase(this.arr[mid].getID());
//            if (val == 0) {
//                return mid;
//                // search value found at mid point
//            }
//            if (val > 0) {
//                l = mid + 1;
//                // if greater than mid cut out left half
//            } else {
//                r = mid - 1;
//                // and likewise for right half in inverse situation
//            }
//        }
//            return -1;
//
//    }
//    public int BinaryHelper()
//    {
//        String searchValue = "KE72150";
//        BinarySearch(this.arr, searchValue, 0, this.arr.length-1);
//        int result = BinarySearch(this.arr, searchValue, 0, this.arr.length-1);
//        return result;
//    }


    public Customer[] InsertionSort(Customer[] arr)
    {
        // basic insertion sort for the array underlying all of the queue instances. returns array and ultimately queue sorted alphabetically by ID.
        int n = arr.length;
        String temp;

        for (int i = 0; i<n; ++i)
        {
            // guarding against null again
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
    // helper method
//    public Customer[] getSorted()
//    {
//        Customer sorted[] = InsertionSort(arr);
////        for (int X = 0; X< sorted.length-1; X++) {
//////            System.out.println(sorted[X].getID());
////        }
//        return sorted;
//
//    }
    public CustomerQueue getSortedAsQ()
    {
        CustomerQueue SQueue = new CustomerQueue();
        InsertionSort(arr);
        Customer[] sorted = InsertionSort(arr);

        for (int X = 0; X< sorted.length-1; X++)
        {
            if(sorted[X]!=null)
            {
                System.out.println(sorted[X].getID());
                SQueue.pushQ(sorted[X]);
            }
        }
        return SQueue;
    }






}
