package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2468_안전영역 {
	static int N;
	static int heightMap[][];
	static boolean isSafetyChecked[][];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		heightMap = new int[N][N];
		isSafetyChecked = new boolean[N][N];
		int maxHeight = Integer.MIN_VALUE;
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				heightMap[i][j] = tmp;
				if (tmp >= maxHeight)
					maxHeight = tmp;
			}
		}
		
		int maxSafeArea = Integer.MIN_VALUE;
		int curSafeArea = 0;
		for (int i = 0; i <= maxHeight; i++) {
			curSafeArea = 0;
			reset();
			rain(i);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (isSafetyChecked[r][c])
						continue;
					checkSafeArea(r, c);
					curSafeArea++;
				}
			}
			maxSafeArea = Math.max(maxSafeArea, curSafeArea);
		}
		
		bw.write(Integer.toString(maxSafeArea));
		br.close();
		bw.flush();
		bw.close();
	}

	public static void reset() {
		for (int i = 0; i < N; i++)
			Arrays.fill(isSafetyChecked[i], false);
	}

	public static void rain(int limit) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (heightMap[i][j] <= limit) {
					isSafetyChecked[i][j] = true;
				}
			}
		}
	}

	public static void checkSafeArea(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || isSafetyChecked[nr][nc])
				continue;
			isSafetyChecked[nr][nc] = true;
			checkSafeArea(nr, nc);
		}
	}

}
