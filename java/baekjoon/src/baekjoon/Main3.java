package baekjoon;

import java.util.*;
import java.io.*;

public class Main3 {
	static int N, M, K;
	static int[][] blocks;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		blocks = new int[N][K];
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				blocks[r][c] = Integer.parseInt(input[c]);
			}
		}
		push(0, K, 0);

		System.out.println(result);
	}
	
	// r번째줄 0 ~ M만큼 밀기
	private static void push(int r, int remainK, int sum) {
		// K만큼 밀었거나 모든 줄을 다 밀어봤으면 결과 업데이트(더 큰값으로)
		if (remainK == 0 || r == N) {
			result = Math.max(result, sum);
			return;
		}
		// r번째줄 0 ~ M만큼 밀어보고 다음줄로 넘기기
		for (int pushL = 0; pushL <= M; pushL++) {
			// 밀어본 횟수가 K를 초과하지 않게 처리
			if (remainK - pushL >= 0) {
				// 레이저맞고 제거되는 내구도(?)
				int removed = pushL == 0 ? 0 : blocks[r][M - pushL];
				// 다음줄 ㄱㄱ
				push(r + 1, remainK - pushL, sum + removed);
			}
		}
	}

}
