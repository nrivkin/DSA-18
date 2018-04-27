import java.util.HashMap;

public class DiceRollSum {

    // Runtime: O(N), 6N
    // Space: O(N), assuming perfect garbage collection
    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        DP[0] = 1;
        for(int i = 1; i < N + 1; i++){
            DP[i] = -1;
        }
        return recurseDiceSum(DP, N);
    }

    private static int recurseDiceSum(int[] DP, int n){
        if(n < 0) return 0;
        if(DP[n] != -1) return DP[n];
        int res = 0;
        for(int i = 1; i < 7; i++){
            res += recurseDiceSum(DP, n - 7 + i);
        }
        DP[n] = res;
        return res;

    }

}
