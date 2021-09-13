package kakao;

import java.util.*;
import java.io.*;

public class P1 {
	static class User {
		String id;
		Set<String> reports;
		Set<String> feedBack;
		Set<String> reportMe;
		public User(String id, Set<String> reports, Set<String> feedBack, Set<String> reportMe) {
			super();
			this.id = id;
			this.reports = reports;
			this.feedBack = feedBack;
			this.reportMe = reportMe;
		}


	}

	public static void main(String[] args) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2;
		int[] result = solution(id_list, report, k);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		List<Integer> result = new ArrayList<>();
		Map<String, User> userMap = new HashMap<>();
		for (String id : id_list) {
			User user = new User(id, new HashSet<>(), new HashSet<>(), new HashSet<>());
			userMap.put(id, user);
		}
		for(String reportString : report) {
			String[] in = reportString.split(" ");
			String from = in[0];
			String to = in[1];
			userMap.get(from).reports.add(to);
			userMap.get(to).reportMe.add(from);
		}
		
		for(String id : id_list) {
			if(userMap.get(id).reportMe.size() >= k) {
				List<String> reportMeList = new ArrayList<>(userMap.get(id).reportMe);
				for(String reporterId : reportMeList) {
					userMap.get(reporterId).feedBack.add(id);
				}
			}
		}
		for(String id : id_list) {
			result.add(userMap.get(id).feedBack.size());
		}
		
		return result.stream().mapToInt(i -> i).toArray();
	}

}
