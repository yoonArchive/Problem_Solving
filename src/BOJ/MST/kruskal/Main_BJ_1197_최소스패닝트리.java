package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_1197_최소스패닝트리 {
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	public static void makeSet(int parents[], int V) {
		for (int i = 1; i <= V; i++)
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<Edge> edgeList = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		Collections.sort(edgeList);

		int parents[] = new int[V + 1];
		makeSet(parents, V);

		int total = 0, count = 0;
		for (Edge e : edgeList) {
			if (union(parents, e.from, e.to)) {
				total += e.weight;
				if (++count == V - 1) break;
			}
		}
		bw.write(Integer.toString(total));
		br.close();
		bw.flush();
		bw.close();
	}

}
