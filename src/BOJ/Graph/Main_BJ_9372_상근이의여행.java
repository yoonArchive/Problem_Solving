package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9372_상근이의여행 {

	static ArrayList<ArrayList<Integer>> country;
	static boolean[] isVisited;
	static int N, M, count;

	public static void travel() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		isVisited[1] = true;

		while (!q.isEmpty()) {
			count++;
			int cur = q.poll();
			for (int i = 0; i < country.get(cur).size(); i++) {
				int next = country.get(cur).get(i);
				if (!isVisited[next]) {
					isVisited[next] = true;
					q.offer(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			country = new ArrayList<>();
			isVisited = new boolean[N + 1];
			for (int i = 0; i <= N; i++)
				country.add(new ArrayList<Integer>());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				country.get(a).add(b);
				country.get(b).add(a);
			}
			count = 0;
			travel();
			sb.append(count - 1 + "\n");

		}
		System.out.println(sb.toString());
		br.close();

	}

}
