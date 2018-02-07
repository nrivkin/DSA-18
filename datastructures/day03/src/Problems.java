package day03.src;

import java.util.*;

public class Problems {

    public static class Node {
        int val;
        public Node next;

        public Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        ArrayList<Integer> original = new ArrayList<>();
        for (int i : A){
            original.add(i);
        }
        int i = 0;
        int counter = 0;
        while (counter < k){
            if (i >= original.size()-2){
                original.remove(original.size() - 1);
                counter++;
            } else if (original.get(i) > original.get(i + 1)){
                original.remove(i);
                if (i > 0) i--;
                counter++;
            } else {
                i++;
            }
        }
        List<Integer> l = new LinkedList<>();
        l.addAll(original);
        return l;
    }

    public static boolean isPalindrome(Node n) {
        if (n == null || n.next == null) return true;
        int len = 1;
        Node curr = n;
        while (curr.next != null){
            len++;
            curr = curr.next;
        }
        if(len == 2 || len == 3){
            return n.val == curr.val;
        }
        Node mid = n;
        for (int i = 0; i < len / 2; i++){
            mid = mid.next;
        }
        if (len % 2 == 1) {
            Node temp = mid;
            mid = mid.next;
            temp.next = null;
        }
        if (len == 4 || len == 5){
            return n.val == curr.val && n.next.val == mid.val;
        }
        Node first = mid;
        Node second = mid.next;
        Node third = second.next;
        first.next = null;
        while (third.next != null){
            second.next = first;
            first = second;
            second = third;
            third = third.next;
        }
        second.next =first;
        third.next = second;
        Node head2 = third;
        for (int i = 0; i < len /2; i++){
            if (n.val != head2.val){
                return false;
            }
            head2 = head2.next;
            n = n.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        String[] elems = s.split(" ");
        StringBuilder builder = new StringBuilder();
        LinkedList<String> stack= new LinkedList<>();
        int pointer = 0;
        while(pointer < elems.length) {
            if (!elems[pointer].equals(")") && pointer < elems.length) {
                String c = elems[pointer];
                if (c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-") || c.equals("(")) stack.add(c);
                else builder.append(c);
                pointer++;
            } else {
                while(!stack.isEmpty() && !stack.peekLast().equals("(")){
                    builder.append(stack.removeLast());
                }
                if (stack.size() != 0) stack.removeLast();
                pointer++;
            }
        }
        char[] postfix = builder.toString().toCharArray();
        builder = new StringBuilder();
        for (int i = 0; i< postfix.length - 1; i++){
            builder.append(postfix[i]).append(" ");
        }
        builder.append(postfix[postfix.length - 1]);
        return builder.toString();
    }

}
