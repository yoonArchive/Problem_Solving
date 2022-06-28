package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_18352_특정거리의도시찾기_ver_Dijkstra {
	static final int DISTANCE = 1;

	public static class distInfo {
		int to, cost;

		public distInfo(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

	}

	public static class Vertex implements Comparable<Vertex> {
		int num, mindistance;

		public Vertex(int num, int mindistance) {
			super();
			this.num = num;
			this.mindistance = mindistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.mindistance - o.mindistance;
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
		ArrayList<ArrayList<distInfo>> list = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(new distInfo(to, DISTANCE));
		}

		int distance[] = new int[N + 1];
		boolean isVisited[] = new boolean[N + 1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		pq.offer(new Vertex(X, distance[X]));

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if (isVisited[cur.num])
				continue;
			isVisited[cur.num] = true;

			for (int j = 0, length = list.get(cur.num).size(); j < length; j++) {
				distInfo infoTmp = list.get(cur.num).get(j);
				int tmp = infoTmp.to;
				if (!isVisited[tmp] && distance[tmp] > distance[cur.num] + infoTmp.cost) {
					distance[tmp] = distance[cur.num] + infoTmp.cost;
					pq.offer(new Vertex(tmp, distance[tmp]));
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (i != X && distance[i] == K)
				sb.append(i).append("\n");
		}
		if (sb.length() == 0)
			sb.append(-1);
		System.out.println(sb.toString());
		br.close();
	}

}
