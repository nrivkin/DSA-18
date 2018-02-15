
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        int curr = 1;
        int i = 1;
        while (curr < array.length){
            if(array[i-1] > array[i]){
                swap(array, i, i-1);
                if (i > 1) i--;
            } else {
                curr++;
                i = curr;
            }
        }
        return array;
    }
}
