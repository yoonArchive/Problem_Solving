package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_19942_다이어트 {
	static int N, minSum;
	static int[] minNutrients, selected, selectedSum, ans;
	static int[][] ingredientsInfo;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		ans = new int[N];
		selected = new int[N];
		selectedSum = new int[4];
		minNutrients = new int[4];
		ingredientsInfo = new int[N + 1][5];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++)
			minNutrients[i] = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				ingredientsInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minSum = Integer.MAX_VALUE;
		Arrays.fill(ans, Integer.MAX_VALUE);
		selectIngredients(0, 1, 0);
		if (minSum == Integer.MAX_VALUE)
			sb.append(-1);
		else {
			sb.append(minSum).append("\n");
			for (int i = 0; i < N; i++) {
				int val = ans[i];
				if (val == 0)
					break;
				sb.append(val).append(" ");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void selectIngredients(int count, int start, int priceSum) {
		if (start == N + 1) {
			if (isAvailable() && priceSum <= minSum) {
				for (int i = 0; i < N; i++)
					if (selected[i] > ans[i])
						return;
				ans = selected.clone();
				minSum = priceSum;
			}
			return;
		}
		for (int i = start; i <= N; i++) {
			for (int j = 0; j < 4; j++) {
				selected[count] = i;
				selectedSum[j] += ingredientsInfo[i][j];
			}
			selectIngredients(count + 1, i + 1, priceSum + ingredientsInfo[i][4]);
			selected[count] = 0;
			for (int j = 0; j < 4; j++)
				selectedSum[j] -= ingredientsInfo[i][j];
			selectIngredients(count, i + 1, priceSum);
		}

	}

	private static boolean isAvailable() {
		for (int i = 0; i < 4; i++)
			if (selectedSum[i] < minNutrients[i])
				return false;
		return true;
	}

}