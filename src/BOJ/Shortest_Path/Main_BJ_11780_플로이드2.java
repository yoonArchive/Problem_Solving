package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_11780_플로이드2 {
	static int INF = 10000001;

	public static class PathInfo implements Comparable<PathInfo> {
		int start, mid, end, cost;

		public PathInfo(int start, int mid, int end, int cost) {
			super();
			this.start = start;
			this.mid = mid;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(PathInfo o) {
			if (this.start == o.start)
				return this.end - o.end;
			return this.start - o.start;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int cost[][] = new int[n + 1][n + 1];
		ArrayList<PathInfo> list = new ArrayList<>();

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
					if (cost[start][end] > cost[start][mid] + cost[mid][end]) {
						cost[start][end] = cost[start][mid] + cost[mid][end];
						list.add(new PathInfo(start, mid, end, cost[start][end]));
					}
				}
			}
		}

		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			PathInfo p = list.get(i);
			System.out.println(p.start + " " + p.mid + " " + p.end);
		}

//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				matrixSb.append(cost[i][j] == INF ? 0 : cost[i][j]).append(" ");
//			}
//			matrixSb.append("\n");
//		}
//		

		// System.out.println(matrixSb.toString());
	}

}
