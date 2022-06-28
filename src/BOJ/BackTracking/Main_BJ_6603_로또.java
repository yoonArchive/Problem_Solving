package BOJ.BackTracking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_6603_로또 {

	static int k;
	static int S[], selected[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			S = new int[k];
			selected = new int[6];
			for (int i = 0; i < k; i++)
				S[i] = Integer.parseInt(st.nextToken());

			combination(0, 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void combination(int start, int count) {
		if (count == 6) {
			for (int num : selected)
				sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = start; i < k; i++) {
			selected[count] = S[i];
			combination(i + 1, count + 1);
		}

	}

}