package baekjoon;

import java.util.*;

public class 장군 {
	static int R = 10, C = 9;
	static int[][] map;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] sang = new int[] { sc.nextInt(), sc.nextInt() };
		map = new int[R][C];
		map[sc.nextInt()][sc.nextInt()] = 1;

		System.out.println(bfs(sang));
	}

	private static int bfs(int[] sang) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sang[0], sang[1], 0 });
		boolean[][] v = new boolean[R][C];
		v[sang[0]][sang[1]] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (map[cur[0]][cur[1]] == 1) {
				return cur[2];
			}

			for (int d = 1; d < 8; d += 2) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 0) {
					for (int gap = -1; gap < 2; gap += 2) {
						int nd = (d + gap) % 8;
						if (canGoCheck(nr, nc, nd, v)) {
							v[nr + dr[nd] * 2][nc + dc[nd] * 2] = true;
							q.offer(new int[] { nr + dr[nd] * 2, nc + dc[nd] * 2, cur[2] + 1 });
						}
					}
				}
			}
		}
		return -1;
	}

	private static boolean canGoCheck(int r, int c, int d, boolean[][] v) {
		int nr = r + dr[d];
		int nc = c + dc[d];
		if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 1)
			return false;
		nr += dr[d];
		nc += dc[d];
		if (nr < 0 || nr >= R || nc < 0 || nc >= C || v[nr][nc])
			return false;
		return true;
	}
}
