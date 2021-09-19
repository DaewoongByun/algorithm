package baekjoon;

import java.util.*;

public class IQTest_1111 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		}

		boolean allSame = isAllSame(list);
		if (N == 1) {
			System.out.println("A");
			return;
		}
		if(N == 2 && allSame) {
			System.out.println(list[0]);
			return;
		}
		if(N == 2 && !allSame) {
			System.out.println("A");
			return;
		}
		if (allSame) {
			System.out.println(list[0]);
			return;
		}
		
		if(list[1] == list[0]) {
			System.out.println("B");
			return;
		}
		int a = (list[2] - list[1]) / (list[1] - list[0]);
		int b = list[1] - a * list[0];

		if (check(list, a, b)) {
			System.out.println(a * list[N - 1] + b);
		} else {
			System.out.println("B");
		}

//		ax + b = y
//		list[0] * a + b = list[1]
//		list[1] * a + b = list[2]
//		a = (list[2] - list[1]) / (list[1] - list[0])
//		b = list[1] - a * list[0]

	}

	private static boolean isAllSame(int[] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i] != list[0]) {
				return false;
			}
		}
		return true;
	}

	private static boolean check(int[] list, int a, int b) {
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] * a + b != list[i + 1])
				return false;
		}
		return true;
	}

}
