package baekjoon;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class 최소비용구하기2_11779 {
	static int N, M;
	static int start, destination;
	static List<int[]>[] adjList;

	static class Info {
		int location;
		int cost;
		List<Integer> wayPoints;

		public Info(int location, int cost, List<Integer> wayPoints) {
			super();
			this.location = location;
			this.cost = cost;
			this.wayPoints = wayPoints;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int k = 0; k < M; k++) {
			String[] in = br.readLine().split(" ");
			int from = Integer.parseInt(in[0]);
			int to = Integer.parseInt(in[1]);
			int cost = Integer.parseInt(in[2]);
			adjList[from].add(new int[] { to, cost });
		}
		String[] in = br.readLine().split(" ");
		start = Integer.parseInt(in[0]);
		destination = Integer.parseInt(in[1]);

		Info result = dijkstra();
		System.out.println(result.cost);
		System.out.println(result.wayPoints.size());
		for (int i = 0; i < result.wayPoints.size(); i++) {
			if (i == result.wayPoints.size() - 1) {
				System.out.println(result.wayPoints.get(i));
			} else {
				System.out.print(result.wayPoints.get(i) + " ");
			}
		}
	}

	private static Info dijkstra() {
		PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.cost - i2.cost);
		List<Integer> wayPoints = new ArrayList<>();
		wayPoints.add(start);
		pq.offer(new Info(start, 0, wayPoints));
		boolean[] v = new boolean[N + 1];

		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			v[cur.location] = true;
			if (cur.location == destination) {
				return cur;
			}
			for (int[] to : adjList[cur.location]) {
				if (!v[to[0]]) {
					Info newInfo = new Info(to[0], to[1] + cur.cost, addToList(cur.wayPoints, to[0]));
					pq.offer(newInfo);
				}
			}
		}

		return null;
	}

	private static List<Integer> addToList(List<Integer> wayPoints, int wayPoint) {
		List<Integer> copy = (ArrayList<Integer>) wayPoints.stream().collect(Collectors.toList());
		copy.add(wayPoint);
		return copy;
	}

}
