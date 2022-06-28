package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_10282_해킹_ver_Dijkstra {
	static int computerCnt, dependencyCnt, hackedComputer;
	static ArrayList<ArrayList<DependencyInfo>> adjList;
	static StringBuilder sb;

	public static class DependencyInfo implements Comparable<DependencyInfo> {
		int to, time;

		public DependencyInfo(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(DependencyInfo o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			computerCnt = Integer.parseInt(st.nextToken());
			dependencyCnt = Integer.parseInt(st.nextToken());
			hackedComputer = Integer.parseInt(st.nextToken());
			adjList = new ArrayList<>();
			for (int i = 0; i <= computerCnt; i++)
				adjList.add(new ArrayList<>());
			for (int i = 0; i < dependencyCnt; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				adjList.get(b).add(new DependencyInfo(a, s));
			}
			infect(hackedComputer);
		}
		System.out.println(sb.toString());
		br.close();

	}

	private static void infect(int start) {
		PriorityQueue<DependencyInfo> pq = new PriorityQueue<>();
		int times[] = new int[computerCnt + 1];
		Arrays.fill(times, Integer.MAX_VALUE);
		times[start] = 0;
		pq.offer(new DependencyInfo(start, times[start]));
		while (!pq.isEmpty()) {
			DependencyInfo info = pq.poll();
			int to = info.to;
			int time = info.time;
			if (times[to] < time)
				continue;
			for (int i = 0, len = adjList.get(to).size(); i < len; i++) {
				DependencyInfo d = adjList.get(to).get(i);
				if (times[d.to] > times[to] + d.time) {
					times[d.to] = times[to] + d.time;
					pq.offer(new DependencyInfo(d.to, times[d.to]));
				}
			}
		}
		int cnt = 0;
		int result = 0;
		for (int i = 1; i <= computerCnt; i++) {
			if (times[i] != Integer.MAX_VALUE) {
				cnt++;
				result = Math.max(result, times[i]);
			}
		}
		sb.append(cnt).append(" ").append(result).append("\n");
	}

}
