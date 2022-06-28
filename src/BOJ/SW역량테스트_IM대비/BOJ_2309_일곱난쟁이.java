package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2309_일곱난쟁이 {
	static final int N = 7;
	static int height[];
	static boolean isSelected[];
	static int selectedHeight[] = new int[N];
	static int size;

	public static void find(int count, int start, int sum) {
		if (count == N) {
			if (sum == 100) {
				int i = 0, j = 0;
				while (i < size) {
					if (isSelected[i])
						selectedHeight[j++] = height[i];
					i++;
				}
			}
			Arrays.sort(selectedHeight);
			return;
		}

		for (int i = start; i < 9; i++) {
			isSelected[i] = true;
			find(count + 1, i + 1, sum + height[i]);
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		height = new int[9];
		size = height.length;
		for (int i = 0; i < size; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		isSelected = new boolean[9];
		find(0, 0, 0);
		for (int i = 0; i < N; i++)
			sb.append(selectedHeight[i] + "\n");
		System.out.println(sb.toString());
	}

}
