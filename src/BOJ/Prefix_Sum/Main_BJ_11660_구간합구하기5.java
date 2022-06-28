package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11660_구간합구하기5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long arr[][] = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				arr[i][j] = arr[i - 1][j] + Integer.parseInt(st.nextToken());
			}
		}

		int sr = 0, sc = 0, er = 0, ec = 0;
		long sum = 0;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			for (int i = sc; i <= ec; i++)
				sum += (arr[er][i] - arr[sr - 1][i]);
			sb.append(sum).append("\n");
			sum = 0;
		}
		System.out.println(sb.toString());
		br.close();
	}

}
