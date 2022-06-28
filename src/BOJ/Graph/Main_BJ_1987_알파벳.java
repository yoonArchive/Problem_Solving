package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1987_알파벳 {

	static int R, C, ans, count;
	static char board[][];
	static boolean isPassed[];
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { -1, 0, 1, 0 };
	static boolean isMovable;

	public static void move(int r, int c, int count) {

		isPassed[board[r][c] - 'A'] = true;

		for (int i = 0, length = dr.length; i < length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || isPassed[board[nr][nc] - 'A'])
				continue;
			move(nr, nc, count + 1);
		}

		isPassed[board[r][c] - 'A'] = false;
		ans = Math.max(ans, count);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		isPassed = new boolean[26]; // 알파벳 길이 length 배열
		for (int i = 0; i < R; i++)
			board[i] = br.readLine().toCharArray();
		ans = 0;
		count = 0;
		isMovable = true;
		move(0, 0, 1);
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();

	}

}
