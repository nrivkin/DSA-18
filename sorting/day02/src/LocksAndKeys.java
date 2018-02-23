import java.util.HashMap;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        KeySort(result, 0, locks.length - 1);
        return result;
    }

    private static void copyOver(char[][] result, int lo, int hi) {
        HashMap<Character, Integer> loc = new HashMap<>();
        for (int i = lo; i <= hi; i++) {
            loc.put(result[0][i], i);
        }
        for (int i = lo; i <= hi; i++) {
            result[1][loc.get(result[0][i])] = result[0][i];
        }
    }

    private static void KeySort(char[][] result, int lo, int hi) {
        if (hi > lo) {
            int p = partition(result, lo, hi);
            KeySort(result,lo,p);
            KeySort(result,p+1,hi);
        }
    }

    private static int partition(char[][] results, int lo, int hi) {
        char[] array = results[0];
        char pivot = results[1][lo];
        int curr = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < pivot) {
                curr++;
                swap(array, curr, i);
            }
        }
        swap(array, curr, lo);
        copyOver(results, lo, hi);
        return curr;
    }
}
