package baekjoon;

import java.util.*;

public class 봄버맨 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int R, C, N;
	static char[][] map;
	static Queue<int[]> bombs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		N = Integer.parseInt(line[2]);
		map = new char[R][C];
		bombs = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String s = sc.nextLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = s.charAt(c);
			}
		}
		if (N < 2) {
			print(map);
			return;
		}
		for (int t = 0; t <= N - 2; t++) {
			if (t % 2 == 0) {
				fillBombs();
			} else {
				explode();
			}
		}
		print(map);
	}

	private static void explode() {
		while (!bombs.isEmpty()) {
			int[] bomb = bombs.poll();
			map[bomb[0]][bomb[1]] = '.';
			for (int d = 0; d < 4; d++) {
				int nr = bomb[0] + dr[d];
				int nc = bomb[1] + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					map[nr][nc] = '.';
				}
			}
		}
	}

	private static void fillBombs() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '.') {
					map[r][c] = 'O';
				} else {
					bombs.offer(new int[] { r, c });
				}
			}
		}
	}

	private static void print(char[][] map) {
		for (char[] line : map) {
			System.out.println(new String(line));
		}
	}

}
