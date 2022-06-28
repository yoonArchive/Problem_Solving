package BOJ.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17404_RGB거리2 {
	static int INF = 99999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int cost[][] = new int[N][3];

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans[] = new int[3];
		Arrays.fill(ans, Integer.MAX_VALUE);

		for (int i = 0; i < 3; i++) { // 첫번째 색상 선택
			int startColor = i;
			int dp[][] = new int[N][3];

			for (int j = 0; j < 3; j++) { // 마지막 색상 선택
				if (j == startColor)
					dp[N - 1][j] = INF; // 마지막 색상이 첫번째 색상이 되지 못하게 임의의 큰 수 지정
				else
					dp[N - 1][j] = cost[N - 1][j];
			}

			for (int j = N - 2; j > 0; j--) { // 중간 색상 선택
				for (int k = 0; k < 3; k++) {
					dp[j][k] = Math.min(dp[j + 1][(k + 1) % 3], dp[j + 1][(k + 2) % 3]) + cost[j][k];
				}
			}
			ans[i] = Math.min(dp[1][(startColor + 1) % 3], dp[1][(startColor + 2) % 3]) + cost[0][startColor];
		}

		int min = INF;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, ans[i]);
		}
		
		bw.write(Integer.toString(min));
		br.close();
		bw.flush();
		bw.close();

	}

}
