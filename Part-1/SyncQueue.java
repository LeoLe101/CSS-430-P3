import java.util.*;
import java.lang.reflect.*;
import java.io.*;

/**
 * SyncQueue object class is an array of QueueNode. Each element is
 * considered a condition. Thread will be put to sleep and monitor here.
 */
public class SyncQueue {

    // initialize variable
    private QueueNode[] queue;
    private final static int COND_NUM = 10;
    private final static int THREAD_ID = 0;
    private final static int ERROR = -1;

    // default constructor
    public SyncQueue() {
        queue = new QueueNode[COND_NUM];
        // initialize each node
        for (int i = 0; i < queue.length; ++i) {
            queue[i] = new QueueNode();
        }
    }

    // constructor with the number of conditions
    // or the number of event types
    public SyncQueue(int cond_max) {
        queue = new QueueNode[cond_max];
        // initialize each node
        for (int i = 0; i < queue.length; ++i) {
            queue[i] = new QueueNode();
        }
    }

    // enqueue calling thread into the queue 
    // and waits until a given condition is satisfied
    public int enqueueAndSleep(int condition) {
        int result = ERROR;
        // check if condition is in the queue range
        if (condition < queue.length && condition > -1) {
            result = queue[condition].sleep();
        }
        return result; // ID of child thread has woken the calling thread
    }

    // dequeue and wake up a thread waiting for a given condition
    // take in the condition and the default thread id (0)
    public void dequeueAndWakeup(int condition) {
        // check if condition is in the queue range
        if (condition < queue.length && condition > -1) {
            queue[condition].wakeUp(THREAD_ID);
        }
    }

    // dequeue and wake up a thread waiting for a given condition
    // take in the condition and the thread id given
    // this thread id will be passed to the thread woken up from sleep
    public void dequeueAndWakeup(int condition, int tid) {
        // check if condition is in the queue range
        if (condition < queue.length && condition > -1) {
            queue[condition].wakeUp(tid);
        }
    }
}