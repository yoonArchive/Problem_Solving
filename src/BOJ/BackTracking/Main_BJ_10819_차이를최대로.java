package BOJ.BackTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_10819_차이를최대로 {
	static int N, max = 0;
	static int A[], order[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		order = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		getMax(0, 0);
		bw.write(Integer.toString(max));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void getMax(int count, int flag) {
		if (count == N) {
			max = Math.max(max, getSum());
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			order[count] = A[i];
			getMax(count + 1, flag | 1 << i);
		}
	}

	private static int getSum() {
		int sum = 0;
		for (int i = 0; i <= N - 2; i++) {
			sum += Math.abs(order[i] - order[i + 1]);
		}
		return sum;
	}

}
