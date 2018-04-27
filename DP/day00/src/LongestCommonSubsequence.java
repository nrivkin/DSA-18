import java.util.*;

public class LongestCommonSubsequence {

    // Runtime: O(n*m)
    // Space: O(n*m)
    public static int LCS(String S1, String S2) {
        int[][] DP = new int[S1.length()][S2.length()];
        for(int i = 0; i < S1.length(); i++){
            for(int j = 0; j < S2.length(); j++){
                DP[i][j] = -1;
            }
        }
        return recurseLCS(DP, S1.toCharArray(), S1.length() - 1, S2.toCharArray(), S2.length() - 1);
    }

    private static int recurseLCS(int[][] DP, char[] C1, int cur1, char[] C2, int cur2){
        if(cur1 <= 0 || cur2 <= 0){
            return 0;
        }
        if(DP[cur1][cur2] != -1) return DP[cur1][cur2];
        int res0 = 0;
        if(C1[cur1] == C2[cur2]) res0 = 1 + recurseLCS(DP, C1, cur1 - 1, C2, cur2 - 1);
        int res1 = recurseLCS(DP, C1, cur1 - 1, C2, cur2);
        int res2 = recurseLCS(DP, C1, cur1, C2, cur2 - 1);
        int res = Math.max(res1, res2);
        res = Math.max(res, res0);
        DP[cur1][cur2] = res;
        return res;
    }
}