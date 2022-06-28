package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10157_자리배정 {
	static int C, R;
	static int dr[] = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int dc[] = { 0, 1, 0, -1 };

	public static void getSeatNum(int[][] seatNum, boolean isAssigned[][], int K) {
		int nr = R - 1, nc = 0, dir = 0;
		int num = 0;
		seatNum[nr][nc] = ++num;
		isAssigned[nr][nc] = true;

		while (true) {
			if (num == K) {
				System.out.println((nc + 1) + " " + (R - nr));
				System.exit(0);
			}
			if (num == R * C) {
				System.out.println("0");
				System.exit(0);
			}
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || isAssigned[nr][nc]) {
				nr -= dr[dir];
				nc -= dc[dir];
				if (dir < 3) dir++;
				else dir = 0;
				continue;
			}
			seatNum[nr][nc] = ++num;
			isAssigned[nr][nc] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		int seatNum[][] = new int[R][C];
		boolean isAssigned[][] = new boolean[R][C];
		getSeatNum(seatNum, isAssigned, K);
	}

}
