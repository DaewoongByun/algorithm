package baekjoon;

import java.util.*;

public class 반복수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<Integer, Integer> used = new HashMap<>();

		int A = sc.nextInt();
		int P = sc.nextInt();

		List<Integer> numList = new ArrayList<>();
		numList.add(A);
		used.put(A, 0);
		
		int result = 0;
		while(true) {
			int nextNum = getNextNum(numList.get(numList.size() - 1), P);
			if(used.containsKey(nextNum)) {
				result = used.get(nextNum);
				break;
			} else {
				used.put(nextNum, numList.size());
				numList.add(nextNum);
			}
		}
		System.out.println(result);

	}

	private static int getNextNum(int num, int P) {
		int result = 0;
		while(num > 0) {
			result += Math.pow(num % 10, P);
			num /= 10;
		}
		return result;
	}
}
