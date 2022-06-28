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

public class Main_BJ_1916_최소비용구하기_ver_BFS {

	public static class BusInfo {
		int to, cost;

		public BusInfo(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

	}

	public static class Vertex implements Comparable<Vertex> {
		int num, minDistance;

		public Vertex(int num, int minDistance) {
			super();
			this.num = num;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<BusInfo>> busList = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			busList.add(new ArrayList<>());
		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			busList.get(from).add(new BusInfo(to, cost));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int startNum = Integer.parseInt(st.nextToken());
		int destinationNum = Integer.parseInt(st.nextToken());

		int[] distance = new int[N + 1];
		boolean[] isVisited = new boolean[N + 1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startNum] = 0;
		pq.offer(new Vertex(startNum, distance[startNum]));

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();

			if (isVisited[cur.num])
				continue;
			isVisited[cur.num] = true;
			if (cur.num == destinationNum)
				break;

			for (int j = 0, length = busList.get(cur.num).size(); j < length; j++) {
				BusInfo infoTmp = busList.get(cur.num).get(j);
				int tmp = infoTmp.to;
				if (!isVisited[tmp] && distance[tmp] > distance[cur.num] + infoTmp.cost) {
					distance[tmp] = distance[cur.num] + infoTmp.cost;
					pq.offer(new Vertex(tmp, distance[tmp]));
				}
			}
		}
		bw.write(Integer.toString(distance[destinationNum]));
		br.close();
		bw.flush();
		bw.close();

	}

}
