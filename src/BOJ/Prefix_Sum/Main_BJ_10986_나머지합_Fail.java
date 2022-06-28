package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_10986_나머지합_Fail {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] arr = new long[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if ((arr[i] - arr[j]) % M == 0)
					cnt++;
			}
		}
		bw.write(Integer.toString(cnt));
		br.close();
		bw.flush();
		bw.close();

	}

}
