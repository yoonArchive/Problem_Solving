package BOJ.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_11404_플로이드 {

	static int INF = 10000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int cost[][] = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(cost[i], INF);
			cost[i][i] = 0; // 자기자신에서 자신으로 가능 비용은 0
		}
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int curCost = Integer.parseInt(st.nextToken());
			cost[from][to] = Math.min(cost[from][to], curCost);
		}
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				if (start == mid)
					continue;
				for (int end = 1; end <= n; end++) {
					if (start == end && mid == end)
						continue;
					if (cost[start][end] > cost[start][mid] + cost[mid][end])
						cost[start][end] = cost[start][mid] + cost[mid][end];
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sb.append(cost[i][j] == INF ? 0 : cost[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
