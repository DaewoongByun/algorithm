package baekjoon;

import java.util.*;
import java.io.*;

public class 소코반 {
	static int R, C;
	static char[][] map;
	static int[] user;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int remainBoxCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int game = 1;
		while (true) {
			remainBoxCount = 0;
			String[] in = br.readLine().split(" ");
			R = Integer.parseInt(in[0]);
			C = Integer.parseInt(in[1]);
			if (R == 0 && C == 0) {
				break;
			}
			map = new char[R][C];
			for (int r = 0; r < R; r++) {
				String line = br.readLine();
				map[r] = line.toCharArray();
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 'W') {
						map[r][c] = '+';
						user = new int[] { r, c };
					} else if (map[r][c] == 'w') {
						map[r][c] = '.';
						user = new int[] { r, c };
					} else if (map[r][c] == 'b') {
						remainBoxCount++;
					}
				}
			}
			char[] commands = br.readLine().toCharArray();
			for (char command : commands) {
				doCommand(command);
				if (remainBoxCount == 0)
					break;
			}
			print(game++);
		}
	}

	private static void print(int game) {
		String result = remainBoxCount == 0 ? "complete" : "incomplete";
		System.out.printf("Game %d: %s\n", game, result);
		map[user[0]][user[1]] = map[user[0]][user[1]] == '.' ? 'w' : 'W';
		for (int r = 0; r < R; r++) {
			System.out.println(new String(map[r]));
		}
	}

	private static void doCommand(char command) {
		int dir = getDir(command);
		int nr = user[0] + dr[dir];
		int nc = user[1] + dc[dir];
		if (map[nr][nc] == '.' || map[nr][nc] == '+') {
			user[0] = nr;
			user[1] = nc;
		} else if (map[nr][nc] == 'b' || map[nr][nc] == 'B') {
			int nnr = nr + dr[dir];
			int nnc = nc + dc[dir];
			if (nnr >= 0 && nnr < R && nnc >= 0 && nnc < C && (map[nnr][nnc] == '.' || map[nnr][nnc] == '+')) {
				user[0] = nr;
				user[1] = nc;
				if (map[nr][nc] == 'b') {
					map[nr][nc] = '.';
				} else if (map[nr][nc] == 'B') {
					map[nr][nc] = '+';
					remainBoxCount++;
				}
				if (map[nnr][nnc] == '.') {
					map[nnr][nnc] = 'b';
				} else if (map[nnr][nnc] == '+') {
					map[nnr][nnc] = 'B';
					remainBoxCount--;
				}
			}
		}

	}

	private static int getDir(char command) {
		if (command == 'U')
			return 0;
		else if (command == 'R')
			return 1;
		else if (command == 'D')
			return 2;
		else if (command == 'L')
			return 3;
		return 0;
	}

}
