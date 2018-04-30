public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int rows = map.length - 1;
        int cols = map[0].length - 1;
        int[][] DP = new int[rows + 1][cols + 1];
        for(int i = 0; i <= rows; i++){
            for(int j = 0; j <= cols; j++){
                DP[i][j] = -1;
            }
        }
        DP[rows][cols] = Math.max(1, 1-map[rows][cols]);
        return recurseMinHealth(map, DP, 0, 0);
    }

    private static int recurseMinHealth(int[][] map, int[][] DP, int row, int col){
        if(DP[row][col] != -1) return DP[row][col];
        int cost = map[row][col];
        int minHealth = Math.max(1, 1-cost);
        if(row == DP.length - 1){
            return Math.max(minHealth, recurseMinHealth(map, DP, row, col+1)-cost);
        }
        if(col == DP[0].length - 1){
            return Math.max(minHealth, recurseMinHealth(map, DP, row+1, col)-cost);
        }
        int nextCost = Math.min(recurseMinHealth(map, DP, row+1, col), recurseMinHealth(map, DP, row, col+1));
        DP[row][col] = Math.max(minHealth, nextCost - cost);
        return DP[row][col];
    }
}
