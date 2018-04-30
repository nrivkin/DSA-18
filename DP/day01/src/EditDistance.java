public class EditDistance {

    public static int minEditDist(String a, String b) {
        // this is very similar to LCS - BU implementation
        int n = a.length();
        int m = b.length();
        if(n == 0) return m;
        if(m == 0) return n;
        int[][] DP = new int[n+1][m+1];
        for(int i = 0; i <= n; i++) DP[i][0] = i;
        for(int j = 0; j <= m; j++) DP[0][j] = j;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int cost = 0;
                if(a.charAt(i) != b.charAt(j)) cost = 1;
                int min = DP[i][j] + cost;
                if(DP[i][j+1] + 1 < min) min = DP[i][j+1] + 1;
                if(DP[i+1][j] + 1 < min) min = DP[i+1][j] + 1;
                DP[i+1][j+1] = min;
            }
        }
        return DP[n][m];
    }

}
