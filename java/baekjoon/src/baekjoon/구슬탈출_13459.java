package baekjoon;

import java.util.*;
import java.io.*;

public class 구슬탈출_13459 {
	static int N, M;
	static int[] red, blue;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
			if (line.contains("R")) {
				red = new int[] { i, line.indexOf('R') };
			}
			if (line.contains("B")) {
				blue = new int[] { i, line.indexOf('B') };
			}
		}
		map[red[0]][red[1]] = '.';
		map[blue[0]][blue[1]] = '.';

		int result = bfs();
		System.out.println(result);
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][][] v = new boolean[10][10][10][10];
		q.offer(new int[] { red[0], red[1], blue[0], blue[1], 0 });
		v[red[0]][red[1]][blue[0]][blue[1]] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[4] >= 10)
				break;
			for (int d = 0; d < 4; d++) {
				int[] result = getNext(cur, d);
				if (result[4] == 2) {
					continue;
				} else if (result[4] == 1) {
					return 1;
				}

				if (!v[result[0]][result[1]][result[2]][result[3]]) {
					v[result[0]][result[1]][result[2]][result[3]] = true;
					q.offer(new int[] { result[0], result[1], result[2], result[3], cur[4] + 1 });
				}
			}
		}
		return 0;
	}

	private static int[] getNext(int[] cur, int d) {
		// 그냥 움직임 : result[4] = 0
		// 빨간공만 빠져서 끝남 : result[4] = 1
		// 파란공이 빠져서 실패 : result[4] = 2
		int[] result = new int[5];
		int[] red = { cur[0], cur[1] };
		int[] blue = { cur[2], cur[3] };

		// 빨간공 먼저 옮기기
		while (true) {
			int nrRed = red[0] + dr[d];
			int ncRed = red[1] + dc[d];
			if (map[nrRed][ncRed] == '#' || (nrRed == blue[0] && ncRed == blue[1])) {
				break;
			}
			if (map[nrRed][ncRed] == 'O') {
				red[0] = -1;
				red[1] = -1;
				result[4] = 1;
				break;
			}
			red[0] = nrRed;
			red[1] = ncRed;
		}
		// 파란공 옮기기
		while (true) {
			int nrBlue = blue[0] + dr[d];
			int ncBlue = blue[1] + dc[d];
			if (map[nrBlue][ncBlue] == '#' || (nrBlue == red[0] && ncBlue == red[1])) {
				break;
			}
			if (map[nrBlue][ncBlue] == 'O') {
				blue[0] = -1;
				blue[1] = -1;
				result[4] = 2;
				return result;
			}
			blue[0] = nrBlue;
			blue[1] = ncBlue;
		}

		while (true) {
			if (red[0] == -1)
				break;
			int nrRed = red[0] + dr[d];
			int ncRed = red[1] + dc[d];
			if (map[nrRed][ncRed] == '#' || (nrRed == blue[0] && ncRed == blue[1])) {
				break;
			}
			if (map[nrRed][ncRed] == 'O') {
				red[0] = -1;
				red[1] = -1;
				result[4] = 1;
				break;
			}
			red[0] = nrRed;
			red[1] = ncRed;
		}
		result[0] = red[0];
		result[1] = red[1];
		result[2] = blue[0];
		result[3] = blue[1];

		return result;
	}

}
