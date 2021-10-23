package baekjoon;

import java.util.*;

public class 공주님을구해라 {
	static int R, C, T;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] in = sc.nextLine().split(" ");
		R = Integer.parseInt(in[0]);
		C = Integer.parseInt(in[1]);
		T = Integer.parseInt(in[2]);

		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			in = sc.nextLine().split(" ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(in[c]);
			}
		}
		int result = solve();
		System.out.println(result == -1 ? "Fail" : result);
	}

	private static int solve() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] v = new boolean[R][C];
		int gramDistance = Integer.MAX_VALUE;
		int distance = Integer.MAX_VALUE;

		q.offer(new int[] { 0, 0, 0 });
		v[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[2] > T)
				continue;
			if (cur[0] == R - 1 && cur[1] == C - 1) {
				distance = cur[2];
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc]) {
					if (map[nr][nc] == 0) {
						v[nr][nc] = true;
						q.offer(new int[] { nr, nc, cur[2] + 1 });
					} else if (map[nr][nc] == 2) {
						gramDistance = cur[2] + 1;
						gramDistance += ((R - 1) - nr) + ((C - 1) - nc);
						v[nr][nc] = true;
						continue;
					}
				}
			}
		}

		int result = Math.min(gramDistance, distance);
		if (result > T)
			return -1;
		return result;
	}
}
