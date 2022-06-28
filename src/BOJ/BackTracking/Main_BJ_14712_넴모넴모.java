package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_14712_넴모넴모 {
	static int N, M, ans;
	static boolean nemo[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nemo = new boolean[N][M];
		countEndCase(0);
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void countEndCase(int index) {
		if (index == N * M) {
			ans++;
			return;
		}

		int r = index / M;
		int c = index % M;

		// case 1. 현재 칸을 선택하면 넴모가 생기는 경우
		if (isIn(r - 1, c - 1) && isNemmo(r, c)) {
			countEndCase(index + 1); // 선택하지 않고 넘어간다.
		}
		// case 2. 현재 칸을 선택해도 넴모가 생기지 않는 경우
		else {
			countEndCase(index + 1); // 선택하지 않고 넘어가는 경우
			nemo[r][c] = true;
			countEndCase(index + 1); // 선택하고 넘어가는 경우
			nemo[r][c] = false;
		}
	}

	private static boolean isNemmo(int r, int c) { // (r,c)를 제외한 2x2영역이 모두 true이면 true
		if (nemo[r][c - 1] && nemo[r - 1][c] && nemo[r - 1][c - 1])
			return true;
		else
			return false;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

}
