package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_10974_모든순열 {
	static int selected[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		selected = new int[N];
		permutation(0, 0, N);
		System.out.println(sb.toString());
		br.close();

	}

	private static void permutation(int count, int flag, int N) {
		if (count == N) {
			for (int i = 0; i < N; i++)
				sb.append(selected[i]).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if ((flag & 1 << (i - 1)) == 0) {
				selected[count] = i;
				permutation(count + 1, flag | 1 << (i - 1), N);
			}
		}

	}

}
