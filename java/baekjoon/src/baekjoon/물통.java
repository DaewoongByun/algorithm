package baekjoon;

import java.util.*;

public class 물통 {
	static int[] max;
	static List<Integer> result;
	static boolean[][][] v;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		result = new ArrayList<>();
		v = new boolean[201][201][201];
		max = new int[3];
		for (int i = 0; i < 3; i++) {
			max[i] = sc.nextInt();
		}
		dfs(new int[] { 0, 0, max[2] });
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

	private static void dfs(int[] volumes) {
		if (v[volumes[0]][volumes[1]][volumes[2]]) {
			return;
		}
		if (volumes[0] == 0) {
			result.add(volumes[2]);
		}
		v[volumes[0]][volumes[1]][volumes[2]] = true;

		for (int from = 0; from < 3; from++) {
			for (int to = 0; to < 3; to++) {
				if (from == to)
					continue;
				if (volumes[from] == 0)
					continue;
				int newFromVolume = volumes[from];
				int newToVolume = volumes[to];
				int remain = max[to] - volumes[to];

				if (remain > volumes[from]) {
					newFromVolume = 0;
					newToVolume += volumes[from];
				} else {
					newFromVolume -= remain;
					newToVolume = max[to];
				}

				int[] newVolumes = Arrays.copyOf(volumes, 3);
				newVolumes[from] = newFromVolume;
				newVolumes[to] = newToVolume;
				dfs(newVolumes);
			}
		}
	}
}
