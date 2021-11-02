package baekjoon;

import java.util.*;

public class 쉽게푸는문제 {

	public static void main(String[] args) {
		int index = 1;
		int num = 1;
		int[] arr = new int[1001];
		while (index <= 1000) {
			for (int i = 0; i < num; i++) {
				arr[index] = arr[index - 1] + num;
				index++;
				if (index > 1000)
					break;
			}
			num++;
		}
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		System.out.println(arr[B] - arr[A-1]);
	}

}
