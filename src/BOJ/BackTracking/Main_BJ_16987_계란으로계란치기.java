package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_16987_계란으로계란치기 {
	static int N, maxCount;
	static int[][] eggsInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		eggsInfo = new int[N][2];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			eggsInfo[i][0] = Integer.parseInt(st.nextToken()); // 내구도
			eggsInfo[i][1] = Integer.parseInt(st.nextToken()); // 무게
		}
		hit(0, 0); // 손에 든 계란의 위치, 깨진 계란 개수
		bw.write(Integer.toString(maxCount));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void hit(int order, int count) {
		if (order == N) {
			maxCount = Math.max(maxCount, count);
			return;
		}

		// 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없다면 치지않고 넘어간다.
		if (eggsInfo[order][0] <= 0 || count == N - 1)
			hit(order + 1, count);
		else {
			for (int i = 0; i < N; i++) {
				// 자기 자신이거나 이미 깨진 계란은 continue
				if (i == order || eggsInfo[i][0] <= 0)
					continue;
				// 손에 들고 있는 계란으로 깨지지 않은 계란 중 하나를 친다.
				eggsInfo[order][0] -= eggsInfo[i][1];
				eggsInfo[i][0] -= eggsInfo[order][1];
				// 한 칸 오른쪽 계란을 들고 다시 반복
				if (eggsInfo[order][0] <= 0 && eggsInfo[i][0] <= 0)
					hit(order + 1, count + 2);
				else if (eggsInfo[order][0] <= 0 || eggsInfo[i][0] <= 0)
					hit(order + 1, count + 1);
				else
					hit(order + 1, count);
				// 값 되돌려주기
				eggsInfo[order][0] += eggsInfo[i][1];
				eggsInfo[i][0] += eggsInfo[order][1];
			}
		}

	}

}
