
package day02.src.your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

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
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if(more.isEmpty() && !less.isEmpty()) more.addLast(less.removeFirst());
        return more.removeFirst();
    }

}