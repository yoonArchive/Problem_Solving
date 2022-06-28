package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2304_창고다각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int stick[][] = new int[N][2];
		StringTokenizer st;
		// step01. 기둥 배열 생성 및 가장 높은 기둥의 정보 저장
		int max_y = 0;
		int max_x = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			stick[i][0] = Integer.parseInt(st.nextToken());
			stick[i][1] = Integer.parseInt(st.nextToken());
			if (stick[i][1] > max_y) {
				max_y = stick[i][1];
				max_x = stick[i][0];
			}
		}
		// step02. 2차원 배열 0번째 인덱스 기준 오름차순 정렬
		Arrays.sort(stick, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		// step03. 면적 계산
		// step03-1. 최대 높이 기둥 기준 왼쪽을 탐색하며 면적 더하기
		int area = 0;
		int current_x = 0;
		int current_y = 0;
		for (int i = 0; i < N; i++) {
			if (stick[i][0] > max_x) {
				break;
			}
			if (i == 0) {
				current_x = stick[0][0];
				current_y = stick[0][1];
				continue;
			} else {
				if (stick[i][1] >= current_y) {
					area += (stick[i][0] - current_x) * current_y;
					current_x = stick[i][0];
					current_y = stick[i][1];
					continue;
				}
			}
		}
		// step03-2. 최대 높이 기둥 기준 오른쪽을 탐색하며 면적 더하기
		for (int i = N - 1; i >= 0; i--) {
			if (stick[i][0] < max_x)
				break;
			if (i == N - 1) {
				current_x = stick[N - 1][0] + 1;
				current_y = stick[N - 1][1];
				continue;
			} else {
				if (stick[i][1] >= current_y) {
					area += (current_x - (stick[i][0] + 1)) * current_y;
					current_x = stick[i][0] + 1;
					current_y = stick[i][1];
					continue;
				}
			}
		}
		if (N == 1)
			bw.write(Integer.toString(max_y));
		else
			bw.write(Integer.toString(area + max_y));

		br.close();
		bw.flush();
		bw.close();

	}

}
