package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_7562_나이트의이동 {
	static int dx[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int dy[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int nx, ny;
	static int map[][];
	static boolean visit[][];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void bfs(int s_x, int s_y, int g_x, int g_y) throws IOException {
		Queue<Pair> q = new LinkedList<>();
		map[s_x][s_y] = 0;
		visit[s_x][s_y] = true;
		q.offer(new Pair(s_x, s_y));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int temp_count = map[p.x][p.y];

			for (int i = 0; i < dx.length; i++) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length)
					continue;
				else {
					map[nx][ny] = Math.min(map[nx][ny], temp_count + 1);
					if (visit[nx][ny] == true)
						continue;
					else {
						visit[nx][ny] = true;
						q.offer(new Pair(nx, ny));
					}
					if (nx == g_x && ny == g_y)
						break;
				}
			}
		}
		bw.write(Integer.toString(map[g_x][g_y]));
		bw.newLine();
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			int l = Integer.parseInt(br.readLine());
			map = new int[l][l];
			for (int j = 0; j < l; j++) {
				Arrays.fill(map[j], Integer.MAX_VALUE);
			}
			visit = new boolean[l][l];
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			int goal_x = Integer.parseInt(st.nextToken());
			int goal_y = Integer.parseInt(st.nextToken());
			bfs(start_x, start_y, goal_x, goal_y);

		}
		br.close();
		bw.flush();
		bw.close();
	}
}
