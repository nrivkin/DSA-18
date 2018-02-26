public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int num : A) {
            if(num > max) max = num;
        }
        int[] count = new int[max+1];
        for (int elem: A) count[elem]++;
        int curr = 0;
        for (int i = 0; i < count.length; i++){
            while (count[i] > 0){
                A[curr] = i;
                curr++;
                count[i]--;
            }
        }
    }
}
