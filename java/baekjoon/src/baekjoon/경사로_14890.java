package baekjoon;

import java.util.*;
import java.io.*;

public class 경사로_14890 {
	static int N, L;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		L = Integer.parseInt(in[1]);
		map = new int[N][N];
		check = new boolean[N][N];
		int result = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		L1: for (int r = 0; r < N; r++) {
			for (int c = 0; c < N - 1; c++) {
				int h1 = map[r][c];
				int h2 = map[r][c + 1];
				if (Math.abs(h1 - h2) > 1)
					continue L1;
				if (h1 == h2)
					continue;
				if (h1 > h2) {
					if (!check(r, c + 1, 1))
						continue L1;
				} else if (h2 > h1) {
					if (!check(r, c, 3))
						continue L1;
				}
			}
			result++;
		}
		check = new boolean[N][N];
		L2:for (int c = 0; c < N; c++) {
			for (int r = 0; r < N - 1; r++) {
				int h1 = map[r][c];
				int h2 = map[r + 1][c];
				if (Math.abs(h1 - h2) > 1)
					continue L2;
				if (h1 == h2)
					continue;
				if (h1 > h2) {
					if (!check(r+1, c, 2))
						continue L2;
				} else if (h2 > h1) {
					if (!check(r, c, 0))
						continue L2;
				}
			}
			result++;
		}
		System.out.println(result);
	}

	private static boolean check(int r, int c, int d) {
		int height = map[r][c];
		int[] dr = new int[] { -1, 0, 1, 0 };
		int[] dc = new int[] { 0, 1, 0, -1 };

		for (int t = 0; t < L; t++) {
			if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] != height || check[r][c])
				return false;
			check[r][c] = true;
			r += dr[d];
			c += dc[d];
		}

		return true;
	}
}
