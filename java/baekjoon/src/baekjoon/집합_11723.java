package baekjoon;

import java.util.*;
import java.io.*;

public class 집합_11723 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder result = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean[] elements = new boolean[21];
		for (int t = 0; t < N; t++) {
			String[] commands = br.readLine().split(" ");
			String command = commands[0];
			int num = commands.length > 1 ? Integer.parseInt(commands[1]) : 0;
			switch (command) {
			case "add":
				elements[num] = true;
				break;
			case "remove":
				elements[num] = false;
				break;
			case "check":
				result.append(elements[num] ? 1 : 0);
				if(t != N-1) {
					result.append("\n");
				}
				break;
			case "toggle":
				elements[num] = !elements[num];
				break;
			case "all":
				Arrays.fill(elements, true);
				break;
			case "empty":
				Arrays.fill(elements, false);
				break;
			default:
				break;
			}
		}
		bw.append(result.toString());
		bw.close();
	}

}
