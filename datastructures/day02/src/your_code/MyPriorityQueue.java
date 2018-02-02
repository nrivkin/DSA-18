<<<<<<< HEAD
package day02.src.your_code;

import java.util.LinkedList;
=======
package your_code;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

<<<<<<< HEAD
    private LinkedList<Integer> less;
    private LinkedList<Integer> more;

    public MyPriorityQueue() {
        this.less = new LinkedList<>();
        this.more = new LinkedList<>();
    }

    public void enqueue(int item) {
        while (!more.isEmpty() && item > more.peekLast()) {
            less.addFirst(more.removeLast());
        }
        while (!less.isEmpty() && item < less.peekFirst()) {
            more.addLast(less.removeFirst());
        }
        more.addLast(item);
=======
    public void enqueue(int item) {
        // TODO
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
<<<<<<< HEAD
        if(more.isEmpty() && !less.isEmpty()) more.addLast(less.removeFirst());
        return more.removeFirst();
=======
        // TODO
        return -1;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }

}