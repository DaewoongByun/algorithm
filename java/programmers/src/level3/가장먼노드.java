package level3;

import java.util.*;

public class 가장먼노드 {

	public static void main(String[] args) {
	}

	public static int solution(int n, int[][] edge) {
		List<Integer>[] adjList = new List[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();
		for (int[] e : edge) {
			adjList[e[0]].add(e[1]);
			adjList[e[1]].add(e[0]);
		}
		int maxDistance = 0;
		Queue<Integer> maxDistanceQueue = new LinkedList<>();
		boolean[] v = new boolean[n + 1];
		v[1] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[1] == maxDistance) {
				maxDistanceQueue.offer(cur[0]);
			} else if (cur[1] > maxDistance) {
				maxDistance = cur[1];
				maxDistanceQueue.clear();
				maxDistanceQueue.offer(cur[0]);
			}
			for (int node : adjList[cur[0]]) {
				if (!v[node]) {
					q.offer(new int[] { node, cur[1] + 1 });
					v[node] = true;
				}
			}
		}

		return maxDistanceQueue.size();
	}
}
