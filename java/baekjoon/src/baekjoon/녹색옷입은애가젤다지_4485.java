package baekjoon;

import java.util.*;
import java.io.*;

public class 녹색옷입은애가젤다지_4485 {
	static int N;
	static int[][] map;
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			if (N == 0)
				break;
			for (int i = 0; i < N; i++) {
				String[] in = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(in[j]);
				}
			}
			int result = bfs();
			System.out.printf("Problem %d: %d\n", k++, result);
		}

	}

	private static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[2] - n2[2]);
		boolean[][] v = new boolean[N][N];
		v[0][0] = true;
		pq.offer(new int[] { 0, 0, map[0][0] });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
					if (nr == N - 1 && nc == N - 1) {
						return cur[2] + map[nr][nc];
					}
					v[nr][nc] = true;
					pq.offer(new int[] { nr, nc, cur[2] + map[nr][nc] });
				}
			}
		}
		return -1;
	}

}
