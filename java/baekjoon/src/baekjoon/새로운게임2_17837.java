package baekjoon;

import java.util.*;
import java.io.*;

public class 새로운게임2_17837 {
	static int N, K;
	static int[] dr = new int[] { 0, 0, -1, 1 };
	static int[] dc = new int[] { 1, -1, 0, 0 };
	static int[][] map;
	static List<Unit>[][] mapUnits;
	static List<Unit> unitList;

	static class Unit {
		int id;
		int r;
		int c;
		int d;

		public Unit(int id, int r, int c, int d) {
			super();
			this.id = id;
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public void changeDirection() {
			if (d % 2 == 0) {
				d += 1;
			} else {
				d -= 1;
			}
		}

		public void move(int dir) {
			r = r + dr[dir];
			c = c + dc[dir];
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Unit other = (Unit) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "(id : " + id + " d : " + d + ")";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		K = Integer.parseInt(in[1]);
		map = new int[N][N];
		mapUnits = new List[N][N];
		unitList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(in[j]);
				mapUnits[i][j] = new ArrayList<>();
			}
		}
		for (int k = 0; k < K; k++) {
			in = br.readLine().split(" ");
			int r = Integer.parseInt(in[0]) - 1;
			int c = Integer.parseInt(in[1]) - 1;
			int d = Integer.parseInt(in[2]) - 1;
			Unit unit = new Unit(k + 1, r, c, d);
			unitList.add(unit);
			mapUnits[r][c].add(unit);
		}

		L: for (int tern = 1; tern < 2000; tern++) {
			if (tern > 1000) {
				System.out.println(-1);
				break;
			}

			for (Unit unit : unitList) {
				boolean result = moveUnit(unit);
				if (result) {
					System.out.println(tern);
					break L;
				}
			}
		}
	}

	private static boolean moveUnit(Unit unit) {
		int nr = unit.r + dr[unit.d];
		int nc = unit.c + dc[unit.d];
		int ternUnitIndex = mapUnits[unit.r][unit.c].indexOf(unit);
		List<Unit> willMoveUnits = new ArrayList<>();
		for(int i = ternUnitIndex; i < mapUnits[unit.r][unit.c].size(); i++) {
			willMoveUnits.add(mapUnits[unit.r][unit.c].get(i));
		}
		for (Unit item : willMoveUnits) {
			mapUnits[unit.r][unit.c].remove(item);
		}
		if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 2) {
			unit.changeDirection();
			nr = unit.r + dr[unit.d];
			nc = unit.c + dc[unit.d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 2) {
				nr = unit.r;
				nc = unit.c;
				mapUnits[nr][nc].addAll(willMoveUnits);
			} else {
				mapUnits[unit.r][unit.c].addAll(willMoveUnits);
				moveUnit(unit);
			}
		} else if (map[nr][nc] == 1) {
			for (int i = willMoveUnits.size() - 1; i >= 0; i--) {
				Unit item = willMoveUnits.get(i);
				item.move(unit.d);
				mapUnits[nr][nc].add(item);
			}
		} else {
			for (Unit item : willMoveUnits) {
				item.move(unit.d);
			}
			mapUnits[nr][nc].addAll(willMoveUnits);
		}
		if (mapUnits[nr][nc].size() >= 4) {
			return true;
		}
		return false;
	}

}
