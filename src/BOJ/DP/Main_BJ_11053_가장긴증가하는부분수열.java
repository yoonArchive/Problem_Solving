package BOJ.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[2][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
			arr[1][i] = 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if ((arr[0][i] > arr[0][j]) && (arr[1][i] <= arr[1][j]))
					arr[1][i] = arr[1][j] + 1;
			}
		}

		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, arr[1][i]);

		bw.write(Integer.toString(max));
		br.close();
		bw.flush();
		bw.close();
	}

}
