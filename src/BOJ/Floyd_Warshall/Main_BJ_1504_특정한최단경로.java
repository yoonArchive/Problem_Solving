package BOJ.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1504_특정한최단경로 {

	static final int INF = 99999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int weight[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(weight[i], INF);
			weight[i][i] = 0;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			weight[a][b] = weight[b][a] = c;
		}
		st = new StringTokenizer(br.readLine(), " ");
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		for (int mid = 1; mid <= N; mid++) {
			for (int start = 1; start <= N; start++) {
				if (start == mid)
					continue;
				for (int end = 1; end <= N; end++) {
					if (start == end && mid == end)
						continue;
					if (weight[start][end] > weight[start][mid] + weight[mid][end])
						weight[start][end] = weight[start][mid] + weight[mid][end];
				}
			}
		}
		int start = 1;
		int end = N;
		int minCost = Math.min(weight[start][v1] + weight[v1][v2] + weight[v2][end],
				weight[start][v2] + weight[v2][v1] + weight[v1][end]);
		if (minCost >= INF)
			bw.write(Integer.toString(-1));
		else
			bw.write(Integer.toString(minCost));
		br.close();
		bw.flush();
		bw.close();
	}
}
