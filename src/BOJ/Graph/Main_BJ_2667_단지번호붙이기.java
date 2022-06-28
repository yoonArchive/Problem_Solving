package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_2667_단지번호붙이기 {
	static int N;
	static int[][] map;
	static ArrayList<Integer> countList;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static class House {
		int r, c;

		public House(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		countList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					bfs(i, j);
			}
		}

		Collections.sort(countList);
		int total = countList.size();
		sb.append(total).append("\n");
		for (int i = 0; i < total; i++)
			sb.append(countList.get(i)).append("\n");
		System.out.println(sb.toString());
		br.close();

	}

	private static void bfs(int sr, int sc) {
		Queue<House> q = new LinkedList<>();
		q.offer(new House(sr, sc));
		map[sr][sc] = 0;
		int count = 1;

		while (!q.isEmpty()) {
			House house = q.poll();
			int r = house.r;
			int c = house.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) || map[nr][nc] == 0)
					continue;
				q.offer(new House(nr, nc));
				map[nr][nc] = 0;
				count++;
			}
		}
		countList.add(count);
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
