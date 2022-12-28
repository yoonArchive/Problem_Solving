package BOJ.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11728_배열합치기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		int[] B = new int[M];
		int combined[] = new int[N + M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++)
			B[i] = Integer.parseInt(st.nextToken());

		// 배열 합치기
		int idx = 0, aIdx = 0, bIdx = 0;
		while (aIdx != N && bIdx != M) {
			if (A[aIdx] > B[bIdx])
				combined[idx++] = B[bIdx++];
			else
				combined[idx++] = A[aIdx++];
		}
		while (aIdx != N) {
			combined[idx++] = A[aIdx++];
		}
		while (bIdx != M) {
			combined[idx++] = B[bIdx++];
		}
		
		for (int i = 0, len = N + M; i < len; i++)
			sb.append(combined[i]).append(" ");
		System.out.println(sb.toString());
		br.close();

	}

}
