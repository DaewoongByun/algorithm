package baekjoon;
import java.util.*;

public class 부녀회장이될테야 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[][] ap = new int[15][15];
		for(int r = 0; r < 15; r++) {
			for(int c = 0; c < 15; c++) {
				if(r == 0)
					ap[r][c] = c;
				else {
					int sum = 0;
					for(int nc = 1; nc <= c ; nc++) {
						sum += ap[r-1][nc];
					}
					ap[r][c] = sum;
				}
			}
		}
		for(int t = 0; t < T; t++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			System.out.println(ap[k][n]);
		}
	}
}
