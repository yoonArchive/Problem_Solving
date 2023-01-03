package BOJ.Implementation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14891_톱니바퀴 {
	static int[][] gear;
	static boolean isRotated[];
	static int direction[];
	static final int GEARNUM = 4;
	static final int TOOTHNUM = 8;

	public static void arrMove() {
		for (int i = 1; i < GEARNUM + 1; i++) {
			if (direction[i] == 1) { // 시계방향 회전 -> 배열값들을 오른쪽으로 이동
				int tmp = gear[i][TOOTHNUM - 1];
				for (int j = TOOTHNUM - 1; j > 0; j--) {
					gear[i][j] = gear[i][j - 1];
				}
				gear[i][0] = tmp;
			} else if (direction[i] == -1) { // 반시계방향 회전 -> 배열값들을 왼쪽으로 이동
				int tmp = gear[i][0];
				for (int j = 0; j < TOOTHNUM - 1; j++) {
					gear[i][j] = gear[i][j + 1];
				}
				gear[i][TOOTHNUM - 1] = tmp;
			} else {// 0
				continue;
			}
		}
	}

	public static void rotate(int gearNum, int dir) {
		boolean rightGearRotate = false;
		int rightDir = 0;
		boolean leftGearRotate = false;
		int leftDir = 0;

		isRotated[gearNum] = true;
		direction[gearNum] = dir;

		if (gearNum < 4) { // 오른쪽 톱니바퀴가 있다면
			if (!isRotated[gearNum + 1]) {
				if (gear[gearNum][2] == gear[gearNum + 1][6]) { // 오른쪽 톱니바퀴와 극이 같으면
					rightGearRotate = false; // 오른쪽 톱니바퀴는 회전하지 않는다.
				} else { // 오른쪽 톱니바퀴와 극이 다르면
					rightDir = dir * (-1);
					rightGearRotate = true; // 오른쪽 톱니바퀴는 현재 바퀴의 반대방향으로 회전한다.
				}
			}
		}

		if (gearNum > 1) { // 왼쪽 톱니바퀴가 있다면
			if (!isRotated[gearNum - 1]) {
				if (gear[gearNum][6] == gear[gearNum - 1][2]) { // 왼쪽 톱니바퀴와 극이 같으면
					leftGearRotate = false; // 오른쪽 톱니바퀴는 회전하지 않는다.
				} else { // 왼쪽 톱니바퀴와 극이 다르면
					leftDir = dir * (-1);
					leftGearRotate = true; // 왼쪽 톱니바퀴는 현재 바퀴의 반대방향으로 회전한다.
				}
			}
		}

		if (rightGearRotate) {
			direction[gearNum + 1] = rightDir;
			rotate(gearNum + 1, rightDir);
		}
		if (leftGearRotate) {
			direction[gearNum - 1] = leftDir;
			rotate(gearNum - 1, leftDir);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		gear = new int[GEARNUM + 1][TOOTHNUM];
		String str;
		for (int i = 1; i < GEARNUM + 1; i++) {
			str = br.readLine();
			for (int j = 0; j < TOOTHNUM; j++) {
				gear[i][j] = str.charAt(j) - '0';
			}
		}
		isRotated = new boolean[GEARNUM + 1];
		direction = new int[GEARNUM + 1];
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotate(gearNum, dir);
			arrMove();
			Arrays.fill(direction, 0);
			Arrays.fill(isRotated, false);
		}

		int result = 0;
		for (int i = 1; i < GEARNUM + 1; i++) {
			if (gear[i][0] == 1)
				result += Math.pow(2, i - 1);
		}
		bw.write(Integer.toString(result));

		br.close();
		bw.flush();
		br.close();
	}

}
