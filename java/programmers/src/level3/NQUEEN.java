package level3;

public class NQUEEN {
	static int result = 0;
	public static void main(String[] args) {
		System.out.println(solution(5));
	}
	public static int solution(int n) {
		boolean[][] v = new boolean[n][n];
		for (int c = 0; c < n; c++) {
			go(v, 0, c);
		}
		return result;
	}

	private static void go(boolean[][] v, int r, int c) {
		if (r == v.length - 1) {
			result++;
			return;
		}
		v[r][c] = true;
		for (int nc = 0; nc < v.length; nc++) {
			if (check(v, r + 1, nc)) {
				go(v, r + 1, nc);
			}
		}
		v[r][c] = false;
	}

	private static boolean check(boolean[][] v, int r, int c) {
		int[] dr = { -1, -1, 1, 1 };
		int[] dc = { -1, 1, -1, 1 };
		// 가로 체크
		for (int nc = 0; nc < v.length; nc++) {
			if (v[r][nc])
				return false;
		}
		// 세로 체크
		for (int nr = 0; nr < v.length; nr++) {
			if (v[nr][c])
				return false;
		}
		// 대각선 체크
		for(int d = 0; d < 4; d++) {
			for(int k = 1; k < v.length; k++) {
				int nr = r + dr[d] * k;
				int nc = c + dc[d] * k;
				if(nr < 0 || nr >= v.length || nc < 0 || nc >= v.length) {
					break;
				}
				if(v[nr][nc]) return false;
			}
		}
		return true;
	}
}
