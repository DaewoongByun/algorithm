package baekjoon;

import java.util.*;

public class 빗물 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		int H = sc.nextInt();
		int W = sc.nextInt();
		int[] heights = new int[W];
		for (int i = 0; i < W; i++) {
			heights[i] = sc.nextInt();
		}

		for (int i = 1; i < W - 1; i++) {
			int height = heights[i];
			int leftMaxHeight = getLeftMaxHeight(heights, i);
			int rightMaxHeight = getRightMaxHeight(heights, i);
			int rainHeight = Math.min(leftMaxHeight, rightMaxHeight) - height;
			if (rainHeight < 0)
				rainHeight = 0;
			result += rainHeight;
		}

		System.out.println(result);
	}

	private static int getRightMaxHeight(int[] heights, int i) {
		int max = 0;
		for(int index = i + 1; index < heights.length; index++) {
			max = Math.max(heights[index], max);
		}
		return max;
	}

	private static int getLeftMaxHeight(int[] heights, int i) {
		int max = 0;
		for(int index = i - 1; index >= 0; index--) {
			max = Math.max(heights[index], max);
		}
		return max;
	}

}
