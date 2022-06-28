package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1926_그림 {
	static int count;
	static int maxArea;
	static int n, m;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void bfs(int[][] paper, boolean[][] isVisited, int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		isVisited[r][c] = true;
		int nowCnt=1;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= n || nc >= m || isVisited[nr][nc] || paper[nr][nc] == 0)
					continue;
				q.offer(new Pair(nr, nc));
				isVisited[nr][nc] = true;
				nowCnt++;
			}
		}
		maxArea=Math.max(maxArea, nowCnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int paper[][] = new int[n][m];
		boolean isVisited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		maxArea=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!isVisited[i][j] && paper[i][j] == 1) {
					count++;
					bfs(paper, isVisited, i, j);
				}
			}
		}
		sb.append(count).append("\n").append(maxArea);
		System.out.println(sb.toString());
		br.close();
	}

}
