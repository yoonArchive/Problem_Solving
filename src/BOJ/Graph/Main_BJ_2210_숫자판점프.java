package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BJ_2210_숫자판점프 {
	static final int SIZE = 5;
	static HashSet<String> set;
	static StringBuilder sb;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void makeNum(int[][] board, int r, int c, int count) {
		if (count == 6) {
			set.add(sb.toString());
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE) {
				sb.append(board[nr][nc]);
				makeNum(board, nr, nc, count + 1);
				sb.setLength(sb.length() - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] board = new int[SIZE][SIZE];
		set = new HashSet<>();

		for (int i = 0; i < SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sb = new StringBuilder();
				sb.append(board[i][j]);
				makeNum(board, i, j, 1);
			}
		}

		bw.write(Integer.toString(set.size()));
		br.close();
		bw.flush();
		bw.close();
	}

}
