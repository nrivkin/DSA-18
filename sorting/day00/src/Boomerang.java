import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int[] start : points){
            HashMap<Integer, Integer> distances = new HashMap<>();
            for (int[] end : points){
                if (start != end){
                    int distance = distance_squared(start, end);
                    distances.put(distance, distances.getOrDefault(distance, 0) + 1);
                }
            }
            for(int val : distances.values()){
                count += val*(val - 1);
            }
        }
        return count;
    }

    public static int distance_squared(int[] start, int[] end){
        int x_squared = (start[0] - end[0])*(start[0] - end[0]);
        int y_squared = (start[1] - end[1])*(start[1] - end[1]);
        return x_squared + y_squared;
    }
}

