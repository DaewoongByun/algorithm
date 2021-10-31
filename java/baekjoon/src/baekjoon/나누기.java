package baekjoon;

import java.util.*;

public class 나누기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int F = sc.nextInt();
		int start = N - (N % 100);
		int result = -1;
		for (int a = 0; a < 100; a++) {
			int num = start + a;
			if (num % F == 0) {
				result = a;
				break;
			}
		}
		System.out.println(result >= 10 ? result : "0" + result);

	}

}
