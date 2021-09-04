package baekjoon;

import java.util.*;
import java.io.*;

public class 사다리타기_2469 {
	static int K, N;
	static String result;
	static String[] map;
	static int unknownLine;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		result = br.readLine();
		map = new String[N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
			if (map[i].contains("?"))
				unknownLine = i;
		}

		for (int cur = 0; cur < K; cur++) {
			char resultChar = getResult(cur);
			if (resultChar != cur + 'A') {
				addStick(cur);
				if(map[unknownLine].contains("x")) {
					break;
				}
			}
		}
		map[unknownLine] = map[unknownLine].replace("?", "*");
		System.out.println(map[unknownLine]);
	}

	private static void addStick(int cur) {
		char correct = (char)(cur + 'A');
		for (int i = 0; i < map[unknownLine].length(); i++) {
			if (map[unknownLine].charAt(i) == '?') {
				map[unknownLine] = map[unknownLine].substring(0, i) + "-" + map[unknownLine].substring(i + 1);
				if(getResult(cur) == correct) {
					return;
				} 
				map[unknownLine] = map[unknownLine].substring(0, i) + "?" + map[unknownLine].substring(i + 1);
			}
		}
		char[] result = new char[map[unknownLine].length()];
		Arrays.fill(result, 'x');
		map[unknownLine] = new String(result);
	}

	private static char getResult(int cur) {
		for (int line = 0; line < map.length; line++) {
			char[] check = new char[2];
			if (cur > 0) {
				check[0] = map[line].charAt(cur - 1);
			}
			if (cur < map[line].length()) {
				check[1] = map[line].charAt(cur);
			}
			if (check[0] == '-') {
				cur--;
				continue;
			}
			if (check[1] == '-') {
				cur++;
				continue;
			}
		}
		return result.charAt(cur);
	}
}
