package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_3967_매직스타 {
	static final int ROWSIZE = 5;
	static final int COLSIZE = 9;
	static final int MAXNUMBER = 12;
	static final int GOAL = 26 + (4 * 'A') - 4;
	static char magicStar[][];
	static boolean isSelected[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		magicStar = new char[ROWSIZE][COLSIZE];
		isSelected = new boolean[MAXNUMBER];
		String line;
		for (int i = 0; i < ROWSIZE; i++) {
			line = br.readLine();
			for (int j = 0; j < COLSIZE; j++) {
				magicStar[i][j] = line.charAt(j);
				if (magicStar[i][j] != '.' && magicStar[i][j] != 'x')
					isSelected[magicStar[i][j] - 'A'] = true;
			}
		}
		makeMagicStar(0);
	}

	private static void makeMagicStar(int idx) {
		if (idx >= ROWSIZE * COLSIZE) {
			if (isAvailable()) {
				for (int i = 0; i < ROWSIZE; i++) {
					for (int j = 0; j < COLSIZE; j++) {
						sb.append(magicStar[i][j]);
					}
					sb.append("\n");
				}
				System.out.println(sb.toString());
				System.exit(0);
			} else
				return;
		}

		int r = idx / COLSIZE;
		int c = idx % COLSIZE;

		if (magicStar[r][c] == 'x') {
			for (int i = 0; i < MAXNUMBER; i++) {
				if (isSelected[i])
					continue;
				magicStar[r][c] = (char) (i + 'A');
				isSelected[i] = true;
				makeMagicStar(idx + 2);
				magicStar[r][c] = 'x';
				isSelected[i] = false;
			}
		} else
			makeMagicStar(idx + 2);
	}

	private static boolean isAvailable() {
		boolean canMakeMagicStar = false;
		if (magicStar[0][4] + magicStar[1][3] + magicStar[2][2] + magicStar[3][1] == GOAL
				&& magicStar[1][1] + magicStar[1][3] + magicStar[1][5] + magicStar[1][7] == GOAL
				&& magicStar[0][4] + magicStar[1][5] + magicStar[2][6] + magicStar[3][7] == GOAL
				&& magicStar[3][1] + magicStar[3][3] + magicStar[3][5] + magicStar[3][7] == GOAL
				&& magicStar[4][4] + magicStar[3][3] + magicStar[2][2] + magicStar[1][1] == GOAL
				&& magicStar[4][4] + magicStar[3][5] + magicStar[2][6] + magicStar[1][7] == GOAL)
			canMakeMagicStar = true;
		return canMakeMagicStar;
	}

}
