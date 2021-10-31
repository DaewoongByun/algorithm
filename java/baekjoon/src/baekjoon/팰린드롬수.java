package baekjoon;

import java.util.*;

public class 팰린드롬수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String s = sc.nextLine();
			if (s.equals("0"))
				break;
			System.out.println(solve(s) ? "yes" : "no");
		}
	}

	private static boolean solve(String s) {
		for(int i = 0; i < s.length() / 2; i++) {
			if(s.charAt(i) == s.charAt(s.length() - i - 1)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
