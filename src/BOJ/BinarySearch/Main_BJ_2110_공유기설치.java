package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2110_공유기설치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] pos = new int[N];
		for (int i = 0; i < N; i++)
			pos[i] = Integer.parseInt(br.readLine());
		Arrays.sort(pos);

		int low = 1; // 설치 간격의 최소 거리
		int high = pos[N - 1] - pos[0]; // 설치 간격의 최대 거리
		int ans = 0;

		while (low <= high) {
			int mid = (low + high) / 2; // low와 high의 중간으로 공유기 설치 간격 설정
			int count = 1, preInstalledIdx = 0;
			for (int i = 1; i < N; i++) {
				if (mid <= pos[i] - pos[preInstalledIdx]) { // i 번째 집과 직전에 공유기가 설치된 집의 거리가 mid보다 크거나 같으면 i번째 집에 공유기 설치가능
					count++; // 설치 갯수를 늘려주고
					preInstalledIdx = i; // 직전 설치한 집 인덱스 갱신
				}
			}
			if (count < C) // count가 C보다 작으면 간격을 줄여서 다시 설치 시도
				high = mid - 1;
			else if (count >= C) { // count가 C보다 크면 간격을 늘려서 다시 설치 시도
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