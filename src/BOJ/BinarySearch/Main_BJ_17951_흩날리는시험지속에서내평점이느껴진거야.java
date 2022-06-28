package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_17951_흩날리는시험지속에서내평점이느껴진거야 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 시험지의 개수
		int K = Integer.parseInt(st.nextToken()); // 시험지를 나눌 그룹의 수
		int problems[] = new int[N];
		st = new StringTokenizer(br.readLine(), " ");

		// 시험 점수 기준
		int low = 0; // 시험지 합의 최소 점수
		int high = 0; // 시험지 합의 최대 점수

		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			problems[i] = cur;
			high += cur;
		}

		int ans = 0;
		while (low <= high) {
			int mid = (low + high) / 2; // 그룹의 최소 점수 기준

			int sum = 0, count = 0;
			for (int i = 0; i < N; i++) {
				sum += problems[i];
				if (sum >= mid) {
					count++; // 그룹 수 + 1
					sum = 0;
				}
			}

			if (count >= K) { // mid 기준으로 그룹을 나누었을 때 K 보다 크다면 mid를 높여야 한다.
				ans = mid;
				low = mid + 1;
			} else
				high = mid - 1;
		}

		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}
}