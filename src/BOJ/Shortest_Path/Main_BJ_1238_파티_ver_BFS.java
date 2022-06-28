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

public class Main_BJ_1238_파티_ver_BFS {

	static PriorityQueue<Vertex> pq;
	static ArrayList<ArrayList<RoadInfo>> roadList;

	public static class RoadInfo {
		int to, time;

		public RoadInfo(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}

	}

	public static class Vertex implements Comparable<Vertex> {
		int num, minTime;

		public Vertex(int num, int minTime) {
			super();
			this.num = num;
			this.minTime = minTime;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minTime - o.minTime;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		roadList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			roadList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			roadList.get(from).add(new RoadInfo(to, time));
		}

		int result[] = new int[N + 1];
		int goDistance[] = new int[N + 1];
		int backDistance[] = new int[N + 1];
		boolean isVisited[] = new boolean[N + 1];
		pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;
			int goDist = move(isVisited, goDistance, i, X);
			int backDist = move(isVisited, backDistance, X, i);
			result[i] = goDist + backDist;
		}
		Arrays.sort(result);
		bw.write(Integer.toString(result[N]));
		br.close();
		bw.flush();
		bw.close();
	}

	public static int move(boolean[] isVisited, int[] moveDist, int start, int end) {
		Arrays.fill(isVisited, false);
		Arrays.fill(moveDist, Integer.MAX_VALUE);
		moveDist[start] = 0;
		pq.offer(new Vertex(start, moveDist[start]));

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if (isVisited[cur.num])
				continue;
			isVisited[cur.num] = true;

			for (int j = 0, length = roadList.get(cur.num).size(); j < length; j++) {
				RoadInfo nowInfo = roadList.get(cur.num).get(j);
				int tmpTo = nowInfo.to;
				if (!isVisited[tmpTo] && moveDist[tmpTo] > moveDist[cur.num] + nowInfo.time) {
					moveDist[tmpTo] = moveDist[cur.num] + nowInfo.time;
					pq.offer(new Vertex(tmpTo, moveDist[tmpTo]));
				}
			}
		}
		return moveDist[end];
	}

}
