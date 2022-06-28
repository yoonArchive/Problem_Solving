package BOJ.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1890_점프 {
	static int N;
	static int dr[] = { 1, 0 };
	static int dc[] = { 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		int board[][] = new int[N][N];
		long path[][] = new long[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				path[i][j] = -1;
			}
		}
		bw.write(Long.toString(go(0, 0, board, path)));

		br.close();
		bw.flush();
		bw.close();

	}

	private static long go(int r, int c, int[][] board, long[][] path) {

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++)
//				System.out.printf("%2d ", path[i][j]);
//			System.out.println();
//		}
//		System.out.println();

		if (path[r][c] != -1) // (r,c)까지 가는 경로의 개수가 이미 구해져있다면
			return path[r][c];

		if (r == N - 1 && c == N - 1)
			return 1;

		path[r][c] = 0;
		for (int d = 0; d < 2; d++) {
			int nr = r + (board[r][c] * dr[d]);
			int nc = c + (board[r][c] * dc[d]);
			if (!isIn(nr, nc))
				continue;
			path[r][c] += go(nr, nc, board, path);
		}

		return path[r][c];
	}

	private static boolean isIn(int nr, int nc) {
		return nr < N && nc < N;
	}

}
