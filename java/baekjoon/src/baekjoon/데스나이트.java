package baekjoon;

import java.util.*;

public class 데스나이트 {
	static int N;
	static int[] dr = { -2, -2, 0, 0, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int r1 = sc.nextInt();
		int c1 = sc.nextInt();
		int r2 = sc.nextInt();
		int c2 = sc.nextInt();

		System.out.println(bfs(r1, c1, r2, c2));

	}

	private static int bfs(int r1, int c1, int r2, int c2) {
		if (r1 == r2 && c1 == c2)
			return 0;

		boolean[][] v = new boolean[N][N];
		v[r1][c1] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r1, c1, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 6; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr == r2 && nc == c2)
					return cur[2] + 1;
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
					v[nr][nc] = true;
					q.offer(new int[] { nr, nc, cur[2] + 1 });
				}
			}
		}
		return -1;
	}

}
