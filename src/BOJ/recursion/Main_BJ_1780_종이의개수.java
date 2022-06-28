package BOJ.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1780_종이의개수 {
	static int[][] arr;
	static int result[];

	public static boolean isSame(int r, int c, int n) { // nxn 배열의 모든 원소가 같은 숫자인지
		int pre = arr[r][c];
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (arr[i][j] != pre)
					return false;
			}
		}
		return true;
	}

	public static void divide(int r, int c, int n) {
		if (isSame(r, c, n)) { // 하나의 종이가 같은 수로만 이루어져 있다면
			if (arr[r][c] == -1)
				result[0]++; // -1로만 이루어짐
			else if (arr[r][c] == 0)
				result[1]++; // 0으로만 이루어짐
			else
				result[2]++; // 1로만 이루어짐
		} else { // 같은 수로 이루어져 있지 않다 -> 종이 분할
			int size = n / 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					divide(r + i * size, c + j * size, size);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		result = new int[3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, n);
		sb.append(result[0] + "\n" + result[1] + "\n" + result[2]);
		System.out.println(sb.toString());
		br.close();
	}

}
