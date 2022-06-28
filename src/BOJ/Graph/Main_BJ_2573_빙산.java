package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2573_빙산 {
	static int N, M, iceCnt;
	static int[][] iceberg;
	static boolean[][] isVisited;
	static Queue<Node> queue;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static class Node {
		int r, c, height;

		public Node(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iceberg = new int[N][M];
		isVisited = new boolean[N][M];
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				iceberg[i][j] = tmp;
				if (tmp != 0) {
					iceCnt++;
					queue.offer(new Node(i, j, tmp));
				}
			}
		}

		int cnt = 1, year = 0;
		while (iceCnt != 0) {
			for (int i = 0; i < N; i++)
				Arrays.fill(isVisited[i], false);
			cnt = 0;
			year++;
			melt();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (iceberg[i][j] != 0 && !isVisited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			if (cnt >= 2)
				break;
		}
		if (cnt < 2)
			year = 0;
		bw.write(Integer.toString(year));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void melt() {
		int qSize = queue.size();
		int r = 0, c = 0, h = 0;
		while (qSize-- > 0) {
			Node n = queue.poll();
			r = n.r;
			c = n.c;
			h = n.height;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isIn(nr, nc) && iceberg[nr][nc] == 0)
					h--;
			}
			if (h <= 0) {
				h = 0;
				iceCnt--;
			}
			queue.offer(new Node(r, c, h));
		}
		updateIceberg();
	}

	private static void updateIceberg() {
		int qSize = queue.size(), r = 0, c = 0, h = 0;
		while (qSize-- > 0) {
			Node n = queue.poll();
			r = n.r;
			c = n.c;
			h = n.height;
			iceberg[r][c] = h;
			if (h != 0)
				queue.offer(new Node(r, c, h));
		}

	}

	private static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		isVisited[r][c] = true;
		q.offer(new Node(r, c, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				if (isIn(nr, nc) && iceberg[nr][nc] != 0 && !isVisited[nr][nc]) {
					isVisited[nr][nc] = true;
					q.offer(new Node(nr, nc, 0));
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
