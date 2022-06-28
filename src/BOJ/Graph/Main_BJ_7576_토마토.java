package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_7576_토마토 {
	static int map[][];
	static int result_arr[][];

	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		result_arr = new int[n + 1][m + 1];
		Queue<Pair> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			Arrays.fill(result_arr[i], -1);
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (int j = 1; j <= m; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) {
					result_arr[i][j] = 1;
					q.offer(new Pair(i, j));
				} else if (tmp == -1)
					result_arr[i][j] = 0;
			}
		}

		int dx[] = { -1, 0, 1, 0 };
		int dy[] = { 0, -1, 0, 1 };
		int max = -1;
		int count = 0;
		while (!q.isEmpty()) {
			count += 1;
			Pair p = q.poll();
			int num = result_arr[p.x][p.y];
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx == 0 || ny == 0 || nx == n + 1 || ny == m + 1)
					continue;
				if (map[nx][ny] == -1 || result_arr[nx][ny] != -1)
					continue;
				q.offer(new Pair(nx, ny));
				result_arr[nx][ny] = num + 1;
				if (num + 1 > max)
					max = num + 1;
			}
		}

		if (max == -1) {
			if (count == 0)
				bw.write(Integer.toString(-1));
			else
				bw.write(Integer.toString(0));
		} else if (max > 1) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (result_arr[i][j] == -1) {
						max = 0;
					}
				}
			}
			bw.write(Integer.toString(max - 1));
		}
		br.close();
		bw.flush();
		bw.close();

	}
}
