package baekjoon;
import java.util.*;
public class 수이어쓰기1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 1 ~ 9 9개, 10 ~ 99 90개, 100 ~ 999 900개, 1000 ~ 9999 9000개
		
		int result = 0;
		int count = 9;
		int jari = 1;
		
		while(N > 0) {
			if(N >= count) {
				result += jari * count;
				N -= count;
				count *= 10;
				jari++;
				
			} else {
				result += jari * N;
				N = 0;
			}
		}
		
		System.out.println(result);
	}

}
