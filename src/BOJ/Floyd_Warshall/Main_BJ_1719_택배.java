package BOJ.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1719_택배 {

	static final int INF = 999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int costs[][] = new int[N + 1][N + 1];
		int route[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			Arrays.fill(costs[i], INF);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++)
				route[i][j] = j;
		}
		int v1, v2, cost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			costs[v1][v2] = cost;
			costs[v2][v1] = cost;
		}
		for (int mid = 1; mid <= N; mid++) {
			for (int start = 1; start <= N; start++) {
				if (mid == start)
					continue;
				for (int end = 1; end <= N; end++) {
					if (mid == end || start == end)
						continue;
					if (costs[start][end] > costs[start][mid] + costs[mid][end]) {
						costs[start][end] = costs[start][mid] + costs[mid][end];
						route[start][end] = route[start][mid];
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(i == j ? "-" : route[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
