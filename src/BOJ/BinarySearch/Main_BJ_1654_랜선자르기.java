package BOJ.BinarySearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long cables[] = new long[K];
		for (int i = 0; i < K; i++)
			cables[i] = Long.parseLong(br.readLine());
		Arrays.sort(cables);

		long low = 1;
		long high = cables[K - 1];
		long ans = 0;

		while (low <= high) {
			long mid = (low + high) / 2;
			int count = 0;
			for (int i = 0; i < K; i++)
				count += cables[i] / mid;
			if (count < N)
				high = mid - 1;
			else {
				ans = mid;
				low = mid + 1;
			}
		}
		bw.write(Long.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

}
