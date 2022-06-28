package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_2606_바이러스 {
	static int graph[][];
	static boolean visit[];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		visit = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		int result = 0;
		q.offer(1);
		visit[1] = true;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			for (int i = 1; i <= n; i++) {
				if (graph[tmp][i] == 1 && visit[i] == false) {
					q.offer(i);
					visit[i] = true;
					result += 1;
				}
			}
		}
		bw.write(Integer.toString(result));
		br.close();
		bw.flush();
		bw.close();
	}
}
