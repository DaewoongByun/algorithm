package baekjoon;

import java.io.*;
import java.util.*;

public class 청소년상어_19236 {
	static class Fish {
		int num;
		int dir;
		int r, c;
		boolean alive;

		public Fish(int num, int dir, int r, int c, boolean alive) {
			super();
			this.num = num;
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.alive = alive;
		}

		public Fish copy() {
			return new Fish(num, dir, r, c, alive);
		}

		@Override
		public String toString() {
			return "(" + num + ", " + dir + ", " + r + ", " + c + ", " + alive + ")";
		}

	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] map = new int[4][4];
	static List<Fish> fishList = new ArrayList<>();
	static Fish shark;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		for (int i = 0; i < 4; i++) {
			String[] in = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(in[j * 2]);
				int dir = Integer.parseInt(in[j * 2 + 1]);
				map[i][j] = num;
				fishList.add(new Fish(num, dir - 1, i, j, true));
			}
		}
		fishList.add(new Fish(-1, -1, -1, -1, false));
		Collections.sort(fishList, (f1, f2) -> f1.num - f2.num);
		shark = new Fish(100, 1, 1, 1, true);

		move(0);
		System.out.println(result);
	}

	private static void move(int score) {
		result = Math.max(score, result);
		for (int i = 1; i < 4; i++) {
			int nr = shark.r + dr[shark.dir] * i;
			int nc = shark.c + dc[shark.dir] * i;
			if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && fishList.get(map[nr][nc]).alive) {
				Fish tempShark = shark.copy();
				int[][] tempMap = copyMap();
				List<Fish> tempFishList = copyFishList();
				int deadFishNum = moveShark(nr, nc);
				moveFish();
				move(score + deadFishNum);
				shark = tempShark;
				map = tempMap;
				fishList = tempFishList;
			}
		}
	}

	private static int moveShark(int nr, int nc) {
		Fish deadFish = fishList.get(map[nr][nc]);
		int deadFishNum = deadFish.num;
		if (map[shark.r][shark.c] == 100)
			map[shark.r][shark.c] = 0;
		shark.r = nr;
		shark.c = nc;
		shark.dir = deadFish.dir;
		map[nr][nc] = 100;

		deadFish.alive = false;

		return deadFishNum;
	}

	private static List<Fish> copyFishList() {
		List<Fish> copy = new ArrayList<>();
		for (Fish f : fishList) {
			copy.add(f.copy());
		}
		return copy;
	}

	private static int[][] copyMap() {
		int[][] copy = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	private static void moveFish() {
		for (int n = 1; n <= 16; n++) {
			Fish fish = fishList.get(n);
			if (!fish.alive)
				continue;
			for (int tern = 0; tern < 8; tern++) {
				int newDir = (fish.dir + tern) % 8;
				int nr = fish.r + dr[newDir];
				int nc = fish.c + dc[newDir];
				if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && map[nr][nc] <= 16) {
					swap(fish.r, fish.c, nr, nc);
					fish.dir = newDir;
					break;
				}
			}
		}
	}

	private static void swap(int r, int c, int nr, int nc) {
		Fish fish1 = fishList.get(map[r][c]);
		Fish fish2 = fishList.get(map[nr][nc]);
		fish1.r = nr;
		fish1.c = nc;
		fish2.r = r;
		fish2.c = c;
		int temp = map[r][c];
		map[r][c] = map[nr][nc];
		map[nr][nc] = temp;
	}

}
