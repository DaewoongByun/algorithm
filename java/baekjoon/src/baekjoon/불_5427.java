package baekjoon;

import java.util.*;
import java.io.*;

public class 불_5427 {
	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int r, c;
	static Queue<int[]> fires;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String[] in = br.readLine().split(" ");
			M = Integer.parseInt(in[0]);
			N = Integer.parseInt(in[1]);
			map = new char[N][M];
			fires = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '@') {
						r = i;
						c = j;
					} else if (map[i][j] == '*') {
						fires.offer(new int[] { i, j });
					}

				}
			}

			int result = bfs();
			System.out.println(result == -1 ? "IMPOSSIBLE" : result);
		}

	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c, 0 });
		boolean[][] v = new boolean[N][M];
		v[r][c] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			spreadFire();
			for (int k = 0; k < size; k++) {
				int[] cur = q.poll();
				if (cur[0] == 0 || cur[0] == N - 1 || cur[1] == 0 || cur[1] == M - 1) {
					return cur[2] + 1;
				}
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && map[nr][nc] == '.') {
						v[nr][nc] = true;
						q.offer(new int[] { nr, nc, cur[2] + 1 });
					}
				}
			}

		}

		return -1;
	}

	private static void spreadFire() {
		int size = fires.size();
		for (int t = 0; t < size; t++) {
			int[] loc = fires.poll();
			for (int d = 0; d < 4; d++) {
				int nr = loc[0] + dr[d];
				int nc = loc[1] + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == '.') {
					map[nr][nc] = '*';
					fires.offer(new int[] { nr, nc });
				}
			}
		}
	}

}
