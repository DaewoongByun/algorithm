package kakao;

import java.util.*;

public class P2 {

	public static void main(String[] args) {
//		for(int n = 1; n <= 100000; n++) {
//			for(int k = 3; k <= 10; k++) {
//				solution(n, k);
//			}
//		}
		System.out.println(solution(1000000, 3));
	}

	public static int solution(int n, int k) {
		int answer = 0;

		String conversionString = conversion(n, k);
		Set<String> check0P0 = new HashSet<>();

		// P
		if (!conversionString.contains("0") && isPrime(conversionString))
			answer++;

		// 0P
		for (int i = conversionString.length() - 1; i >= 0; i--) {
			if (conversionString.charAt(i) == '0') {
				if (i == conversionString.length() - 1)
					break;
				String sub = conversionString.substring(i + 1);
				if (isPrime(sub)) {
					answer++;
//					System.out.println(sub + " 0P");
				}
				break;
			}
		}

		// P0
		for (int i = 0; i < conversionString.length(); i++) {
			if (conversionString.charAt(i) == '0') {
				if (i == 0)
					break;
				String sub = conversionString.substring(0, i);
				if (isPrime(sub)) {
					answer++;
//					System.out.println(sub + " P0");
				}
				break;
			}
		}
		// 0P0
		for (int i = 0; i < conversionString.length() - 2; i++) {
			if (conversionString.charAt(i) == '0') {
				for (int j = i + 1; j < conversionString.length(); j++) {
					if (conversionString.charAt(j) == '0') {
						if (j - 1 == i)
							break;
						String sub = conversionString.substring(i + 1, j);
						if (!check0P0.contains(sub) && isPrime(sub)) {
							answer++;
							check0P0.add(sub);
//							System.out.println(sub + " 0P0");
						}
						break;
					}
				}
			}
		}
		return answer;
	}

//	public static boolean isPrime(String s) {
//		if ("".equals(s))
//			return false;
//		return (new java.math.BigInteger(s)).isProbablePrime(100);
//	}
	
	public static boolean isPrime(String s) {
		if ("".equals(s))
			return false;
		if("1".equals(s)) return false;
		long n = Long.parseLong(s);
		for (long i = 2; i<=(long)Math.sqrt(n); i++) {
	      if (n % i == 0) {
	          return false;
	      }
		}
		return true;
	}

	public static String conversion(int number, int N) {
		StringBuilder sb = new StringBuilder();
		int current = number;

		while (current > 0) {
			sb.append(current % N);
			current /= N;
		}
		return sb.reverse().toString();
	}

}
