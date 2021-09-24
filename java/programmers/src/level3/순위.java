package level3;

import java.util.*;

public class 순위 {
	static class Player {
		int num;
		Set<Integer> better;
		Set<Integer> worse;

		public Player(int num) {
			super();
			this.num = num;
			this.better = new HashSet<>();
			this.worse = new HashSet<>();
		}

	}

	public static void main(String[] args) {
		int n = 5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		System.out.println(solution(n, results));
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;

		Player[] players = new Player[n + 1];
		for (int i = 1; i <= n; i++) {
			players[i] = new Player(i);
		}

		for (int[] result : results) {
			int winner = result[0];
			int looser = result[1];
			players[winner].worse.add(looser);
			players[looser].better.add(winner);
		}
		for (int num = 1; num <= n; num++) {
			Player p = players[num];
			boolean[] v = new boolean[n + 1];
			// 나보다 센사람
			setBetter(p, num, v, players);
			// 나보다 약한사람
			setWorse(p, num, v, players);
			
		}
		for (int num = 1; num <= n; num++) {
			Player p = players[num];
			if(p.better.size() + p.worse.size() == n - 1) {
				answer++;
			}
		}
		

		return answer;
	}

	private static void setWorse(Player p, int num, boolean[] v, Player[] players) {
		Player cur = players[num];
		for (int worsePlayer : new ArrayList<>(cur.worse)) {
			if(!v[worsePlayer]) {
				v[worsePlayer] = true;
				p.worse.add(worsePlayer);
				setWorse(p, worsePlayer, v, players);
			}
		}
	}

	private static void setBetter(Player p, int num, boolean[] v, Player[] players) {
		Player cur = players[num];
		for (int betterPlayer : new ArrayList<>(cur.better)) {
			if(!v[betterPlayer]) {
				v[betterPlayer] = true;
				p.better.add(betterPlayer);
				setBetter(p, betterPlayer, v, players);
			}
		}
	}
}
