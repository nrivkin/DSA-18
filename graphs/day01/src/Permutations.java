import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        List<Integer> ordered = new ArrayList<>();
        List<Integer> unused = new ArrayList<>(A);
        getPermutations(ordered, unused, permutations);
        return permutations;
    }

    private static void getPermutations(List<Integer> ordered, List<Integer> unused, List<List<Integer>> permutations){
        if(unused.isEmpty()) permutations.add(new ArrayList<>(ordered));
        else{
            ArrayList<Integer> temp = new ArrayList<>(unused);
            for (Integer i: temp){
                int position = unused.indexOf(i);
                ordered.add(unused.get(position));
                unused.remove(position);
                getPermutations(ordered, unused, permutations);
                ordered.remove(ordered.size() - 1);
                unused.add(position, i);
            }
        }
    }

}
