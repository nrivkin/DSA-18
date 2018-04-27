

public class LongestIncreasingSubsequence {

    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        int[] DP = new int[A.length];
        for(int i = 0; i < DP.length; i++){
            DP[i] = 1;
        }
        return recurseLIS(DP, A);
    }

    private static int recurseLIS(int[] DP, int[] A){
        if(A.length == 0) return 0;
        int max = 1;
        for(int i = 1; i < A.length; i++){
            for(int j = 0; j < i; j++){
                if(A[i] > A[j]){
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                    max = Math.max(max, DP[i]);
                }
            }
        }
        return max;
    }
}