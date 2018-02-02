<<<<<<< HEAD
package day02.src.your_code;
=======
package your_code;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
<<<<<<< HEAD
    private LinkedList<Integer> largest;

    public MyStack() {
        ll = new LinkedList<>();
        largest = new LinkedList<>();
=======

    public MyStack() {
        ll = new LinkedList<>();
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
<<<<<<< HEAD
        if (largest.isEmpty()){
            largest.addFirst(e);
        } else if (maxElement() <= e) {
            largest.addFirst(e);
        }
=======
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
<<<<<<< HEAD
        if (pop == maxElement()) {
            largest.removeFirst();
        }
=======
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
<<<<<<< HEAD
        return largest.getFirst();
=======
        // TODO
        return 0;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }
}
