package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_11725_트리의부모찾기_인접리스트 {
	static ArrayList<ArrayList<Integer>> list;
	static boolean isVisited[];
	static int parent[];
	static int N;

	public static void dfs(int start) {
		isVisited[start] = true;

		for (int i = 0; i < list.get(start).size(); i++) {
			int v = list.get(start).get(i);
			if (!isVisited[v]) {
				parent[v] = start;
				dfs(v);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		isVisited = new boolean[N + 1];
		parent = new int[N + 1];

		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<Integer>());

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int vertex1 = Integer.parseInt(st.nextToken());
			int vertex2 = Integer.parseInt(st.nextToken());
			list.get(vertex1).add(vertex2);
			list.get(vertex2).add(vertex1);
		}
		dfs(1);
		for (int i = 2; i <= N; i++)
			sb.append(parent[i] + "\n");
		System.out.println(sb.toString());

	}

}
