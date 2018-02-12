
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private static final InsertionSort insertionsort = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= INSERTION_THRESHOLD){
            return insertionsort.sort(array);
        }
        int[] left = new int[array.length/2];
        int[] right = new int[array.length - left.length];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);
        return merge(sort(left), sort(right));
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int acurr = 0;
        int bcurr = 0;
        for (int i = 0; i < merged.length; i++){
            if (acurr >= a.length){
                merged[i] = b[bcurr];
                bcurr++;
            } else if (bcurr >= b.length){
                merged[i] = a[acurr];
                acurr++;
            } else {
                if (a[acurr] > b[bcurr]){
                    merged[i] = b[bcurr];
                    bcurr++;
                } else {
                    merged[i] = a[acurr];
                    acurr++;
                }
            }
        }
        return merged;
    }

}
