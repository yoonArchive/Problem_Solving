package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_18352_특정거리의도시찾기_ver_BFS {
	static final int DISTANCE = 1;
	static ArrayList<ArrayList<distInfo>> list;

	public static class distInfo {
		int to, cost;

		public distInfo(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

	}

	public static void bfs(int X, int[] minCost, boolean[] isVisited) {
		Queue<distInfo> q = new LinkedList<>();
		q.offer(new distInfo(X, 0));
		isVisited[X] = true;

		while (!q.isEmpty()) {
			distInfo info = q.poll();
			int curTo = info.to;
			int curCost = info.cost;
			for (int i = 0, size = list.get(curTo).size(); i < size; i++) {
				int nextTo = list.get(curTo).get(i).to;
				int nextCost = curCost + DISTANCE;
				if (isVisited[nextTo])
					continue;
				q.offer(new distInfo(nextTo, nextCost));
				isVisited[nextTo] = true;
				minCost[nextTo] = Math.min(minCost[nextTo], nextCost);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(new distInfo(to, DISTANCE));
		}
		boolean isVisited[] = new boolean[N + 1];
		int minCost[] = new int[N + 1];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		bfs(X, minCost, isVisited);
		for (int i = 0; i <= N; i++) {
			if (minCost[i] == K)
				sb.append(i).append("\n");
		}
		if (sb.length() == 0)
			sb.append(-1);
		System.out.println(sb.toString());
		br.close();
	}

}
