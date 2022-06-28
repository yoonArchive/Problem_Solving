package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13549_숨바꼭질3_ver_BFS {
	public static class LocationInfo {
		int loc, time;

		public LocationInfo(int loc, int time) {
			this.loc = loc;
			this.time = time;
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
		Queue<LocationInfo> q = new LinkedList<>();
		boolean isVisited[] = new boolean[100001];
		q.offer(new LocationInfo(n, 0));
		isVisited[n] = true;

		int minTime = 0;
		while (!q.isEmpty()) {
			LocationInfo locationInfo = q.poll();
			int loc = locationInfo.loc;
			int time = locationInfo.time;

			if (loc == k) {
				minTime = time;
				break;
			}
			if (loc * 2 < 100001 && !isVisited[loc * 2]) {
				q.offer(new LocationInfo(loc * 2, time));
				isVisited[loc * 2] = true;
			}
			if ((loc - 1) >= 0 && !isVisited[loc - 1]) {
				q.offer(new LocationInfo(loc - 1, time + 1));
				isVisited[loc - 1] = true;
			}
			if (loc + 1 < 100001 && !isVisited[loc + 1]) {
				q.offer(new LocationInfo(loc + 1, time + 1));
				isVisited[loc + 1] = true;
			}
		}

		return minTime;
	}

}
