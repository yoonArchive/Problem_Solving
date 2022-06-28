package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9205_맥주마시면서걸어가기_ver_BFS {
	static final String happyState = "happy";
	static final String sadState = "sad";

	public static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static String goFestival(ArrayList<Location> list) {
		Queue<Location> queue = new LinkedList<>();
		boolean isArrived = false;
		int goalX = list.get(list.size() - 1).x; // 락 페스티벌 좌표
		int goalY = list.get(list.size() - 1).y;

		queue.offer(list.get(0));
		list.remove(0);

		/*
		 * 현재 좌표에서 1000m 이내 좌표들만 queue에 poll 이 때 락 페스티벌 좌표가 나오면 페스티벌에 도착한 것!
		 */
		while (!queue.isEmpty()) {
			if (isArrived) {
				return happyState;
			}
			Location loc = queue.poll();
			int nowX = loc.x;
			int nowY = loc.y;
			int size = list.size() - 1;
			for (int i = size; i >= 0; i--) {
				if (Math.abs((list.get(i).x - nowX)) + Math.abs(list.get(i).y - nowY) <= 1000) {
					Location tmp = list.get(i);
					if (tmp.x == goalX && tmp.y == goalY)
						isArrived = true;
					queue.offer(list.get(i));
					list.remove(i);
				}
			}
		}
		return sadState;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Location> list = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int nowX = Integer.parseInt(st.nextToken());
				int nowY = Integer.parseInt(st.nextToken());
				list.add(new Location(nowX, nowY));
			}
			sb.append(goFestival(list) + "\n");

		}
		System.out.println(sb.toString());
		br.close();
	}

}
