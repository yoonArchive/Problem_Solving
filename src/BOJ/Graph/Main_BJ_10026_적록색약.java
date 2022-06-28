package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_10026_적록색약 {
	static int n;
	static char map[][];
	static boolean visit[][];

	public static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void bfs1(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(i, j));
		visit[i][j] = true;
		char now_val = map[i][j];

		int dx[] = { 0, -1, 0, 1 };
		int dy[] = { -1, 0, 1, 0 };
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int t = 0; t < dx.length; t++) {
				int nx = p.x + dx[t];
				int ny = p.y + dy[t];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (map[nx][ny] == now_val && visit[nx][ny] == false) {
					q.offer(new Pair(nx, ny));
					visit[nx][ny] = true;
				}
			}

		}
	}

	public static void bfs2(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(i, j));
		visit[i][j] = true;
		char now_val = map[i][j];

		int dx[] = { 0, -1, 0, 1 };
		int dy[] = { -1, 0, 1, 0 };
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int t = 0; t < dx.length; t++) {
				int nx = p.x + dx[t];
				int ny = p.y + dy[t];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (now_val == 'R' || now_val == 'G') {
					if ((map[nx][ny] == 'R' || map[nx][ny] == 'G') && visit[nx][ny] == false) {
						q.offer(new Pair(nx, ny));
						visit[nx][ny] = true;
					}
				} else {
					if (map[nx][ny] == now_val && visit[nx][ny] == false) {
						q.offer(new Pair(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == false) {
					bfs1(i, j);
					count += 1;
				}
			}
		}
		bw.write(Integer.toString(count) + " ");
		for (int i = 0; i < visit.length; i++) {
			Arrays.fill(visit[i], false);
		}

		count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == false) {
					bfs2(i, j);
					count += 1;
				}
			}
		}
		bw.write(Integer.toString(count));
		br.close();
		bw.flush();
		bw.close();
	}
}
