package BOJ.BackTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_16197_두동전 {
	static final char COIN = 'o';
	static final char BLANK = '.';
	static final char WALL = '#';
	static int N, M, ans = Integer.MAX_VALUE;
	static char board[][];
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		int cnt = 0, coin1_r = 0, coin1_c = 0, coin2_r = 0, coin2_c = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				board[i][j] = c;
				if (c == COIN) {
					if (cnt == 0) {
						coin1_r = i;
						coin1_c = j;
						cnt++;
					} else {
						coin2_r = i;
						coin2_c = j;
					}
				}
			}
		}
		pressButton(coin1_r, coin1_c, coin2_r, coin2_c, 0);
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void pressButton(int c1_r, int c1_c, int c2_r, int c2_c, int count) {
		if (count > ans || count > 10) {
			return;
		}
		if (!isIn(c1_r, c1_c) && !isIn(c2_r, c2_c))
			return;
		else if ((isIn(c1_r, c1_c) && !isIn(c2_r, c2_c)) || (!isIn(c1_r, c1_c) && isIn(c2_r, c2_c))) {
			ans = Math.min(ans, count);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int n1_r = c1_r + dr[i];
			int n1_c = c1_c + dc[i];
			int n2_r = c2_r + dr[i];
			int n2_c = c2_c + dc[i];
			if (isIn(n1_r, n1_c) && board[n1_r][n1_c] == WALL) {
				n1_r = c1_r;
				n1_c = c1_c;
			}
			if (isIn(n2_r, n2_c) && board[n2_r][n2_c] == WALL) {
				n2_r = c2_r;
				n2_c = c2_c;
			}
			pressButton(n1_r, n1_c, n2_r, n2_c, count + 1);
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
