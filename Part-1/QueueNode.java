import java.util.*;
import java.lang.reflect.*;
import java.io.*;

/**
 * QueueNode object class that hold a vector of child threads
 * inside of each condition given
 */
public class QueueNode {

    // initialize variable
    private Vector<Integer> queue;

    // default constructor
    public QueueNode() {
        queue = new Vector<Integer>();
    }

    // synchronized sleep function that will sleep until the child thread
    // is notified and back
    public synchronized int sleep() {
        // if there isn't any child thread in this condition/thread,
        // wait/sleep until it is added into the queue and notified
        if (queue.size() == 0) {
            try {
                wait();
            } catch (Exception e) {
                SysLib.cerr(e.toString() + "\n");
            }
        }
        // return the child thread that woken the calling thread
        // whenever wakeup() is called
        return queue.remove(queue.size() - 1);
    }

    public synchronized void wakeUp(int tid) {
        // add the child thread ID to the current 
        // queue and notify thread to wake up
        queue.add(tid);
        notify();
    }
}