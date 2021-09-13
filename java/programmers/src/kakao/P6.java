package kakao;

public class P6 {
	
	public static void main(String[] args) {
		int[][] board = new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		System.out.println(solution(board,skill));
	}
	
	public static int solution(int[][] board, int[][] skill) {
		for(int[] sk : skill) {
			set(board, sk);
		}
		return countAlive(board);
    }

	private static int countAlive(int[][] board) {
		int count = 0;
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c] > 0)
					count++;
			}
		}
		return count;
	}

	private static void set(int[][] board, int[] skill) {
		if(skill[0] == 1) {
			for(int r = skill[1]; r <= skill[3]; r++) {
				for(int c = skill[2]; c <= skill[4]; c++) {
					board[r][c] -= skill[5];
				}
			}
		} else {
			for(int r = skill[1]; r <= skill[3]; r++) {
				for(int c = skill[2]; c <= skill[4]; c++) {
					board[r][c] += skill[5];
				}
			}
		}
	}
}
