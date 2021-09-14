package baekjoon;

import java.util.*;
import java.io.*;

public class 게임개발_1516 {
	static int N;
	static Building[] buildings;

	static class Building {
		int num;
		int time;
		int prevCnt;
		int prevTime;
		List<Integer> nextBuildings;

		public Building(int num) {
			super();
			this.num = num;
			this.time = 0;
			this.prevCnt = 0;
			this.prevTime = 0;
			this.nextBuildings = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		buildings = new Building[N + 1];
		Queue<Building> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			buildings[i] = new Building(i);
		}
		for (int i = 1; i <= N; i++) {
			String[] in = br.readLine().split(" ");
			int time = Integer.parseInt(in[0]);
			buildings[i].time = time;

			for (int k = 1; k < in.length - 1; k++) {
				int prev = Integer.parseInt(in[k]);
				buildings[prev].nextBuildings.add(i);
				buildings[i].prevCnt++;
			}
			if (buildings[i].prevCnt == 0) {
				q.offer(buildings[i]);
			}
		}

		while (!q.isEmpty()) {
			Building cur = q.poll();
			int curBuildingFinishTime = cur.time + cur.prevTime;
			for (int nextBuildingNum : cur.nextBuildings) {
				buildings[nextBuildingNum].prevTime = Math.max(buildings[nextBuildingNum].prevTime,
						curBuildingFinishTime);
				if (--buildings[nextBuildingNum].prevCnt == 0) {
					q.offer(buildings[nextBuildingNum]);
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.println(buildings[i].prevTime + buildings[i].time);
		}
	}

}
