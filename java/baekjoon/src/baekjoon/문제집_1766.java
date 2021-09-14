package baekjoon;

import java.util.*;
import java.io.*;

public class 문제집_1766 {
	static int N, M;
	static Problem[] problems;

	static class Problem {
		int num;
		int prevCnt;
		List<Integer> nextProblem;

		public Problem(int num) {
			super();
			this.num = num;
			this.prevCnt = 0;
			this.nextProblem = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		problems = new Problem[N + 1];
		for (int i = 1; i <= N; i++)
			problems[i] = new Problem(i);

		for (int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int prev = Integer.parseInt(in[0]);
			int next = Integer.parseInt(in[1]);
			problems[prev].nextProblem.add(next);
			problems[next].prevCnt++;
		}

		StringBuilder result = new StringBuilder();
		PriorityQueue<Problem> pq = new PriorityQueue<>((p1, p2) -> p1.num - p2.num);
		for (int i = 1; i <= N; i++) {
			if (problems[i].prevCnt == 0)
				pq.offer(problems[i]);
		}
		
		while(!pq.isEmpty()) {
			Problem cur = pq.poll();
			for(int pNum : cur.nextProblem) {
				if(--problems[pNum].prevCnt == 0) {
					pq.offer(problems[pNum]);
				}
			}
			result.append(cur.num + " ");
		}
		result.setLength(result.length() - 1);
		System.out.println(result.toString());
	}

}
