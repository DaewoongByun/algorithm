package baekjoon;

import java.util.*;
import java.io.*;

public class 헨리_10253 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			long result = solution(a, b);
			System.out.println(result);
		}

	}

	private static long solution(long a, long b) {
		if (a == 1)
			return b;
		long bunmo = findMaxBunmo(a, b);
		long[] next = minus(a, b, 1, bunmo);
		if(next[0] == 0) return bunmo;
		return solution(next[0], next[1]);
	}

	// a / b - n / bonmo
	private static long[] minus(long a, long b, long n, long bunmo) {
		a *= bunmo;
		n *= b;
		long[] result = {a-n, bunmo * b};
		return result;
	}

	// 1 / x1 <= a / b를 만족하는 가장 큰 단위분수 x1
	private static long findMaxBunmo(long a, long b) {
		if (b % a == 0)
			return b / a;
		else {
			return b / a + 1;
		}
	}

}
