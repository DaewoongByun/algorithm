package baekjoon;

import java.util.*;

public class 나머지와몫이같은수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		System.out.println(solve(N));
	}

	private static long solve(long N) {
		return N * (N-1) * (N+1) / 2;
	}

}
