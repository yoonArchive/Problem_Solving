package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3187_양치기꿍 {
	static final char WOLF = 'v';
	static final char SHEEP = 'k';
	static final char FENCE = '#';
	static final char BLANK = '.';
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, -1, 0, 1 };
	static int R, C;
	static int wolfNum, sheepNum;

	public static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char map[][] = new char[R][C];
		boolean isVisited[][] = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				char cur = line.charAt(j);
				map[i][j] = cur;
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char cur = map[i][j];
				if (cur == WOLF || cur == SHEEP) {
					bfs(map, isVisited, i, j);
					for(int k=0;k<R;k++)System.out.println(Arrays.toString(map[k]));
					System.out.println();
				}
			}
		}
		sb.append(sheepNum).append(" ").append(wolfNum);
		System.out.println(sb.toString());
		br.close();
	}

	public static void bfs(char map[][], boolean isVisited[][], int r, int c) {
		int curWolfNum = 0;
		int curSheepNum = 0;
		if (map[r][c] == WOLF) curWolfNum++;
		else curSheepNum++;
		map[r][c] = BLANK;

		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		isVisited[r][c] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && map[nr][nc] != FENCE && !isVisited[nr][nc]) {
					q.offer(new Pair(nr, nc));
					isVisited[nr][nc] = true;

					if (map[nr][nc] == WOLF) curWolfNum++;
					else if (map[nr][nc] == SHEEP) curSheepNum++;
					map[nr][nc] = BLANK;
				}
			}
		}

		if (curWolfNum >= curSheepNum)  wolfNum += curWolfNum;
		else sheepNum += curSheepNum;
	}

}
