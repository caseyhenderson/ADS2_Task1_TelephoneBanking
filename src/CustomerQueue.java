public class CustomerQueue {
    private float body[];
    private String IDBody[];
    private int front;
    private int rear;
    private int QCap;
    private int count;

    // Default Constructor
    CustomerQueue(int size){
        body = new float[size];
        IDBody = new String[size];
        QCap = size;
        front = 0;
        rear = -1;
        count = 0;

    }
    // add printing all the queue
    // a lot of these methods will need shifting to Main potentially????

    public void removeFromQueue()
    {
        if (checkEmpty())// same as checkEmpty() == true
        {
            System.out.println("The queue is empty and therefore nothing can be removed from it");
            // Preventing Underflow
            System.exit(1);
            // this should do what break does
        }
        System.out.println("Removing" + body[front]);
        front = (front + 1) % QCap; // modulo stops it going over the new size (now thing has been removed)
        count--;
    }
    // will need to change 'float' to whatever customer is actually stored as, fine now though

    public void addToQueue(float item, String ID)
    {
        if (checkFull())
        {
            System.out.println("The queue is full and therefore nothing can be added to it.");
            // preventing Overflow
            System.exit(1);
        }
        System.out.println("Inserting " + item);
        rear = (rear + 1)% QCap;
        // PROBLEM IS HERE WITH WHATEVER'S CAUSING US TO BE OUT OF BOUNDS
        // fixed now by altering size and qcap, size is parameter QCap is defined value
        body[rear] = item;
        IDBody[rear] = ID;
        count++;

    }
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

    public float getFront()
    {
        if(checkEmpty()) {
            System.out.println("Empty so nothing at front");
            //underflow
            System.exit(1);
        }
        return body[front];

    }

    public int queueSize()
    {
        return count;
    }


    public boolean checkEmpty()
    {
        return (queueSize() == 0);
        //  check this
    }
    public boolean checkFull()
    {
        return (queueSize() == QCap);
    }
    public void printItems() // can add queue as parameter, unclear if necessary or not.
    {
        for (int i = 0; i<QCap; i++)
        {
            System.out.println("Item: " + body[i] );
            System.out.println("ID: " + IDBody[i]);
        }
    // return to check this

    }

}
