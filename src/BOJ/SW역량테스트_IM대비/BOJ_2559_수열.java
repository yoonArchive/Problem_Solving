package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2559_수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int temperature[][] = new int[2][N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			temperature[0][i] = Integer.parseInt(st.nextToken());

		int lastIdx = N - K + 1;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < lastIdx; i++) {
			if (i == 0) {
				for (int j = 0; j < K; j++)
					temperature[1][i] += temperature[0][j];
				maxSum = temperature[1][i];
				continue;
			}
			temperature[1][i] = temperature[1][i - 1] - temperature[0][i - 1] + temperature[0][i + K - 1];
			maxSum = Math.max(maxSum, temperature[1][i]);
		}
		bw.write(Integer.toString(maxSum));
		br.close();
		bw.flush();
		bw.close();

	}

}
