package BOJ.DP;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_13549_숨바꼭질3_ver_DP {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int dp[] = new int[100001];
		dp[N] = 0; // 수빈이 위치
		for (int i = N - 1; i >= 0; i--)
			dp[i] = dp[i + 1] + 1;

		for (int i = N + 1; i <= K; i++) 
			dp[i] = Math.min(dp[i - 1] + 1, dp[(i + 1) / 2] + i % 2); // i가 홀수인 경우 고려
		
		bw.write(Integer.toString(dp[K]));
		br.close();
		bw.flush();
		bw.close();
	}

}
