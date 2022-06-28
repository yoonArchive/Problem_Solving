package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2512_예산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 지방의 수
		int budget[] = new int[N]; // 각 지방의 예산 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			budget[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(budget);
		int M = Integer.parseInt(br.readLine()); // 총 예산

		int low = 0;
		int high = budget[N - 1];
		long sum = 0;
		int ans = 0;
		while (low <= high) {
			sum = 0;
			int mid = (low + high) / 2;
			for (int i = 0; i < N; i++) {
				if (budget[i] < mid)
					sum += budget[i];
				else
					sum += mid;
			}
			if (sum > M)
				high = mid - 1;
			else {
				low = mid + 1;
				ans = mid;
			}
		}
		
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();

	}

}
