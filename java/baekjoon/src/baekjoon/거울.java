package baekjoon;

import java.util.*;
import java.io.*;

public class 거울 {
	static int R, C;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		R = Integer.parseInt(in[0]);
		C = Integer.parseInt(in[1]);
		map = new int[R][C];
		for(int r = 0; r < R; r++) {
			in = br.readLine().split(" ");
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(in[c]);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			sb.append(findExit(r, 0, 1) + " ");
		}
		for (int c = 0; c < C; c++) {
			sb.append(findExit(R - 1, c, 0) + " ");
		}
		for (int r = R - 1; r >= 0; r--) {
			sb.append(findExit(r, C - 1, 3) + " ");
		}
		for (int c = C - 1; c >= 0; c--) {
			sb.append(findExit(0, c, 2) + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

	private static int findExit(int r, int c, int d) {
		while (r >= 0 && r < R && c >= 0 && c < C) {
			if (map[r][c] == 0) {
				r = r + dr[d];
				c = c + dc[d];
			} else {
				if (d == 0)
					d = 1;
				else if (d == 1)
					d = 0;
				else if (d == 2)
					d = 3;
				else
					d = 2;
				r = r + dr[d];
				c = c + dc[d];
			}
		}
		return getNum(r, c);
	}

	private static int getNum(int r, int c) {
		if(c == -1) {
			return r + 1;
		}
		else if(r == R) {
			return R + 1 + c;
		}
		else if(c == C) {
			return R + C + 1 + (R - 1 - r);
		}
		else {
			return R + C + R + 1 + (C - 1 - c);
		}
	}
}
