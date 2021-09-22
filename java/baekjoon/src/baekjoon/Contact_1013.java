package baekjoon;

import java.util.*;
import java.io.*;

public class Contact_1013 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if (check(s)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	// (100+1+ | 01)+
	private static boolean check(String s) {
		int index = 0;
		while (index < s.length()) {
			if (s.charAt(index) == '1') {
				if (index + 3 > s.length())
					return false;
				if (s.charAt(index + 1) != '0')
					return false;
				if (s.charAt(index + 2) != '0')
					return false;
				index += 3;
				while (index < s.length() && s.charAt(index) == '0') {
					index++;
				}
				if (index == s.length())
					return false;
				while (index < s.length() && s.charAt(index) == '1') {
					index++;
				}
			} else {
				if (index == s.length() - 1) {
					return false;
				}
				// 2번째 수가 0일 때
				if (s.charAt(index + 1) != '1') {
					index = index - 1;
					if (index <= 0)
						return false;
					if (s.charAt(index - 1) == '0')
						return false;
				} else {
					index += 2;
				}
			}
		}
		return true;
	}
	// 1001001

}
