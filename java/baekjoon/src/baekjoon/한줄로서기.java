package baekjoon;

import java.util.*;

public class 한줄로서기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] left = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			left[i] = sc.nextInt();
		}
		int[] jaris = new int[N];
		for (int i = 1; i <= N; i++) {
			int countLeft = 0;
			for (int jari = 0; jari < N; jari++) {
				if (left[i] == countLeft && jaris[jari] == 0) {
					jaris[jari] = i;
					break;
				}
				if (jaris[jari] == 0) {
					countLeft++;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			if(i == N-1) {
				System.out.print(jaris[i]);
				continue;
			}
			System.out.print(jaris[i] + " ");
		}
	}

}
