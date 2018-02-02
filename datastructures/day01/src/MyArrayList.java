public class MyArrayList {
<<<<<<< HEAD
    // better to use <E>, general purpose
    // in Cow should override hashcode - any time you override equals you should also override hashcode
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        this.size = 0;
        this.elems = new Cow[10];
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        this.size = 0;
        this.elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)*
    public void add(Cow c) {
        if (size + 1 == elems.length) {
            Cow[] temp = new Cow[(elems.length << 1)];
            System.arraycopy(elems, 0, temp, 0, elems.length);
            elems = temp;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        return elems[index];
    }

    // TODO: Runtime: O(N)*
    public Cow remove(int index) throws IndexOutOfBoundsException {
        if (index > size - 1 || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Cow c = elems[index];
        for (int i = index; i < size - 1; i++){
            elems[i] = elems[i+1];
        }
        size--;
        if (size < elems.length >> 2){
            Cow[] temp = new Cow[elems.length >> 1];
            System.arraycopy(elems, 0, temp, 0, size - 1);
            elems = temp;
        }
        return c;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) throws IndexOutOfBoundsException {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        add(elems[size - 1]);
        for (int i = size - 2; i > index; i--) {
            elems[i] = elems[i-1];
        }
        elems[index] = c;
=======
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(?)
    public MyArrayList() {
        // TODO
    }

    // TODO: Runtime: O(?)
    public MyArrayList(int capacity) {
        // TODO
    }

    // TODO: Runtime: O(?)
    public void add(Cow c) {
        // TODO
    }

    // TODO: Runtime: O(?)
    public int size() {
        // TODO
        return -1;
    }

    // TODO: Runtime: O(?)
    public Cow get(int index) {
        // TODO
        return null;
    }

    // TODO: Runtime: O(?)
    public Cow remove(int index) {
        // TODO
        return null;
    }

    // TODO: Runtime: O(?)
    public void add(int index, Cow c) {
        // TODO
>>>>>>> 8caf3730f68ef24e13da03a7002975901d9c77c4
    }
}
