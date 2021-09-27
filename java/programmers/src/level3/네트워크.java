package level3;

public class 네트워크 {
	public int solution(int n, int[][] computers) {
		int answer = 0;
		boolean[] v = new boolean[n];
		for (int node = 0; node < n; node++) {
			if (!v[node]) {
				connect(n, node, computers, v);
				answer++;
			}
		}

		return answer;
	}

	private void connect(int n, int node, int[][] computers, boolean[] v) {
		v[node] = true;
		for (int next = 0; next < n; next++) {
			if (computers[node][next] == 1 && !v[next]) {
				connect(n, next, computers, v);
			}
		}
	}
}
