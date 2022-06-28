package BOJ.BinarySearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int trees[] = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			trees[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(trees);

		int low = 1;
		int high = trees[N - 1];
		int ans = 0;
		while (low <= high) {
			int mid = (low + high) / 2; // 절단기의 높이를 mid로 지정
			long sum = 0;
			for (int i = 0; i < N; i++) {
				int cut = trees[i] - mid;
				if (cut > 0)
					sum += cut;
			}
			if (sum < M)
				high = mid - 1;
			else {
				ans = mid;
				low = mid + 1;
			}
		}
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

}
