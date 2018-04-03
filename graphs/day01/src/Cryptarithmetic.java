import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }

    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        Set<Character> unused = new HashSet<>();
        for (Character c: S1.toCharArray()) unused.add(c);
        for (Character c: S2.toCharArray()) unused.add(c);
        for (Character c: S3.toCharArray()) unused.add(c);
        Map<Character, Integer> assignments = new HashMap<>();
        makeAssignments(assignments, unused, S1, S2, S3);
        return assignments;
    }

    private static boolean makeAssignments(Map<Character, Integer> assignments,
                                                           Set<Character> unused, String S1, String S2, String S3){
        if (unused.isEmpty()){
            if(validSolution(S1, S2, S3, assignments)) return true;
            else return false;
        }else{
            List<Character> temp = new ArrayList<>(unused);
            char c = temp.get(0);
            for(Integer i: randomOrder()){
                unused.remove(c);
                assignments.put(c, i);
                boolean success = makeAssignments(assignments, unused, S1, S2, S3);
                if(success) return true;
                unused.add(c);
                assignments.remove(c);
            }
            return false;
        }
    }
}

//
//    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
//        Set<Character> present = new HashSet<>();
//        List<Character> unused = new ArrayList<>();
//        int i = 0;
//        while (i < S1.length() || i < S2.length() || i < S3.length()){
//            if (i < S1.length()){
//                char c = S1.charAt(i);
//                if(!present.contains(c)) {
//                    unused.add(c);
//                    present.add(c);
//                }
//            }
//            if (i < S2.length()){
//                char c = S2.charAt(i);
//                if(!present.contains(c)) {
//                    unused.add(c);
//                    present.add(c);
//                }
//            }
//            if (i < S3.length()){
//                char c = S3.charAt(i);
//                if(!present.contains(c)) {
//                    unused.add(c);
//                    present.add(c);
//                }
//            }
//            i++;
//        }
//        Map<Character, Integer> assignments = new HashMap<>();
//        makeAssignments(assignments, unused, S1, S2, S3);
//        return assignments;
//    }
//
//    private static Boolean makeAssignments(Map<Character, Integer> assignments,
//                                           List<Character> unused, String S1, String S2, String S3){
//        if (unused.isEmpty()){
//            if(validSolution(S1, S2, S3, assignments)) return true;
//            else return false;
//        }else{
//            char c = unused.get(0);
//            for(Integer i: Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)){
//                unused.remove(0);
//                assignments.put(c, i);
//                Boolean success = makeAssignments(assignments, unused, S1, S2, S3);
//                if(success) return true;
//                assignments.remove(c);
//                unused.add(0, c);
//            }
//            return false;
//        }
//    }
//}
