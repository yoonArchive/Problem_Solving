package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_11779_최소비용구하기2_ver_Dijkstra {
	static int cityCnt, busCnt;
	static ArrayList<ArrayList<ArriveCityInfo>> list;

	public static class ArriveCityInfo implements Comparable<ArriveCityInfo> {
		int cityNo, cost;

		public ArriveCityInfo(int cityNo, int cost) {
			super();
			this.cityNo = cityNo;
			this.cost = cost;
		}

		@Override
		public int compareTo(ArriveCityInfo o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		cityCnt = Integer.parseInt(br.readLine());
		busCnt = Integer.parseInt(br.readLine());
		int costArr[] = new int[cityCnt + 1];
		int route[] = new int[cityCnt + 1];
		list = new ArrayList<>();
		for (int i = 0; i <= cityCnt; i++)
			list.add(new ArrayList<>());
		for (int i = 0; i < busCnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(from).add(new ArriveCityInfo(to, cost));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		move(start, goal, costArr, route);
		sb.append(costArr[goal]).append("\n");
		Stack<Integer> trace = new Stack<>();
		StringBuilder traceSb = new StringBuilder();
		int current = goal;
		while (current != 0) {
			trace.push(current);
			current = route[current];
		}
		sb.append(trace.size());
		System.out.println(sb.toString());
		for (int i = trace.size() - 1; i >= 0; i--)
			traceSb.append(trace.pop()).append(" ");
		System.out.println(traceSb.toString());
	}

	private static void move(int start, int goal, int[] costArr, int[] route) {
		PriorityQueue<ArriveCityInfo> pq = new PriorityQueue<>();
		Arrays.fill(costArr, Integer.MAX_VALUE);
		costArr[start] = 0;
		route[start] = 0;
		pq.offer(new ArriveCityInfo(start, costArr[start]));
		while (!pq.isEmpty()) {
			ArriveCityInfo current = pq.poll();
			int cityNo = current.cityNo;
			int cost = current.cost;
			if (cityNo == goal)
				break;
			if (costArr[cityNo] < cost)
				continue;
			for (ArriveCityInfo next : list.get(cityNo)) {
				if (costArr[next.cityNo] > cost + next.cost) {
					costArr[next.cityNo] = cost + next.cost;
					pq.offer(new ArriveCityInfo(next.cityNo, costArr[next.cityNo]));
					route[next.cityNo] = cityNo;
				}
			}
		}
	}
}
