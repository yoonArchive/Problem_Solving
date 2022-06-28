package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_14929_귀찮아 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] w = new int[n + 1];
		int[] sum = new int[n + 1];
		long ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			w[i] = Integer.parseInt(st.nextToken());
			sum[i] = w[i] + sum[i - 1]; // {0, x1, x1+x2, x1+x2+x3, ...}
		}
		for (int i = 1; i < n; i++)
			ans += w[i] * (sum[n] - sum[i]); // x1(x2+x3+...)+x2(x3+x4+...)+...+xn-1(xn)
		bw.write(Long.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}
}
