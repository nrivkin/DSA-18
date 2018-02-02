import ADTs.QueueADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import day02.src.your_code.MyPriorityQueue;
import day02.src.your_code.MyQueue;
=======
import your_code.MyPriorityQueue;
import your_code.MyQueue;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QueueTest {

    private MyQueue queue;
    private MyPriorityQueue maxQueue;

    /**
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {

        queue = new MyQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        maxQueue = new MyPriorityQueue();
        maxQueue.enqueue(1);
        maxQueue.enqueue(2);
        maxQueue.enqueue(3);
    }

    /**
     * Tests functionality of a queue
     */
    public void testQueue(QueueADT<Integer> queue) {
        int e = queue.dequeue();
        assertEquals(e, 1);

        queue.enqueue(150);
        assertEquals(queue.isEmpty(), false);

        e = queue.dequeue();
        assertEquals(e, 2);
        assertEquals(queue.isEmpty(), false);

        e = queue.dequeue();
        assertEquals(e, 3);
        assertEquals(queue.isEmpty(), false);

        e = queue.dequeue();
        assertEquals(e, 150);
        assertEquals(queue.isEmpty(), true);
    }

    /**
<<<<<<< HEAD
     * Tests functionality of day02.src.your_code.MyQueue
=======
     * Tests functionality of your_code.MyQueue
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
     */
    @Test
    public void testMyQueue() {
        testQueue(queue);
    }

    /**
<<<<<<< HEAD
     * Tests functionality of day02.src.your_code.MyPriorityQueue
=======
     * Tests functionality of your_code.MyPriorityQueue
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
     */
    @Test
    public void testPriorityQueue() {
        maxQueue.enqueue(5);
        assertEquals(maxQueue.dequeueMax(), 5);
        maxQueue.enqueue(2);
        assertEquals(maxQueue.dequeueMax(), 3);
        assertEquals(maxQueue.dequeueMax(), 2);
        assertEquals(maxQueue.dequeueMax(), 2);
        assertEquals(maxQueue.dequeueMax(), 1);
    }

}
