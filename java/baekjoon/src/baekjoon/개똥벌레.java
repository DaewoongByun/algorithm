package baekjoon;

import java.util.*;
import java.io.*;

public class 개똥벌레 {
	static int N, H;
	static int[] countTop;
	static int[] countBottom;

	static int result;
	static int resultCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		H = Integer.parseInt(in[1]);
		countTop = new int[H + 1];
		countBottom = new int[H + 1];
		result = Integer.MAX_VALUE;
		resultCount = 0;

		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {
				countBottom[height]++;
			} else {
				countTop[height]++;
			}
		}

		for (int h = H - 1; h >= 0; h--) {
			countTop[h] += countTop[h + 1];
			countBottom[h] += countBottom[h + 1];
		}

		for (int h = 1; h <= H; h++) {
			int totalCount = countTop[h] + countBottom[H - h + 1];
			if (totalCount < result) {
				result = totalCount;
				resultCount = 1;
			} else if (totalCount == result) {
				resultCount++;
			}
		}
		System.out.println(result + " " + resultCount);
	}

}
