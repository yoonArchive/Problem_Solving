package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2508_사탕박사고창영 {
	static final ArrayList<Character> PEEL = new ArrayList<>(Arrays.asList('>', 'v'));
	static final ArrayList<Character> PAIR = new ArrayList<>(Arrays.asList('<', '^'));
	static final char BLANK = '.';
	static final char EDIBLE = 'o';
	static final int[][] DELTA = { { 0, 1 }, { 1, 0 } };
	static int R, C;

	public static int countCandy(char[][] candyBox) {
		int candy = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				char cur = candyBox[r][c];
				if (PEEL.contains(cur)) {
					int peelIdx = PEEL.indexOf(cur);
					int nr = r, nc = c;
					for (int i = 0; i < 2; i++) {
						nr += DELTA[peelIdx][0];
						nc += DELTA[peelIdx][1];
						if (nr < R && nc < C) {
							if (i == 0) {
								if (candyBox[nr][nc] == EDIBLE) continue;
								else break;
							}
							if (i == 1) {
								if (candyBox[nr][nc] == PAIR.get(peelIdx)) {
									candyBox[nr][nc] = BLANK;
									candyBox[nr - DELTA[peelIdx][0]][nc - DELTA[peelIdx][1]] = BLANK;
									candy++;
								}
							}
						}
					}
				}
			}
		}
		return candy;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			char candyBox[][] = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					candyBox[i][j] = line.charAt(j);
				}
			}
			bw.write(Integer.toString(countCandy(candyBox)));
			bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}

}
