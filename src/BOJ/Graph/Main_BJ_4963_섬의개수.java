package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_4963_섬의개수 {
	static final int ISLAND = 1;
	static final int SEA = 0;
	static final int CHECKED = -1;
	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int w, h;

	public static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static int checkIsland(int[][] map, int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		map[r][c] = CHECKED;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0, length = dr.length; i < length; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr >= 0 && nc >= 0 && nr < h && nc < w && map[nr][nc] == ISLAND) {
					q.offer(new Pair(nr, nc));
					map[nr][nc] = CHECKED;
				}
			}
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			int map[][] = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == ISLAND)
						result += checkIsland(map, i, j);
					else map[i][j] = CHECKED;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());

	}

}
