import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        if (runningMedian.length == 0) return runningMedian;
        PriorityQueue<Integer> maxPQ = maxPQ();
        PriorityQueue<Integer> minPQ = minPQ();
        runningMedian[0] = (double) inputStream[0];
        maxPQ.offer(inputStream[0]);
        for(int i = 1; i < inputStream.length; i++) {
            int next = inputStream[i];
            if(next < runningMedian[i-1]) {
                maxPQ.offer(next);
            } else {
                minPQ.offer(next);
            }
            if(minPQ.size()-1 > maxPQ.size()){
                int temp = minPQ.poll();
                maxPQ.offer(temp);
            } else if (maxPQ.size() - 1 > minPQ.size()){
                int temp = maxPQ.poll();
                minPQ.offer(temp);
            }
            if(minPQ.size() == maxPQ.size()){
                runningMedian[i] = (double) (maxPQ.peek() + minPQ.peek()) / 2;
            } else {
                runningMedian[i] = (double) maxPQ.size() > minPQ.size() ? maxPQ.peek() : minPQ.peek();
            }
        }
        return runningMedian;
    }
}
