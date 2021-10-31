package baekjoon;

import java.util.*;

public class 이차원배열의합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] mat = new int[N + 1][M + 1];
		int[][] dp = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				mat[r][c] = sc.nextInt();
				dp[r][c] = mat[r][c] + dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1];
			}
		}
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			System.out.println(solve(dp, i, j, x, y));
		}
	}

	private static int solve(int[][] dp, int i, int j, int x, int y) {
		return dp[x][y] - dp[i-1][y] - dp[x][j-1] + dp[i-1][j-1];
	}

}
