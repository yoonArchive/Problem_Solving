package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_1774_우주신과의교감 {

	public static class Loc {
		int num;
		double x;
		double y;

		Loc(int num, double x, double y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	public static void makeSet(int[] parents, int N) {
		for (int i = 1; i <= N; i++) 
			parents[i] = i;
	}

	public static int findSet(int[] parents, int n) {
		if (parents[n] == n) return n;
		return parents[n] = findSet(parents, parents[n]);
	}

	public static boolean union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] parents = new int[N + 1];
		makeSet(parents, N);

		Loc[] location = new Loc[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			location[i] = new Loc(i, x, y);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(parents, from, to);
		}

		ArrayList<Edge> edgeList = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double weight=Math.sqrt(Math.pow(location[i].x - location[j].x, 2) + Math.pow(location[i].y - location[j].y, 2));
				edgeList.add(new Edge(location[i].num, location[j].num, weight));
			}
		}
		Collections.sort(edgeList);
		double ans = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (union(parents, edge.from, edge.to)) {
				ans += edge.weight;
				union(parents, edge.from, edge.to);
			}
		}
		
		bw.write(String.format("%.2f", ans) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}