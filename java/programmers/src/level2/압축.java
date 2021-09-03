package level2;

import java.util.*;

public class 압축 {

	public static void main(String[] args) {
		System.out.println(solution("KAKAO"));
	}

	public static int[] solution(String msg) {
		List<Integer> result = new ArrayList<>();
		List<String> wordList = new ArrayList<>();
		setWordList(wordList);

		while (!msg.equals("")) {
			int[] results = findNext(wordList, msg);
			int index = results[0];
			int length = results[1];
			msg = msg.substring(length);
			result.add(index);
		}

		return result.stream().mapToInt(i -> i).toArray();
	}

	private static int[] findNext(List<String> wordList, String msg) {
		int index = 0;
		int length = 0;
		for (int i = 1; i < wordList.size(); i++) {
			if (msg.startsWith(wordList.get(i))) {
				if (wordList.get(i).length() > length) {
					length = wordList.get(i).length();
					index = i;
				}
			}
		}
		if (msg.length() > length) {
			StringBuilder sb = new StringBuilder();
			sb.append(wordList.get(index));
			sb.append(msg.charAt(length));
			wordList.add(sb.toString());
		}
		return new int[] { index, length };
	}

	public static void setWordList(List<String> wordList) {
		wordList.add("");
		for (int i = 'A'; i <= 'Z'; i++) {
			char c = (char) i;
			wordList.add(Character.toString(c));
		}
	}

}
