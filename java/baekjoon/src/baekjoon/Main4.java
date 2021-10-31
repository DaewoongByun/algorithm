package baekjoon;

import java.util.*;
import java.io.*;

public class Main4 {
	static int N, M, G;
	static List<Integer>[] adjList;
	static List<Integer>[] adjListReverse;
	static int[][] distance;
	static int x, s, t;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in[] = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		G = Integer.parseInt(in[2]);
		adjList = new List[N + 1];
		adjListReverse = new List[N + 1];
		distance = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			adjListReverse[i] = new ArrayList<>();
		}
		for (int k = 0; k < M; k++) {
			in = br.readLine().split(" ");
			int from = Integer.parseInt(in[0]);
			int to = Integer.parseInt(in[1]);
			adjList[from].add(to);
			adjListReverse[to].add(from);
		}
		in = br.readLine().split(" ");
		x = Integer.parseInt(in[0]);
		s = Integer.parseInt(in[1]);
		t = Integer.parseInt(in[2]);

		setDistance();
		System.out.println(getResult());
	}

	private static int getResult() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
		pq.offer(new int[] { s, 0 });
		boolean[] v = new boolean[N + 1];
		v[s] = true;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curLoc = cur[0];
			int curDis = cur[1];
			for (int i = 0; i < adjList[curLoc].size(); i++) {
				int to = adjList[curLoc].get(i);
				if (!v[to] && distance[curLoc][to] > 0) {
					if (to == t)
						return curDis + distance[curLoc][to];
					pq.offer(new int[] { to, curDis + distance[curLoc][to] });
					v[to] = true;
				}
			}
		}
		return -1;
	}

	private static void setDistance() {
		Queue<int[]> q = new LinkedList<>();
		// -1 : in , 1 : out
		q.offer(new int[] { x, 0, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curLoc = cur[0];
			int curDis = cur[1];
			int curDir = cur[2];

			if (curDir == 0) {
				for (int i = 0; i < adjList[curLoc].size(); i++) {
					int to = adjList[curLoc].get(i);
					if (distance[curLoc][to] == 0) {
						distance[curLoc][to] = G - curDis;
					}
					q.offer(new int[] { to, curDis + 1, 1 });
				}
				for (int i = 0; i < adjListReverse[curLoc].size(); i++) {
					int to = adjListReverse[curLoc].get(i);
					if (distance[to][curLoc] == 0)
						distance[to][curLoc] = curDis + 1 > G ? G : curDis + 1;
					q.offer(new int[] { to, curDis + 1, -1 });
				}
			} else if (curDir == -1) {
				for (int i = 0; i < adjListReverse[curLoc].size(); i++) {
					int to = adjListReverse[curLoc].get(i);
					if (distance[to][curLoc] == 0)
						distance[to][curLoc] = curDis + 1 > G ? G : curDis + 1;
					q.offer(new int[] { to, curDis + 1, -1 });
				}
			} else if (curDir == 1) {
				for (int i = 0; i < adjList[curLoc].size(); i++) {
					int to = adjList[curLoc].get(i);
					if (distance[curLoc][to] == 0)
						distance[curLoc][to] = G - curDis < 1 ? 1 : G - curDis;
					q.offer(new int[] { to, curDis + 1, 1 });
				}
			}
		}
	}
}
