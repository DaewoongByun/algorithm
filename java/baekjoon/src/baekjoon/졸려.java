package baekjoon;

import java.util.*;

public class 졸려 {
	static int X;
	static String original;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = Integer.parseInt(sc.nextLine());
		original = sc.nextLine();
		String s = original;
		int k;
		for (k = 0; k < X; k++) {
			s = reverse(s);
			if (s.equals(original)) {
				break;
			}
		}
		if (k == X) {
			System.out.println(s);
		} else {
			// 다시 original 될때까지 k개의 문자를 거침
			// ABCABCAB
			X %= (k + 1);
			for(int i = 0; i < X; i++) {
				s = reverse(s);
			}
			System.out.println(s);
		}
	}

	public static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i += 2) {
			sb.append(str.charAt(i));
		}
		int index = str.length() % 2 == 0 ? str.length() - 1 : str.length() - 2;
		for (int i = index; i >= 0; i -= 2) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
}
