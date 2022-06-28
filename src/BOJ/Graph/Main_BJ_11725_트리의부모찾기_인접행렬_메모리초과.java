package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11725_트리의부모찾기_인접행렬_메모리초과 {
	static int n;
	static int map[][];
	static boolean visit[];
	static int parent[];

	public static void dfs(int start) throws IOException {
		visit[start] = true;
		for (int i = 1; i <= n; i++) {
			if (map[start][i] == 1 && visit[i] == false) {
				parent[i] = start;
				dfs(i);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		visit = new boolean[n + 1];
		parent = new int[n + 1];
		for (int i = 1; i < n; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		dfs(1);
		for (int i = 2; i < parent.length; i++) {
			sb.append(parent[i]+"\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}