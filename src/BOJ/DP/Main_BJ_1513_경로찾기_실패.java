package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1513_경로찾기_실패 {
	static int deltas[][] = { { 1, 0 }, { 0, 1 } };
	static int N, M, C;
	static boolean canGame[][];
	static boolean isVisited[][][];
	static long path[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		canGame = new boolean[N][M]; // 해당 좌표에 오락실이 있는지 여부
		isVisited = new boolean[N][M][C + 1]; // r, c, 방문 오락실 개수
		path = new long[N][M][C + 1]; // r, c, 방문 오락실 개수
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			canGame[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				for (int k = 0; k <= C; k++)
					path[i][j][k] = -1;

		isVisited[0][0][0] = true;
		go(0, 0, 0);

		for (int i = 0; i <= C; i++)
			sb.append(path[0][0][i] % 1000007).append(" ");

		System.out.println(sb.toString());
		br.close();
	}

	private static long go(int r, int c, int count) {
		if (r == N - 1 && c == M - 1) {
			return 1;
		}

		if (path[r][c][count] != -1)
			return path[r][c][count];

		path[r][c][count] = 0;
		isVisited[r][c][count] = true;

		for (int d = 0; d < 2; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if (!isIn(nr, nc))
				continue;
			if (canGame[nr][nc] && !isVisited[nr][nc][count + 1]) { // 오락실
				path[r][c][count + 1] += go(nr, nc, count + 1);
			} else if (!canGame[nr][nc] && !isVisited[nr][nc][count]) // 오락실 x
				path[r][c][count] += go(nr, nc, count);
		}
		return path[r][c][count];

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

}
