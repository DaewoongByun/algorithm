package baekjoon;

import java.util.*;
import java.io.*;

public class 로봇시뮬레이션_2174 {
	static int R, C;
	static int N, M;
	static int[][] map;
	static Robot[] robots;
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static class Robot {
		int r, c, dir;

		public Robot(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		C = Integer.parseInt(in[0]);
		R = Integer.parseInt(in[1]);
		in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);

		map = new int[R][C];
		robots = new Robot[N + 1];
		for (int k = 0; k < N; k++) {
			in = br.readLine().split(" ");
			int x = Integer.parseInt(in[0]);
			int y = Integer.parseInt(in[1]);
			y = R - y;
			x--;
			map[y][x] = k + 1;
			String dir = in[2];
			if (dir.equals("N")) {
				robots[k + 1] = new Robot(y, x, 0);
			} else if (dir.equals("E")) {
				robots[k + 1] = new Robot(y, x, 1);
			} else if (dir.equals("S")) {
				robots[k + 1] = new Robot(y, x, 2);
			} else {
				robots[k + 1] = new Robot(y, x, 3);
			}
		}

		for (int k = 0; k < M; k++) {
			in = br.readLine().split(" ");
			int robotNum = Integer.parseInt(in[0]);
			String command = in[1];
			int repeat = Integer.parseInt(in[2]);

			int[] result = order(robotNum, command, repeat);
			if(result[0] == 1) {
				System.out.printf("Robot %d crashes into robot %d\n", robotNum, result[1]);
				return;
			} else if(result[0] == 2) {
				System.out.printf("Robot %d crashes into the wall", robotNum);
				return;
			}
		}
		System.out.println("OK");
	}

	private static int[] order(int robotNum, String command, int repeat) {
		int[] result = new int[2];

		if (command.equals("L")) {
			repeat = repeat % 4;
			robots[robotNum].dir = (robots[robotNum].dir + 4 - repeat) % 4;
		} else if (command.equals("R")) {
			repeat = repeat % 4;
			robots[robotNum].dir = (robots[robotNum].dir + repeat) % 4;
		} else {
			Robot robot = robots[robotNum];
			for (int k = 0; k < repeat; k++) {
				int nr = robot.r + dr[robot.dir];
				int nc = robot.c + dc[robot.dir];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					return new int[] { 2, 0 };
				}
				if (map[nr][nc] > 0) {
					return new int[] { 1, map[nr][nc] };
				} else {
					map[nr][nc] = map[robot.r][robot.c];
					map[robot.r][robot.c] = 0;
					robot.r = nr;
					robot.c = nc;
				}
			}

		}

		return result;
	}

}
