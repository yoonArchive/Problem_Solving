package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14938_서강그라운드_ver_Dijkstra {
	static int n, m, r;
	static int itemCnts[];
	static ArrayList<ArrayList<Region>> adjList;

	public static class Region implements Comparable<Region> {
		int no, distance;

		public Region(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}

		@Override
		public int compareTo(Region o) {
			return this.distance - o.distance;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		itemCnts = new int[n + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++)
			itemCnts[i] = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			adjList.add(new ArrayList<>());
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			adjList.get(a).add(new Region(b, l));
			adjList.get(b).add(new Region(a, l));
		}
		int maxCnt = 0;
		for (int i = 1; i <= n; i++)
			maxCnt = Math.max(maxCnt, fall(i));
		bw.write(Integer.toString(maxCnt));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int fall(int start) {
		PriorityQueue<Region> pq = new PriorityQueue<>();
		int dist[] = new int[n + 1];
		boolean isVisited[] = new boolean[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Region(start, dist[start]));

		while (!pq.isEmpty()) {
			Region r = pq.poll();
			int no = r.no;

			if (isVisited[no])
				continue;
			isVisited[no] = true;

			for (int i = 0, len = adjList.get(no).size(); i < len; i++) {
				Region current = adjList.get(no).get(i);
				if (!isVisited[current.no] && dist[current.no] > dist[no] + current.distance) {
					dist[current.no] = dist[no] + current.distance;
					pq.offer(new Region(current.no, dist[current.no]));
				}
			}
		}

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] <= m)
				sum += itemCnts[i];
		}
		return sum;
	}

}
