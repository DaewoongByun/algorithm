package baekjoon;

import java.util.*;

public class Main2 {

	public static Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseNum) { // 하나의 테스트케이스를 처리하는 함수
												// 각 테스트케이스에 대하여 데이터를 입력받고 정답을 출력하세요
		Set<String> set = new HashSet<>();
		String[] in = scanner.nextLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);

		String[] requestNumbers = scanner.nextLine().split(" ");
		for (int index = 0; index < N; index++) {
			String requestNumber = requestNumbers[index];
			if(!set.contains(requestNumber)) {
				set.add(requestNumber);
				M--;
				if(M == 0) {
					System.out.println(index + 1);
					break;
				}
			}
		}
	}

	public static void main(String[] args) { // 프로그램의 시작부
		int tn = Integer.parseInt(scanner.nextLine()); // 테스트케이스의 수를 입력받는다
		for (int caseNum = 1; caseNum <= tn; caseNum++) { // 테스트케이스의 수 만큼 반복 수행한다
			testCase(caseNum);
		}
	}
}
