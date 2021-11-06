package baekjoon;

import java.util.*;

public class 숫자정사각형 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] in = sc.nextLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			in = sc.nextLine().split("");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(in[c]);
			}
		}
		System.out.println(getResult());
	}

	private static int getResult() {
		for (int length = Math.min(N, M); length >= 2; length--) {
			for (int r = 0; r < N; r++) {
				if (r + length - 1 >= N)
					break;
				for (int c = 0; c < M; c++) {
					if (c + length - 1 >= M)
						break;
					if (map[r][c] == map[r + length - 1][c] && map[r][c] == map[r][c + length - 1]
							&& map[r][c] == map[r + length - 1][c + length - 1]) {
						return length * length;
					}
				}
			}
		}
		return 1;
	}

}
