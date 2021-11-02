package baekjoon;
import java.util.*;

public class 피시방알바 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0;
		boolean[] seats= new boolean[101];
		for(int i = 0; i < N; i++) {
			int numNeedSeat = sc.nextInt();
			if(seats[numNeedSeat]) 
				count++;
			else
				seats[numNeedSeat] = true;
		}
		System.out.println(count);
	}
}
