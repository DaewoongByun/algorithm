package baekjoon;

import java.util.*;
import java.io.*;

public class 미네랄_2933 {
	static int R, C;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		String[] in = br.readLine().split(" ");
		R = Integer.parseInt(in[0]);
		C = Integer.parseInt(in[1]);
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int N = Integer.parseInt(br.readLine());
		in = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int r = R - Integer.parseInt(in[i]);
			if (i % 2 == 0) {
				// 왼쪽
				throwStick(r, "left");
			} else {
				// 오른쪽
				throwStick(r, "right");
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			if (i < R - 1)
				System.out.println();
		}
	}

	private static void throwStick(int r, String dir) {
		int c = -1;
		if ("left".equals(dir)) {
			for (int j = 0; j < C; j++) {
				if (map[r][j] == 'x') {
					c = j;
					break;
				}
			}
		} else {
			for (int j = C - 1; j >= 0; j--) {
				if (map[r][j] == 'x') {
					c = j;
					break;
				}
			}
		}
		if (c == -1)
			return;
		map[r][c] = '.';
		// 왼
		if (needDrop(r, c - 1))
			drop(r, c - 1);
		// 위
		if (needDrop(r - 1, c))
			drop(r - 1, c);
		// 오
		if (needDrop(r, c + 1))
			drop(r, c + 1);
		// 아래
		if (needDrop(r + 1, c))
			drop(r + 1, c);
	}

	private static void drop(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });
		boolean[][] v = new boolean[R][C];

		char[][] copyMap = copy();
		copyMap[r][c] = 'o';
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && copyMap[nr][nc] == 'x') {
					q.offer(new int[] { nr, nc });
					copyMap[nr][nc] = 'o';
				}
			}
		}
		
		q.offer(new int[] { r, c });
		v = new boolean[R][C];
		v[r][c] = true;
		int dropHeight = 500;
		
		List<int[]> dropList = new ArrayList<>();
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			dropList.add(cur);
			int curDropHeight = getCurDropHeight(cur[0], cur[1], copyMap);
			if (curDropHeight > 0) {
				dropHeight = Math.min(dropHeight, curDropHeight);
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && map[nr][nc] == 'x') {
					v[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		for(int[] loc : dropList) {
			map[loc[0]][loc[1]] = '.';
		}
		for(int[] loc : dropList) {
			map[loc[0] + dropHeight][loc[1]] = 'x';
		}
	}

	private static char[][] copy() {
		char[][] copy = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	private static int getCurDropHeight(int r, int c, char[][] copyMap) {
		for (int i = r + 1; i < R; i++) {
			if (copyMap[i][c] == 'o') {
				return 0;
			}
			if (map[i][c] == 'x') {
				return i - r - 1;
			}
			if (i == R - 1) {
				return i - r;
			}
		}
		return 0;
	}

	private static boolean needDrop(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '.')
			return false;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });
		boolean[][] v = new boolean[R][C];
		v[r][c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == R - 1) {
				return false;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc] && map[nr][nc] == 'x') {
					v[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}

		return true;
	}

}
