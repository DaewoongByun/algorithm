package baekjoon;

import java.util.*;
import java.io.*;

public class 다리만들기2_17472 {
	static int N, M, islandCount;
	static int[][] map, adjMat;
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(in[j]);
			}
		}
		setIsland();
		adjMat = new int[islandCount + 1][islandCount + 1];
		getIslandDistance();
		int result = makeSpanningTree();
		System.out.println(result);
	}

	private static int makeSpanningTree() {
		int result = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
		boolean[] visit = new boolean[islandCount + 1];
		pq.offer(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visit[cur[0]])
				continue;
			visit[cur[0]] = true;
			result += cur[1];
			if (isSpanningFinish(visit))
				break;
			for (int nextIsland = 1; nextIsland <= islandCount; nextIsland++) {
				if (!visit[nextIsland] && adjMat[cur[0]][nextIsland] > 0) {
					pq.offer(new int[] { nextIsland, adjMat[cur[0]][nextIsland] });
				}
			}
		}
		return isSpanningFinish(visit) ? result : -1;
	}

	private static boolean isSpanningFinish(boolean[] visit) {
		for (int i = 1; i <= islandCount; i++) {
			if (!visit[i])
				return false;
		}
		return true;
	}

	private static void getIslandDistance() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
							getIslandDistance(i, j, d);
						}
					}
				}
			}
		}
	}

	private static void getIslandDistance(int r, int c, int dir) {
		int length = 0;
		int islandNum = map[r][c];
		while (true) {
			r += dr[dir];
			c += dc[dir];
			if (r < 0 || r >= N || c < 0 || c >= M) {
				return;
			}
			if (map[r][c] == 0) {
				length++;
			} else if (map[r][c] != islandNum) {
				if (length < 2)
					return;
				if (adjMat[islandNum][map[r][c]] == 0) {
					adjMat[islandNum][map[r][c]] = length;
					adjMat[map[r][c]][islandNum] = length;
				} else {
					adjMat[islandNum][map[r][c]] = Math.min(adjMat[islandNum][map[r][c]], length);
					adjMat[map[r][c]][islandNum] = adjMat[islandNum][map[r][c]];
				}
				return;
			} else {
				return;
			}
		}
	}

	private static void setIsland() {
		int islandNum = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					setIsland(i, j, islandNum);
					islandNum++;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					map[i][j]--;
				}
			}
		}
		islandCount = islandNum - 2;
	}

	private static void setIsland(int r, int c, int islandNum) {
		map[r][c] = islandNum;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1) {
				setIsland(nr, nc, islandNum);
			}
		}
	}

}
