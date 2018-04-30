public class SplitCoins {

    public static int splitCoins(int[] coins) {
        int n = coins.length;
        int coinsum = 0;
        for(int i : coins) coinsum += i;
        int shift = 0;
        if(coinsum % 2 != 0) shift++;
        if(n == 0) return 0;
        int[][] DP = new int[coinsum/2 + 1][n + 1];
        for(int i = 0; i <= n; i++){
            DP[0][i] = 0;
        }
        for(int i = 1; i <= coinsum/2; i++){
            DP[i][0] = i;
        }
        for(int i = 1; i < DP.length; i++){
            for(int j = 1; j <= n; j++){
                DP[i][j] = DP[i][j-1];
                if(coins[j-1] <= i){
                    DP[i][j] = Math.min(DP[i][j], DP[i - coins[j-1]][j-1]);
                }
            }
        }
        return DP[coinsum/2][n] * 2 + shift;
    }
}
