package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_14502_연구소 {
	static final int BLANK = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static ArrayList<Pair> blankList, virusList;
	static int map[][];
	static int N, M;
	static int maxSafetyZone;

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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		blankList = new ArrayList<>();
		virusList = new ArrayList<>();
		// step 1. 입력값을 2차원 배열 map에 저장하며, 빈 칸의 좌표는 blankList에, 바이러스의 좌표는 virusList에 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int cur = Integer.parseInt(st.nextToken());
				map[i][j] = cur;
				if (cur == BLANK)
					blankList.add(new Pair(i, j));
				else if (cur == VIRUS)
					virusList.add(new Pair(i, j));
			}
		}
		
		// step 2. 3개의 빈 칸에 벽을 세우기 위해 3개의 빈 칸을 고른다.(조합)
		int blankLength = blankList.size(); 
		int selectedIdx[] = new int[3]; 
		maxSafetyZone = 0;
		selectIdx(selectedIdx, blankLength, 0, 0);
		
		bw.write(Integer.toString(maxSafetyZone));
		br.close();
		bw.flush();
		bw.close();

	}

	public static void selectIdx(int selectedIdx[], int blankLength, int count, int start) {
		if (count == 3) {
			// step 3. 선택된 인덱스의 좌표 값에 벽을 세운다.
			blankLength -= 3;
			buildWall(selectedIdx, blankLength); 
			return;
		}
		for (int i = start; i < blankLength; i++) {
			selectedIdx[count] = i;
			selectIdx(selectedIdx, blankLength, count + 1, i + 1);
		}
	}

	public static void buildWall(int selectedIdx[], int blankLength) {
		int tmp[][] = new int[N][M];
		copyMap(tmp);
		for (int i = 0; i < 3; i++) {
			int r = blankList.get(selectedIdx[i]).r;
			int c = blankList.get(selectedIdx[i]).c;
			tmp[r][c] = WALL;
		}
		// step 4. 바이러스를 퍼뜨린다.
		spreadVirus(tmp, blankLength);
	}

	public static void spreadVirus(int[][] tmp, int blankLength) {
		Queue<Pair> q = new LinkedList<>();
		for (int i = 0, virusNum = virusList.size(); i < virusNum; i++) {
			int virusR = virusList.get(i).r;
			int virusC = virusList.get(i).c;
			q.offer(new Pair(virusR, virusC));
		}
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && tmp[nr][nc] == BLANK ) {
					q.offer(new Pair(nr, nc));
					tmp[nr][nc] = VIRUS;
					blankLength--;
				}
			}
		}
		maxSafetyZone = Math.max(maxSafetyZone, blankLength);
	}

	public static void copyMap(int tmp[][]) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				tmp[i][j] = map[i][j];
	}

}
