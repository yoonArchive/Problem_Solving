package BOJ.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_21278_호석이두마리치킨 {
	static int N, M, minTime = Integer.MAX_VALUE;
	static boolean isConnected[][];
	static int selectedBuilding[], time[];
	static StringBuilder sb;

	public static class Pair {
		int building, cost;

		public Pair(int building, int cost) {
			super();
			this.building = building;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isConnected = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int building1 = Integer.parseInt(st.nextToken());
			int building2 = Integer.parseInt(st.nextToken());
			isConnected[building1][building2] = true;
			isConnected[building2][building1] = true;
		}
		selectedBuilding = new int[2];
		time = new int[N + 1];
		// 건물 2개 선택하기
		selectBuilding(1, 0);
		System.out.println(sb.toString());
		br.close();
	}

	private static void selectBuilding(int start, int count) {
		if (count == 2) {
			// 건물 2개 선택 완료했다면 왕복시간을 구한다.
			Arrays.fill(time, Integer.MAX_VALUE);
			getTotalTime(selectedBuilding[0]);
			getTotalTime(selectedBuilding[1]);
			time[selectedBuilding[0]] = time[selectedBuilding[1]] = 0;
			int currentTime = 0;
			for (int i = 1; i <= N; i++) {
				currentTime += time[i] * 2;
			}
			if (minTime > currentTime) {
				sb.setLength(0);
				minTime = currentTime;
				sb.append(selectedBuilding[0]).append(" ").append(selectedBuilding[1]).append(" ").append(minTime);
			}
			return;
		}
		for (int i = start; i <= N; i++) {
			selectedBuilding[count] = i;
			selectBuilding(i + 1, count + 1);
		}
	}

	private static void getTotalTime(int start) {
		Queue<Pair> q = new LinkedList<>();
		boolean isVisited[] = new boolean[N + 1];
		q.offer(new Pair(start, 1));
		isVisited[start] = true;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int building = p.building;
			int cost = p.cost;
			for (int i = 1; i <= N; i++) {
				if (!isVisited[i] && isConnected[building][i]) {
					q.offer(new Pair(i, cost + 1));
					isVisited[i] = true;
					time[i] = Math.min(time[i], cost);
				}
			}
		}
	}
}
