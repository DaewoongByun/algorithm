package baekjoon;

import java.util.*;
import java.io.*;

public class 이차원배열과연산_17140 {
	static int r, c, k;
	static int N, M;
	static int[][] mat;

	static class NumberCounts {
		int[] count;
		Set<Integer> usedNumber;

		public NumberCounts() {
			count = new int[101];
			usedNumber = new HashSet<>();
		}

		public void put(int num) {
			count[num]++;
			usedNumber.add(num);
		}

		public int[] toArray() {
			int[] usedNumberArray = usedNumber.stream().mapToInt(i -> i).toArray();
			List<int[]> list = new ArrayList<>();
			for (int num : usedNumberArray) {
				list.add(new int[] { num, count[num] });
			}
			list.sort((e1, e2) -> {
				if (e1[1] == e2[1]) {
					return e1[0] - e2[0];
				} else {
					return e1[1] - e2[1];
				}
			});
			int[] result = new int[list.size() * 2];
			for (int i = 0; i < list.size(); i++) {
				result[i * 2] = list.get(i)[0];
				result[i * 2 + 1] = list.get(i)[1];
			}
			return result;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		r = Integer.parseInt(st[0]);
		c = Integer.parseInt(st[1]);
		k = Integer.parseInt(st[2]);
		N = 3;
		M = 3;
		mat = new int[101][101];
		for (int i = 0; i < 3; i++) {
			st = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				mat[i][j] = Integer.parseInt(st[j]);
			}
		}

		for (int t = 0; t < 200; t++) {
			if (t == 101) {
				System.out.println(-1);
				break;
			}
			if (mat[r - 1][c - 1] == k) {
				System.out.println(t);
				break;
			}
			if (N >= M) {
				R();
			} else {
				C();
			}
		}

	}

	private static void C() {
		int nextN = 0;
		for (int m = 0; m < M; m++) {
			NumberCounts nc = new NumberCounts();
			for (int n = 0; n < N; n++) {
				if (mat[n][m] == 0)
					continue;
				nc.put(mat[n][m]);
			}
			int[] result = nc.toArray();
			nextN = Math.max(nextN, result.length);
			for (int i = 0; i < result.length; i++) {
				mat[i][m] = result[i];
			}
			for (int i = result.length; i < N; i++) {
				mat[i][m] = 0;
			}
		}
		N = nextN;
	}

	private static void R() {
		int nextM = 0;
		for (int n = 0; n < N; n++) {
			NumberCounts nc = new NumberCounts();
			for (int m = 0; m < M; m++) {
				if (mat[n][m] == 0)
					continue;
				nc.put(mat[n][m]);
			}
			int[] result = nc.toArray();
			nextM = Math.max(nextM, result.length);
			for (int i = 0; i < result.length; i++) {
				mat[n][i] = result[i];
			}
			for (int i = result.length; i < M; i++) {
				mat[n][i] = 0;
			}
		}
		M = nextM;
	}
}
