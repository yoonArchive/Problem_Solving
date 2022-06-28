package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1277_발전소설치_ver_BFS {
	static int N, W;
	static double M;
	static int coordinates[][];
	static ArrayList<ArrayList<Integer>> adjList;
	static PriorityQueue<ConnectInfo> pq;
	static boolean isSelected[];

	public static class ConnectInfo implements Comparable<ConnectInfo> {

		int to, wireCnt;
		double wireLength;

		public ConnectInfo(int to, int wireCnt, double wireLength) {
			super();
			this.to = to;
			this.wireCnt = wireCnt;
			this.wireLength = wireLength;
		}

		@Override
		public int compareTo(ConnectInfo o) {
			if (this.wireLength == o.wireLength)
				return o.to - this.to;
			return (int) (this.wireLength - o.wireLength);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		coordinates = new int[N + 1][2];
		adjList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			coordinates[i][0] = Integer.parseInt(st.nextToken());
			coordinates[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<>());
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
		}
		bw.write(Integer.toString((int) (connectPlant(1) * 1000)));
		br.close();
		bw.flush();
		bw.close();
	}

	private static double connectPlant(int start) {
		pq = new PriorityQueue<>();
		isSelected = new boolean[N + 1];
		double totalLen = 0;
		addConnectInfo(start, 0, 0);
		isSelected[start] = true;

		while (!pq.isEmpty()) {
			ConnectInfo c = pq.poll();
			int to = c.to;
			int wireCnt = c.wireCnt;
			double wireLength = c.wireLength;
			if (to == N) {
				totalLen = wireLength;
				break;
			}
			addConnectInfo(to, wireCnt, wireLength);
			isSelected[to] = true;
		}
		return totalLen;
	}

	private static void addConnectInfo(int start, int wc, double wl) {
		for (int i = 1; i <= N; i++) {
			if (start == i || isSelected[i])
				continue;
			if (adjList.get(start).contains(i))
				pq.offer(new ConnectInfo(i, wc, wl));
			else {
				double length = getLength(start, i);
				if (length < M && wc + 1 <= W)
					pq.offer(new ConnectInfo(i, wc + 1, wl + length));
			}
		}
	}

	private static double getLength(int to, int from) {
		return Math.sqrt(Math.pow(coordinates[to][0] - coordinates[from][0], 2)
				+ Math.pow(coordinates[to][1] - coordinates[from][1], 2));
	}

}
