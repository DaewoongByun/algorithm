package baekjoon;

import java.util.*;

public class Maaaaaaaaaze {
	static int[][][] map;
	static int[] dr = { 0, 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 0, 1, 0, -1 };
	static int[] dk = { -1, 1, 0, 0, 0, 0 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[5][5][5];
		for (int k = 0; k < 5; k++) {
			for (int r = 0; r < 5; r++) {
				String[] in = sc.nextLine().split(" ");
				for (int c = 0; c < 5; c++) {
					map[k][r][c] = 1 - Integer.parseInt(in[c]);
				}
			}
		}

		rotate(0, new int[5][5][5], new boolean[5]);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void rotate(int k, int[][][] rotatedMap, boolean[] isUsed) {
		if (k == 5) {
			findMinDistance(rotatedMap, new int[] { 0, 0, 0 }, new int[] { 4, 4, 4 });
//			findMinDistance(rotatedMap, new int[] { 0, 0, 4 }, new int[] { 4, 4, 0 });
//			findMinDistance(rotatedMap, new int[] { 0, 4, 0 }, new int[] { 4, 0, 4 });
//			findMinDistance(rotatedMap, new int[] { 0, 4, 4 }, new int[] { 4, 0, 0 });
			return;
		}
		for (int insertK = 0; insertK < 5; insertK++) {
			if (isUsed[insertK])
				continue;

			int[][] rotated = copy(map[k]);
			rotatedMap[insertK] = rotated;
			isUsed[insertK] = true;
			rotate(k + 1, rotatedMap, isUsed);
			isUsed[insertK] = false;
			for (int rCount = 1; rCount < 4; rCount++) {
				rotated = rotateClockDir(rotated);
				rotatedMap[insertK] = rotated;
				isUsed[insertK] = true;
				rotate(k + 1, rotatedMap, isUsed);
				isUsed[insertK] = false;
			}
		}
	}

	private static int[][] rotateClockDir(int[][] layer) {
		int[][] rotated = new int[5][5];
		for (int c = 0; c < 5; c++) {
			rotated[0][c] = layer[4 - c][0];
			rotated[4][c] = layer[4 - c][4];
		}
		for (int r = 0; r < 5; r++) {
			rotated[r][0] = layer[4][r];
			rotated[r][4] = layer[0][r];
		}
		for (int c = 1; c < 4; c++) {
			rotated[1][c] = layer[4 - c][1];
			rotated[3][c] = layer[4 - c][3];
		}
		for (int r = 1; r < 4; r++) {
			rotated[r][1] = layer[3][r];
			rotated[r][3] = layer[1][r];
		}
		rotated[2][2] = layer[2][2];
		return rotated;
	}

	private static int[][] copy(int[][] layer) {
		int[][] copy = new int[5][5];
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				copy[r][c] = layer[r][c];
			}
		}
		return copy;
	}

	private static void findMinDistance(int[][][] rotatedMap, int[] start, int[] end) {
		if (rotatedMap[start[0]][start[1]][start[2]] == 1)
			return;
		if (rotatedMap[end[0]][end[1]][end[2]] == 1)
			return;
		boolean[][][] v = new boolean[5][5][5];
		v[start[0]][start[1]][start[2]] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { start[0], start[1], start[2], 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[3] >= result) {
				return;
			}
			for (int d = 0; d < 6; d++) {
				int nk = cur[0] + dk[d];
				int nr = cur[1] + dr[d];
				int nc = cur[2] + dc[d];
				if (nk == end[0] && nr == end[1] && nc == end[2]) {
					result = cur[3] + 1;
					return;
				}
				if (nk >= 0 && nk < 5 && nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && !v[nk][nr][nc]
						&& rotatedMap[nk][nr][nc] == 0) {
					v[nk][nr][nc] = true;
					q.offer(new int[] { nk, nr, nc, cur[3] + 1 });
				}
			}
		}
	}

}
