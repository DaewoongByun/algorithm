package baekjoon;

import java.util.*;
import java.io.*;

public class 군탈체포조 {
	static int N;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static List<int[]> deserters;
	static int[] start;
	static int result;
	static Map<String, Integer> minDistance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		deserters = new ArrayList<>();
		result = Integer.MAX_VALUE;
		minDistance = new HashMap<>();

		for (int r = 0; r < N; r++) {
			String[] in = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(in[c]);
				if (map[r][c] == 0)
					deserters.add(new int[] { r, c });
				if (map[r][c] == -1)
					start = new int[] { r, c };
			}
		}

		dfs(0, new boolean[deserters.size()], 0, start[0], start[1]);
		System.out.println(result == -1 ? 0 : result);
	}

	private static void dfs(int index, boolean[] v, int distance, int r, int c) {
		if (index == deserters.size()) {
			result = Math.min(result, distance + getDistance(r, c, start[0], start[1]));
			return;
		}
		for (int i = 0; i < deserters.size(); i++) {
			if (!v[i]) {
				v[i] = true;
				int[] to = deserters.get(i);
				int dis = getDistance(r, c, to[0], to[1]);
				dfs(index + 1, v, distance + dis, to[0], to[1]);
				v[i] = false;
			}
		}
	}

	private static int getDistance(int fromR, int fromC, int toR, int toC) {
		if (fromR == toR && fromC == toC)
			return 0;
		String key = getString(fromR, fromC, toR, toC);
		if (minDistance.containsKey(key)) {
			return minDistance.get(key);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[2] - a2[2]);
		pq.offer(new int[] { fromR, fromC, 0 });
		boolean[][] v = new boolean[N][N];
		v[fromR][fromC] = true;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			String key1 = getString(cur[0], cur[1], toR, toC);
			if (minDistance.containsKey(key1)) {
				return minDistance.get(key1) + cur[2];
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
					int newDistance = cur[2] + (map[nr][nc] < 0 ? 0 : map[nr][nc]);
					if (nr == toR && nc == toC) {
						minDistance.put(key, newDistance);
						minDistance.put(getString(toR, toC, fromR, fromC), newDistance);
						return newDistance;
					}
					v[nr][nc] = true;
					pq.offer(new int[] { nr, nc, newDistance });
					minDistance.put(getString(fromR, fromC, nr, nc), newDistance);
					minDistance.put(getString(nr, nc, fromR, fromC), newDistance);
				}
			}

		}

		return -1;
	}

	private static String getString(int fromR, int fromC, int toR, int toC) {
		int[] arr = new int[] { fromR, fromC, toR, toC };
		return arr.toString();
	}
}
