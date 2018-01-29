public class MyArrayList {
    // better to use <E>, general purpose
    // in Cow should override hashcode - any time you override equals you should also override hashcode
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        new MyArrayList(10);
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        size = 0;
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)*
    public void add(Cow c) {
        if (size + 1 == elems.length) {
            Cow[] temp = new Cow[elems.length * 2];
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

    // TODO: Runtime: O(?)
    public Cow get(int index) {
        return elems[index];
    }

    // TODO: Runtime: O(?)
    public Cow remove(int index) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Cow c = elems[index];
        for (int i = index; i < size - 1; i++){
            elems[i] = elems[i+1];
        }
        size--;
        if (size < elems.length / 4){
            Cow[] temp = new Cow[elems.length / 2];
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
    }
}
