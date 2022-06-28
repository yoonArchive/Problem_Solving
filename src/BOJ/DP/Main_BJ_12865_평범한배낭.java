package BOJ.DP;

import java.io.*;
import java.util.*;

class Main_BJ_12865_평범한배낭 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int arr[][] = new int[n + 1][2];
		for (int i = 1; i < arr.length; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int dp[][] = new int[n + 1][k + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if (i == 0) {
					dp[i][j] = 0;
					break;
				}
				if (j >= arr[i][0]) {
					dp[i][j] = Math.max(dp[i - 1][j], arr[i][1] + dp[i - 1][j - arr[i][0]]);
				} else
					dp[i][j] = dp[i - 1][j];
			}
		}

		bw.write(Integer.toString(dp[n][k]));
		br.close();
		bw.flush();
		bw.close();
	}
}