package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기 {
	static int diceNum;
	static int dice[][];
	static int comb[][];
	static int[] pair = { 5, 3, 4, 1, 2, 0 };
	static int topNum, bottomNum;
	static int maxSideSum, result;

	public static void stack(int top_idx, int count) {
		if (count == diceNum) {
			int maxSideSum = 0;
			for (int i = 0; i < count; i++)
				maxSideSum += comb[i][3];
			result = Math.max(result, maxSideSum);
			return;
		}

		if (count == 0) {
			topNum = dice[count][top_idx];
			bottomNum = dice[count][pair[top_idx]];
		} else {
			bottomNum = topNum;
			for (int i = 0; i < 6; i++) {
				if (dice[count][i] == bottomNum) {
					top_idx = pair[i];
				}
			}
			topNum = dice[count][top_idx];
		}
		int j = 0;
		int num = 1;
		while (num <= 6) {
			if (num != topNum && num != bottomNum)
				comb[count][j++] = num;
			num++;
		}
		stack(top_idx, count + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// step1. 주사위 배열 생성 후, 면에 적힌 숫자 저장
		diceNum = Integer.parseInt(br.readLine());
		dice = new int[diceNum][6];
		StringTokenizer st;
		for (int i = 0; i < diceNum; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// step2. 주사위 쌓기 + 옆면 숫자 계산
		comb = new int[diceNum][4];
		for (int i = 0; i < 6; i++) {
			stack(i, 0); // i는 1번 주사위의 윗면 인덱스
		}
		bw.write(Integer.toString(result));

		br.close();
		bw.flush();
		bw.close();

	}

}
