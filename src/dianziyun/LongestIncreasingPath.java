package dianziyun;

public class LongestIncreasingPath {
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxPathLength = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pathLength = dfs(matrix, dp, i, j);
                maxPathLength = Math.max(maxPathLength, pathLength);
            }
        }

        return maxPathLength;
    }

    private int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int maxPathLength = 1;
        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] direction : directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];

            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && matrix[newRow][newCol] > matrix[i][j]) {
                int pathLength = 1 + dfs(matrix, dp, newRow, newCol);
                maxPathLength = Math.max(maxPathLength, pathLength);
            }
        }

        dp[i][j] = maxPathLength;
        return maxPathLength;
    }
}