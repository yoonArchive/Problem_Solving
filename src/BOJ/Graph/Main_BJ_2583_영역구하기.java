package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_2583_영역구하기 {
	static int map[][];
	static boolean visit[][];
	static int m, n, k;
	static int count = 0;
	static int area = 0;
	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { -1, 0, 1, 0 };
	static ArrayList<Integer> list;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void bfs(int x, int y) throws IOException {
		Queue<Pair> q = new LinkedList<>();
		list = new ArrayList<>();
		for (int i = x; i < m; i++) {
			for (int j = y; j < n; j++) {
				if (visit[i][j] == false) {
					q.offer(new Pair(i, j));
					visit[i][j] = true;

					while (!q.isEmpty()) {
						Pair p = q.poll();
						count += 1;
						for (int a = 0; a < dx.length; a++) {
							int nx = p.x + dx[a];
							int ny = p.y + dy[a];
							if (nx < 0 || ny < 0 || nx >= m || ny >= n)
								continue;
							if (visit[nx][ny] == false) {
								visit[nx][ny] = true;
								q.offer(new Pair(nx, ny));
							}
						}
					}
					list.add(count);
					area += 1;
					count = 0;
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		visit = new boolean[m][n];
		int temp_x = m - 1;
		for (int i = 0; i < k; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			int left_x = Integer.parseInt(st.nextToken());
			int left_y = Integer.parseInt(st.nextToken());
			int right_x = Integer.parseInt(st.nextToken()) - 1;
			int right_y = Integer.parseInt(st.nextToken()) - 1;
			for (int a = left_x; a <= right_x; a++) {
				for (int b = left_y; b <= right_y; b++) {
					visit[temp_x - b][a] = true;
				}
			}
		}

		bfs(0, 0);
		Collections.sort(list);
		bw.write(Integer.toString(area));
		bw.newLine();
		for (int i = 0; i < list.size(); i++)
			bw.write(Integer.toString(list.get(i)) + " ");
		br.close();
		bw.flush();
		bw.close();
	}
}
