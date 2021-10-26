package baekjoon;

import java.util.*;
import java.io.*;

public class 학생번호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] students = new String[N];
		for (int i = 0; i < N; i++) {
			students[i] = br.readLine();
		}
		int length;
		L: for (length = 1; length <= 100; length++) {
			Set<String> set = new HashSet<>();
			for (String number : students) {
				String endNumber = number.substring(number.length() - length);
				if (set.contains(endNumber)) {
					continue L;
				}
				set.add(endNumber);
			}
			if(set.size() == N) {
				break;
			}
		}
		System.out.println(length);
	}
}
