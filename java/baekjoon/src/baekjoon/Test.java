package baekjoon;
import java.io.*;
import java.util.*;
import java.lang.*;

class Test {
	static class Log {
		String type;
		String num;
		int count;

		Log(String type, String num, int count) {
			this.type = type;
			this.num = num;
			this.count = count;
		}
	}

	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(scanner.nextLine()); // 송수신 기록의 수
		String[] strLogs = new String[n]; // 송수신 기록
		List<Log> logs = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			strLogs[i] = scanner.nextLine();
		}

		for (int i = 0; i < n; i++) {
			String type = strLogs[i].split(" ")[0];
			String num = strLogs[i].split(" ")[1];
			if (i == 0) {
				logs.add(new Log(type, num, 1));
			} else {
				if (logs.get(logs.size()-1).type.equals(type) && logs.get(logs.size()-1).num.equals(num)) {
					logs.get(logs.size()-1).count++;
				} else {
					logs.add(new Log(type, num, 1));
				}
			}
		}
		System.out.println(logs.size());
		for (Log log : logs) {
			if(log.count > 1) {
				System.out.printf("%s %s (%d)\n", log.type, log.num, log.count);
			} else {
				System.out.printf("%s %s\n", log.type, log.num);
			}
		}

	}
}