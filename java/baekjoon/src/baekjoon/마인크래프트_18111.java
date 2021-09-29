package baekjoon;

import java.io.*;
import java.util.*;

public class 마인크래프트_18111 {
	static int N, M, B, maxHeight, t, resultHeight, prevTime;
	static int[][] map;
	static int[] blockCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		B = Integer.parseInt(in[2]);
		map = new int[N][M];
		maxHeight = 0;
		resultHeight = 257;
		prevTime = 0;
		blockCount = new int[257];
		t = 0;
		for (int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(in[j]);
				maxHeight = Math.max(map[i][j], maxHeight);
				blockCount[map[i][j]]++;
			}
		}

		for (int height = maxHeight; height >= 0; height--) {
			if (isPossible(height)) {
				int time = getTime(height);
				// prevTime : 이전 걸린 시간 , time 지금 걸린 시간
				// prevTime > time : 계속 감
				// prevTime == time : resultHeight 안바꿈
				// prevTime < time : t가 결과
				if (prevTime == 0) {
					prevTime = time;
					resultHeight = height;
					if (time == 0) {
						break;
					}
				} else {
					if (prevTime < time) {
						break;
					} else if (prevTime > time) {
						prevTime = time;
						resultHeight = height;
					}
				}
			}
			dig(height);
		}
		System.out.println(prevTime + " " + resultHeight);
	}

	private static boolean isPossible(int height) {
		int needBlock = 0;
		for (int h = 0; h < height; h++) {
			needBlock += (height - h) * blockCount[h];
			if (needBlock > B)
				return false;
		}
		return true;
	}

	private static int getTime(int height) {
		int needBlock = 0;
		for (int h = 0; h < height; h++) {
			needBlock += (height - h) * blockCount[h];
		}
		return t + needBlock;
	}

	private static void dig(int height) {
		B += blockCount[height];
		t += blockCount[height] * 2;
		if (height > 0)
			blockCount[height - 1] += blockCount[height];
		blockCount[height] = 0;
	}

}
