package baekjoon;

import java.util.*;
import java.io.*;

public class 숨바꼭질3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean[] v = new boolean[200001];

		if (N == K) {
			System.out.println(0);
			return;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
		pq.offer(new int[] { N, 0 });
		v[N] = true;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			v[cur[0]] = true;
			if (cur[0] == K) {
				System.out.println(cur[1]);
				break;
			}
			int newLocation = cur[0] * 2;

			if (newLocation <= 200000 && newLocation >= 0 && !v[newLocation]) {
				pq.offer(new int[] { newLocation, cur[1] });
			}
			newLocation = cur[0] - 1;
			if (newLocation <= 200000 && newLocation >= 0 && !v[newLocation]) {
				pq.offer(new int[] { newLocation, cur[1] + 1 });
			}
			newLocation = cur[0] + 1;

			if (newLocation <= 200000 && newLocation >= 0 && !v[newLocation]) {
				pq.offer(new int[] { newLocation, cur[1] + 1 });
			}
		}
	}

}
