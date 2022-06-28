package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_3980_선발명단 {
	static final int SIZE = 11;
	static int maxAbility;
	static int[][] ability;
	static boolean[] isSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			ability = new int[SIZE][SIZE];
			isSet = new boolean[SIZE];
			for (int i = 0; i < SIZE; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < SIZE; j++)
					ability[i][j] = Integer.parseInt(st.nextToken());
			}
			maxAbility = 0;
			setPlayer(0, 0);
			sb.append(maxAbility).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void setPlayer(int position, int sum) {
		if (position == SIZE) {
			maxAbility = Math.max(maxAbility, sum);
			return;
		}
		for (int player = 0; player < SIZE; player++) {
			if (ability[player][position] == 0 || isSet[player])
				continue;
			isSet[player] = true;
			setPlayer(position + 1, sum + ability[player][position]);
			isSet[player] = false;
		}

	}

}
