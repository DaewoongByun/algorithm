package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class 달팽이 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N][N];
		int r = N / 2;
		int c = N / 2;
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		int d = 3;
		int num = 1;

		int[] result = new int[2];

		while (num <= N * N) {
			map[r][c] = num;
			if (num == K) {
				result[0] = r;
				result[1] = c;
			}
			num++;

			int nd = (d + 1) % 4;
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			if (map[nr][nc] == 0) {
				r = nr;
				c = nc;
				d = nd;
				continue;
			} else {
				r += dr[d];
				c += dc[d];
				continue;
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		sb.append((result[0] + 1) + " " + (result[1] + 1));
		bw.write(sb.toString());
		bw.flush();
	}
}
