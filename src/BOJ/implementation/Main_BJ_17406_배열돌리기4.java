package BOJ.implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17406_배열돌리기4 {
	static int[][] arr;
	static int[][] arrCopy;
	static int order[], numbers[];
	static boolean isSelected[];
	static int[][] rotateInfo;
	static int N, M, K;
	static int ans;

	public static int getMin() {
		int rowMin = Integer.MAX_VALUE;
		int sum;
		for (int i = 0; i < N; i++) {
			sum = 0;
			for (int j = 0; j < M; j++) {
				sum += arrCopy[i][j];
			}
			rowMin = Math.min(rowMin, sum);
		}
		return rowMin;
	}

	public static void rotation(int start_r, int start_c, int end_r, int end_c) {
		int tmp = arrCopy[start_r][end_c];
		for (int c = end_c; c > start_c; c--) {
			arrCopy[start_r][c] = arrCopy[start_r][c - 1];
		}
		for (int r = start_r; r < end_r; r++) {
			arrCopy[r][start_c] = arrCopy[r + 1][start_c];
		}
		for (int c = start_c; c < end_c; c++) {
			arrCopy[end_r][c] = arrCopy[end_r][c + 1];
		}
		for (int r = end_r; r > start_r; r--) {
			arrCopy[r][end_c] = arrCopy[r - 1][end_c];
		}
		arrCopy[start_r + 1][end_c] = tmp;
	}

	public static void setOrder(int count) {// 순열로 순서 조합 만들기
		if (count == K) { // 순서 정해지면 회전 수행
			// 초기 배열 상태에서 회전!
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arrCopy[i][j] = arr[i][j];
				}
			}
			for (int i = 0; i < K; i++) {
				int r = rotateInfo[numbers[i]][0];
				int c = rotateInfo[numbers[i]][1];
				int s = rotateInfo[numbers[i]][2];
				int j = 0;
				while (true) {
					if (r - s - 1 + j == r + s - 1 - j)
						break;
					rotation(r - s - 1 + j, c - s - 1 + j, r + s - 1 - j, c + s - 1 - j);
					j++;
				}
			}
			ans = Math.min(ans, getMin());
			return;
		}
		for (int i = 0; i < order.length; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				numbers[count] = i;
				setOrder(count + 1);
				isSelected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 회전 횟수

		arr = new int[N][M];
		arrCopy = new int[N][M];
		order = new int[K];
		numbers = new int[K];
		isSelected = new boolean[K];
		rotateInfo = new int[K][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				rotateInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = Integer.MAX_VALUE;
		for (int i = 0; i < K; i++)
			order[i] = i;

		setOrder(0);

		sb.append(ans);
		System.out.println(sb.toString());
		br.close();
	}
}
