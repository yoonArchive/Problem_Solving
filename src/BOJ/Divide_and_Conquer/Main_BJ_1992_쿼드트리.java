package BOJ.Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1992_쿼드트리 {
	static StringBuilder sb;
	static int map[][];

	public static boolean isSame(int r, int c, int size) {
		// 특정 범위 안 숫자들이 모두 같으면 true 반환, 다른게 있으면 false
		int pre = map[r][c];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (map[i][j] != pre)
					return false;
			}
		}
		return true;
	}

	public static void compression(int r, int c, int size) {

		if (isSame(r, c, size)) { // isSame이 true이면 시작좌표 값을 출력
			sb.append(map[r][c]);
			return;
		}

		else { // isSame이 false이면 4개로 분할하여 다시 압축
			sb.append("("); // 분할이 시작하는 곳에서 ( 출력
			for (int i = r; i < r + size; i += size / 2) {
				for (int j = c; j < c + size; j += size / 2) {
					compression(i, j, size/2);
				}
			}
			sb.append(")");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); //N X N 배열
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		compression(0, 0, N);
		System.out.println(sb.toString());
		br.close();
	}
}
