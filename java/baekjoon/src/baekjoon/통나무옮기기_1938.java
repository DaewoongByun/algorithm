package baekjoon;

import java.util.*;
import java.io.*;

public class 통나무옮기기_1938 {
	static int N;
	static char[][] map;
	static boolean[][][] v;
	static Stick stick;
	static Stick goal;
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static class Stick {
		int r, c, type;

		public Stick(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

		public Stick clone() {
			return new Stick(r, c, type);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		v = new boolean[N][N][2];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B') {
					if (j < N - 1 && j > 0 && map[i][j - 1] == 'B' && map[i][j + 1] == 'B')
						stick = new Stick(i, j, 0);
					else if (i < N - 1 && i > 0 && map[i - 1][j] == 'B' && map[i + 1][j] == 'B')
						stick = new Stick(i, j, 1);
				}
				if (map[i][j] == 'E') {
					if (j < N - 1 && j > 0 && map[i][j - 1] == 'E' && map[i][j + 1] == 'E')
						goal = new Stick(i, j, 0);
					else if (i < N - 1 && i > 0 && map[i - 1][j] == 'E' && map[i + 1][j] == 'E')
						goal = new Stick(i, j, 1);
				}

			}
		}

		int result = bfs();
		System.out.println(result);

	}

	private static int bfs() {
		Queue<Stick> q = new LinkedList<>();
		q.offer(stick.clone());
		v[stick.r][stick.c][stick.type] = true;
		int result = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				Stick cur = q.poll();
				if (cur.r == goal.r && cur.c == goal.c && cur.type == goal.type) {
					return result;
				}
				// 한 칸 이동
				for (int d = 0; d < 4; d++) {
					if (canMove(cur, d)) {
						int nextR = cur.r + dr[d];
						int nextC = cur.c + dc[d];
						if (!v[nextR][nextC][cur.type]) {
							Stick nextStick = new Stick(nextR, nextC, cur.type);
							v[nextR][nextC][cur.type] = true;
							q.offer(nextStick);
						}

					}
				}
				// 90도 회전
				boolean canRotate = true;
				L: for (int r = cur.r - 1; r <= cur.r + 1; r++) {
					for (int c = cur.c - 1; c <= cur.c + 1; c++) {
						if (r < 0 || r >= N || c < 0 || c >= N) {
							canRotate = false;
							break L;
						}
						if (map[r][c] == '1') {
							canRotate = false;
							break L;
						}

					}
				}
				if (canRotate && !v[cur.r][cur.c][1 - cur.type]) {
					Stick nextStick = new Stick(cur.r, cur.c, 1 - cur.type);
					v[cur.r][cur.c][1 - cur.type] = true;
					q.offer(nextStick);
				}
			}
			result++;

		}
		return 0;
	}

	private static boolean canMove(Stick cur, int d) {
		int nr = cur.r + dr[d];
		int nc = cur.c + dc[d];
		int nr1 = (cur.type == 0 ? cur.r : cur.r - 1) + dr[d];
		int nc1 = (cur.type == 0 ? cur.c - 1 : cur.c) + dc[d];
		int nr2 = (cur.type == 0 ? cur.r : cur.r + 1) + dr[d];
		int nc2 = (cur.type == 0 ? cur.c + 1 : cur.c) + dc[d];
		if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
			return false;
		if (nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= N || map[nr1][nc1] == '1')
			return false;
		if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= N || map[nr2][nc2] == '1')
			return false;
		return true;
	}

}
