import java.util.*;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        List<Integer> gaps = new ArrayList<>();
        int covered = occupied[occupied.length - 1] - occupied[0] + 1;
        for (int i = 0; i < occupied.length - 1; i++){
            int gapSize = occupied[i+1]- occupied[i] - 1;
            if(gapSize != 0){
                gaps.add(gapSize);
            }
        }
        Collections.sort(gaps);
        for (int i = 0; i < M - 1; i++) {
            if(!gaps.isEmpty()) {
                int gapSize = gaps.remove(gaps.size() - 1);
                covered -= gapSize;
            }
        }
        System.out.println(covered);
        return covered;
    }
}