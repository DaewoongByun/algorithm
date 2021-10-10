package level3;

import java.util.*;

public class 보석쇼핑 {

	public static void main(String[] args) {

	}

	public int[] solution(String[] gems) {
		Set<String> gemSet = new HashSet<>();
		for (String gem : gems) {
			gemSet.add(gem);
		}
		for (int size = gemSet.size(); size <= gems.length; size++) {
			for (int start = 0; start <= gems.length - size; start++) {
				Set<String> tempSet = new HashSet<>();
				int end = start + size - 1;
				for(int i =start; i <= end; i++) {
					tempSet.add(gems[i]);
				}
				if(tempSet.size() == gemSet.size()) {
					return new int[] {start+1, end+1};
				}
			}
		}
		return new int[] {-1, -1};
	}

}
