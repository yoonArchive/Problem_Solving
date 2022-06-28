package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_20040_사이클게임 {
	public static void makeSet(int parents[], int n) {
		for (int i = 0; i < n; i++)
			parents[i] = i;
	}

	public static int findSet(int parents[], int n) {
		if (parents[n] == n) return n;
		return parents[n] = findSet(parents, parents[n]);
	}

	public static boolean union(int parents[], int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int parents[] = new int[n];
		makeSet(parents, n);
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!union(parents, a, b)) {
				sb.append(i);
				break;
			}
			if(i==m) sb.append("0");
		}
		System.out.println(sb.toString());
		br.close();

	}

}
