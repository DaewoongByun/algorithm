package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 순위검색 {

	public static void main(String[] args) {
		String[] info = new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = new String[] { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(Arrays.toString(solution(info, query)));
	}

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		List<Integer>[][][][] scores = new List[3][2][2][2];
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 2; b++) {
				for (int c = 0; c < 2; c++) {
					for (int d = 0; d < 2; d++) {
						scores[a][b][c][d] = new ArrayList<>();
					}
				}
			}
		}

		for (String i : info) {
			add(scores, i);
		}

		for (int i = 0; i < query.length; i++) {
			String q = query[i];
			answer[i] = sCount(scores, q);
		}
		return answer;
	}

	private static int sCount(List<Integer>[][][][] scores, String query) {
		int count = 0;
		String[] tokens = query.split(" and ");
		String language = tokens[0];
		String position = tokens[1];
		String level = tokens[2];
		String soulFood;
		int score;

		String[] last = tokens[3].split(" ");
		soulFood = last[0];
		score = last.length > 1 ? Integer.parseInt(last[1]) : 0;

		List<Integer> languageIndex = new ArrayList<>();
		List<Integer> positionIndex = new ArrayList<>();
		List<Integer> levelIndex = new ArrayList<>();
		List<Integer> soulFoodIndex = new ArrayList<>();

		if (getIndex(language) >= 0) {
			languageIndex.add(getIndex(language));
		} else {
			languageIndex.add(0);
			languageIndex.add(1);
			languageIndex.add(2);
		}
		if (getIndex(position) >= 0) {
			positionIndex.add(getIndex(position));
		} else {
			positionIndex.add(0);
			positionIndex.add(1);
		}
		if (getIndex(level) >= 0) {
			levelIndex.add(getIndex(level));
		} else {
			levelIndex.add(0);
			levelIndex.add(1);
		}
		if (getIndex(soulFood) >= 0) {
			soulFoodIndex.add(getIndex(soulFood));
		} else {
			soulFoodIndex.add(0);
			soulFoodIndex.add(1);
		}
		
		for(int i1 : languageIndex) {
			for(int i2 : positionIndex) {
				for(int i3 : levelIndex) {
					for(int i4 : soulFoodIndex) {
						count += scores[i1][i2][i3][i4].stream().filter(s->s >= score).count();
					}	
				}	
			}	
		}

		return count;
	}

	private static void add(List<Integer>[][][][] scores, String s) {
		String[] tokens = s.split(" ");
		int languageIndex = getIndex(tokens[0]);
		int positionIndex = getIndex(tokens[1]);
		int levelIndex = getIndex(tokens[2]);
		int soulFoodIndex = getIndex(tokens[3]);
		int score = Integer.parseInt(tokens[4]);

		scores[languageIndex][positionIndex][levelIndex][soulFoodIndex].add(score);
	}

	private static int getIndex(String s) {
		if (s.equals("cpp") || s.equals("backend") || s.equals("junior") || s.equals("chicken")) {
			return 0;
		}
		if (s.equals("java") || s.equals("frontend") || s.equals("senior") || s.equals("pizza")) {
			return 1;
		}
		if (s.equals("python"))
			return 2;
		return -1;
	}

}
