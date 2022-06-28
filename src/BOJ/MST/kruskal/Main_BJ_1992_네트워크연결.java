package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_1992_네트워크연결 {
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	public static void makeSet(int[] parents, int N) {
		for (int i = 1; i <= N; i++)
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
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int parents[] = new int[N + 1];
		makeSet(parents, N);
		ArrayList<Edge> edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		Collections.sort(edgeList);
		int minCost = 0;
		int count = 0;
		for (Edge edge : edgeList) {
			if (union(parents, edge.from, edge.to)) {
				minCost += edge.weight;
				if (++count == N - 1) break;
			}
		}
		bw.write(Integer.toString(minCost));
		br.close();
		bw.flush();
		bw.close();
	}

}
