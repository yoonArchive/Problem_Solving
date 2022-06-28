package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14696_딱지놀이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] shape = new int[2][5];

		for (int i = 0; i < N; i++) {
			boolean aWin = false;
			boolean isDraw = false;
			for (int r = 0; r < shape.length; r++)
				Arrays.fill(shape[r], 0);

			for (int j = 0; j < 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				for (int k = 0; k < num; k++) {
					shape[j][Integer.parseInt(st.nextToken())]++;
				}
			}

			int shape_num = 4;
			while (true) {
				if (shape_num < 1) {
					isDraw = true;
					break;
				}
				if (shape[0][shape_num] > shape[1][shape_num]) {
					aWin = true;
					break;
				} else if (shape[0][shape_num] < shape[1][shape_num]) {
					aWin = false;
					break;
				} else
					shape_num--;
			}

			if (isDraw) {
				sb.append("D\n");
				continue;
			}
			if (aWin) {
				sb.append("A\n");
				continue;
			} else {
				sb.append("B\n");
				continue;
			}
		}
		System.out.println(sb.toString());
	}

}
