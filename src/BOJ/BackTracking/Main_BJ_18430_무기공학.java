package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_18430_무기공학 {
	static int N, M, max = 0;
	static int strength[][];
	static boolean isSelected[][];
	static int dr[][] = { { 0, 1 }, { 0, 1 }, { 1, 1 }, { 1, 1 } };
	static int dc[][] = { { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		strength = new int[N][M];
		isSelected = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				strength[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getSum(0, 0);
		bw.write(Integer.toString(max));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void getSum(int index, int sum) {
		if (index == N * M) {
			max = Math.max(max, sum);
			return;
		}

		int r = index / M;
		int c = index % M;

		getSum(index + 1, sum); // 현재 칸을 선택하지 않는다.

		if (!isSelected[r][c]) { // 현재 칸을 선택한다.
			for (int d = 0; d < 4; d++) {
				int nr1 = r + dr[d][0];
				int nc1 = c + dc[d][0];
				int nr2 = r + dr[d][1];
				int nc2 = c + dc[d][1];
				if (isIn(nr1, nc1) && isIn(nr2, nc2) && !isSelected[nr1][nc1] && !isSelected[nr2][nc2]) {
					isSelected[r][c] = true;
					isSelected[nr1][nc1] = true;
					isSelected[nr2][nc2] = true;
					getSum(index + 1, sum + makeBoomerang(d, r, c, nr1, nc1, nr2, nc2));
					isSelected[r][c] = false;
					isSelected[nr1][nc1] = false;
					isSelected[nr2][nc2] = false;
				}
			}
		}
	}

	private static int makeBoomerang(int d, int r, int c, int nr1, int nc1, int nr2, int nc2) {
		if (d == 0)
			return (2 * strength[r][c]) + strength[nr1][nc1] + strength[nr2][nc2];
		else
			return (2 * strength[nr1][nc1]) + strength[r][c] + strength[nr2][nc2];
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
