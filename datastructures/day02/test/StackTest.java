import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
<<<<<<< HEAD
import day02.src.your_code.MyStack;
=======
import your_code.MyStack;
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4

public class StackTest {

    private MyStack stack;

    /**
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        stack = new MyStack();
        stack.push(1);
        stack.push(4);
        stack.push(2);
    }

    /**
     * Tests functionality of the stack
     */
    @Test
    public void testStack() {
        stack.push(3);
<<<<<<< HEAD
        int e = stack.pop();
=======
        int e = (int) stack.pop();
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
        assertEquals(e, 3);

        int bigNumber = 6;
        stack.push(bigNumber);

        assertEquals(stack.isEmpty(), false);
        stack.pop();
        stack.pop();
        stack.pop();
<<<<<<< HEAD
        e = stack.pop();
=======
        e = (int) stack.pop();
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
        assertEquals(e, 1);

        assertEquals(stack.isEmpty(), true);
    }

    /**
     * Tests maxElement functionality
     */
    @Test
    public void testMax() {
        stack.push(3);
        assertEquals((int) stack.maxElement(), 4);
        stack.pop();

        int bigNumber = 6;
        stack.push(bigNumber);
        assertEquals((int) stack.maxElement(), bigNumber);

        stack.pop();
<<<<<<< HEAD
        stack.pop();
=======
        assertEquals((int) stack.maxElement(), 4);
        stack.pop();
        assertEquals((int) stack.maxElement(), 4);
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
        stack.pop();
        assertEquals((int) stack.maxElement(), 1);
    }

}
