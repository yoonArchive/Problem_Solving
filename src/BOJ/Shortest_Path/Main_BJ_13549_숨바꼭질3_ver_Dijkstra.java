package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_13549_숨바꼭질3_ver_Dijkstra {
	static final int SIZE = 100001;

	public static class LocationInfo implements Comparable<LocationInfo> {
		int loc, time;

		public LocationInfo(int loc, int time) {
			this.loc = loc;
			this.time = time;
		}

		@Override
		public int compareTo(LocationInfo o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		bw.write(Integer.toString(hideAndSeek(N, K)));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int hideAndSeek(int n, int k) {
		PriorityQueue<LocationInfo> q = new PriorityQueue<>();

		int distance[] = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			distance[i] = Integer.MAX_VALUE;

		distance[n] = 0;
		q.offer(new LocationInfo(n, 0));

		int minTime = 0;
		while (!q.isEmpty()) {
			LocationInfo info = q.poll();
			int loc = info.loc;
			int time = info.time;

			if (loc == k) {
				minTime = time;
				break;
			}

			int tmp = loc * 2;
			if (tmp < SIZE && time < distance[tmp]) {
				distance[tmp] = time;
				q.offer(new LocationInfo(tmp, time));
			}

			tmp = loc - 1;
			if (tmp >= 0 && time + 1 < distance[tmp]) {
				distance[tmp] = time + 1;
				q.offer(new LocationInfo(tmp, time + 1));
			}

			tmp = loc + 1;
			if (tmp < SIZE && time + 1 < distance[tmp]) {
				distance[tmp] = time + 1;
				q.offer(new LocationInfo(tmp, time + 1));
			}

		}

		return minTime;
	}

}
