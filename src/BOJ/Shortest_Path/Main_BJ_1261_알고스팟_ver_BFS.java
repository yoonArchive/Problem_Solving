package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1261_알고스팟_ver_BFS {
	static final int WALL = 1;
	static int N, M;
	static int map[][];
	static boolean isVisited[][];
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, -1, 0, 1 };

	public static class MoveInfo implements Comparable<MoveInfo> {
		int r, c, count;

		public MoveInfo(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(MoveInfo o) {
			return this.count - o.count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		isVisited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++)
				map[i][j] = line.charAt(j - 1) - '0';
		}
		bw.write(Integer.toString(move()));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int move() {
		PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
		pq.offer(new MoveInfo(1, 1, 0));
		isVisited[1][1] = true;
		int count = 0;
		while (!pq.isEmpty()) {
			MoveInfo m = pq.poll();
			int r = m.r;
			int c = m.c;
			count = m.count;
			if (r == N && c == M) {
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) || isVisited[nr][nc])
					continue;
				if (map[nr][nc] == WALL)
					pq.offer(new MoveInfo(nr, nc, count + 1));
				else
					pq.offer(new MoveInfo(nr, nc, count));
				isVisited[nr][nc] = true;
			}
		}
		return count;
	}

	private static boolean isIn(int r, int c) {
		return r >= 1 && c >= 1 && r <= N && c <= M;
	}

}
