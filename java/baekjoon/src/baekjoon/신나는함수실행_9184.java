package baekjoon;

import java.util.*;
import java.io.*;

public class 신나는함수실행_9184 {
	static int[][][] w;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		w = new int[51][51][51];
		for (int i = 0; i < 51; i++) {
			for (int j = 0; j < 51; j++) {
				for (int k = 0; k < 51; k++) {
					w[i][j][k] = -1;
				}
			}
		}
		while (true) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			int c = Integer.parseInt(in[2]);
			if (a == -1 && b == -1 && c == -1) {
				break;
			}
			System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
		}
	}

	private static int w(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		if (w[a][b][c] != -1) {
			return w[a][b][c];
		}
		if (a > 20 || b > 20 || c > 20) {
			return w[20][20][20] = w(20, 20, 20);
		}
		if (a < b && b < c) {
			w[a][b][c - 1] = w(a, b, c - 1);
			w[a][b - 1][c - 1] = w(a, b - 1, c - 1);
			w[a][b - 1][c] = w(a, b, c - 1);
			return w[a][b][c - 1] + w[a][b - 1][c - 1] - w[a][b - 1][c];
		}
		w[a - 1][b][c] = w(a - 1, b, c);
		w[a - 1][b - 1][c] = w(a - 1, b - 1, c);
		w[a - 1][b][c - 1] = w(a - 1, b, c - 1);
		w[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1);
		return w[a - 1][b][c] + w[a - 1][b - 1][c] + w[a - 1][b][c - 1] - w[a - 1][b - 1][c - 1];
	}

}
