package BOJ.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_2579_계단오르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int stairs[] = new int[N + 1];
		int dp[] = new int[N + 1];
		for (int i = 1; i <= N; i++)
			stairs[i] = Integer.parseInt(br.readLine());
		dp[1] = stairs[1];	
		if (N == 1)
			bw.write(Integer.toString(dp[1]));
		else {
			dp[2] = stairs[1] + stairs[2];
			if (N == 2)
				bw.write(Integer.toString(dp[2]));
			else {
				for (int i = 3; i <= N; i++) {
					dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
				}
				bw.write(Integer.toString(dp[N]));
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}

}
