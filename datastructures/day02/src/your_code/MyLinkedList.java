package day02.src.your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node n = new Node(c, null, null);
        if (size == 0){
            tail = n;
            head = n;
        } else {
             tail.next = n;
             n.prev = tail;
             tail = n;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        Node n = new Node(c, null, null);
        if (size == 0){
            tail = n;
            head = n;
        } else {
            head.prev = n;
            n.next = head;
            head = n;
        }
        size++;
    }

    public Chicken get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        for (int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr.val;
    }

    public Chicken remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        for (int i = 0; i < index; i++){
            curr = curr.next;
        }
        if (curr == head){
            return removeFirst();
        } else if (curr == tail){
            return removeLast();
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        size--;
        return curr.val;

    }

    public Chicken removeFirst() throws IndexOutOfBoundsException{
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        if (size > 1) head = curr.next;
        head.prev = null;
        size--;
        return curr.val;
    }

    public Chicken removeLast() {
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        Node curr = tail;
        Chicken val = curr.val;
        if (size > 1) tail = curr.prev;
        tail.next = null;
        size--;
        return val;
    }
}