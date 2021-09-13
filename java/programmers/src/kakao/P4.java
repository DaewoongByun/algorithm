package kakao;

import java.util.Arrays;

public class P4 {
	static int scoreDiff;
	static int[] resultLion;
	static int N;

	public static void main(String[] args) {
		int n = 9;
		int[] info = { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 };
		System.out.println(Arrays.toString(solution(n, info)));
	}

	public static int[] solution(int n, int[] info) {
		scoreDiff = 0;
		resultLion = new int[11];
		N = n;
		find(n, resultLion, 0, 0, info);
		if (scoreDiff <= 0)
			return new int[] { -1 };
		return resultLion;
	}

	private static void find(int remain, int[] lion, int used, int index, int[] info) {
		if (used == N || index == 11) {
			int diff = calcDiff(lion, info);
			if (diff > scoreDiff) {
				scoreDiff = diff;
				resultLion = Arrays.copyOf(lion, lion.length);
			} else if (diff == scoreDiff) {
				for (int i = lion.length - 1; i >= 0; i--) {
					if (lion[i] > resultLion[i]) {
						resultLion = lion;
						break;
					} else if (lion[i] < resultLion[i]) {
						break;
					}
				}
			}
			return;
		}
		int to = Math.min(remain, info[index] + 1);
		if (index == 10) {
			int[] newLion = Arrays.copyOf(lion, lion.length);
			newLion[index] = remain;
			find(0, newLion, used + remain, index + 1, info);

		} else {

			for (int k = to; k >= 0; k--) {
				int[] newLion = Arrays.copyOf(lion, lion.length);
				newLion[index] = k;
				find(remain - k, newLion, used + k, index + 1, info);
			}
		}
	}

	private static int calcDiff(int[] lion, int[] info) {
		int lionScore = 0;
		int anotherScore = 0;
		for (int i = 0; i < 10; i++) {
			int score = 10 - i;
			if (lion[i] == 0 && info[i] == 0)
				continue;
			if (lion[i] > info[i]) {
				lionScore += score;
			} else {
				anotherScore += score;
			}
		}
		return lionScore - anotherScore;
	}
}
