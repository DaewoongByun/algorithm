package level3;

import java.util.*;

public class 단어변환 {
	public int solution(String begin, String target, String[] words) {
		return bfs(begin, target, words);
	}

	private int bfs(String begin, String target, String[] words) {
		Queue<String> q = new LinkedList<>();
		Set<String> v = new HashSet<>(); // 방문 체크
		v.add(begin);
		q.offer(begin);

		int result = 0; // 단계
		while (!q.isEmpty()) {
			int size = q.size();
			for (int t = 0; t < size; t++) {
				String cur = q.poll();
				
				// 타겟이면 리턴
				if (cur.equals(target)) {
					return result;
				}
				for (String word : words) {
					// 방문 여부와 알파벳 한개 바꿔서 변환 가능한지 여부
					if (!v.contains(word) && canNext(cur, word)) {
						v.add(word);
						q.offer(word);
					}
				}
			}
			result++;
		}
		return 0;
	}

	private boolean canNext(String cur, String word) {
		int differCount = 0;
		for(int i = 0; i < cur.length(); i++) {
			if(cur.charAt(i) != word.charAt(i)) {
				differCount++;
			}
			if(differCount > 1) {
				return false;
			}
		}
		return differCount == 0 ? false : true;
	}
}
