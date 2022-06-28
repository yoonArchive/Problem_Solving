package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_12851_숨바꼭질2 {
	static boolean isVisited[];
	static StringBuilder sb = new StringBuilder();

	public static class moveInfo implements Comparable<moveInfo> {
		int location, second;

		public moveInfo(int location, int second) {
			super();
			this.location = location;
			this.second = second;
		}

		@Override
		public int compareTo(moveInfo o) {
			return this.second - o.second;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치
		isVisited = new boolean[100001];
		hideAndSeek(N, K);
		System.out.println(sb.toString());
	}

	private static void hideAndSeek(int N, int K) {
		PriorityQueue<moveInfo> q = new PriorityQueue<>();
		q.offer(new moveInfo(N, 0));

		int count = 0;
		int minTime = Integer.MAX_VALUE; // 동생을 찾는 데 걸리는 최소 시간

		while (!q.isEmpty()) {
			moveInfo info = q.poll();
			int current = info.location;
			int second = info.second;
			
			if (second > minTime) // 최소 시간을 넘었다면 더 볼 필요가 없음
				break;
			
			isVisited[current] = true;

			// 처음 동생이 있는 위치에 도착
			if (count == 0 && current == K) {
				count++;
				minTime = second;
			}

			// 이미 동생이 있는 위치에 최소 시간으로 도착한 적이 있는 경우
			else if (count != 0 && current == K && second == minTime) {
				count++;
			}

			int next = current + 1;
			int nextSecond = second + 1;

			if (canGo(next))
				q.offer(new moveInfo(next, nextSecond));

			next = current - 1;
			if (canGo(next))
				q.offer(new moveInfo(next, nextSecond));

			next = current * 2;
			if (canGo(next))
				q.offer(new moveInfo(next, nextSecond));

		}

		sb.append(minTime).append("\n").append(count);

	}

	private static boolean canGo(int next) {
		return next >= 0 && next <= 100000 && !isVisited[next];
	}

}
