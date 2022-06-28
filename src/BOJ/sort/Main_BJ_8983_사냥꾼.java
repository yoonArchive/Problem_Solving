package BOJ.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BJ_8983_사냥꾼 {
	static int M;
	static ArrayList<Integer> huntingGun;
	static int[][] animalLoc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		huntingGun = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++)
			huntingGun.add(Integer.parseInt(st.nextToken()));

		animalLoc = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			animalLoc[i][0] = Integer.parseInt(st.nextToken());
			animalLoc[i][1] = Integer.parseInt(st.nextToken());
		}

		Collections.sort(huntingGun);
		huntingGun.add(Integer.MAX_VALUE);

		Arrays.sort(animalLoc, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int huntCnt = 0;
		// 첫번째 동물 x좌표와 x좌표가 가장 가까운 사대 찾기
		int gunIdx = findNearestGun(animalLoc[0][0], 0);

		for (int i = 0; i < N; i++) {
			if (animalLoc[i][1] > L) continue;

			int distance = getDist(gunIdx, i);
			if (distance <= L) huntCnt++;

			else {
				gunIdx = findNearestGun(animalLoc[i][0], gunIdx);
				distance = getDist(gunIdx, i);
				if (distance <= L) huntCnt++;
				else continue;
			}
		}

		bw.write(Integer.toString(huntCnt));
		br.close();
		bw.flush();
		bw.close();
	}

	public static int findNearestGun(int animalX, int startIdx) {
		int gunIdx = -1;
		int minDiff = Integer.MAX_VALUE;
		for (int i = startIdx; i <= M; i++) {
			int diff = Math.abs(animalX - huntingGun.get(i));
			minDiff = Math.min(minDiff, diff);
			if (diff > minDiff) {
				gunIdx = i - 1;
				break;
			}
		}
		return gunIdx;
	}

	public static int getDist(int gunIdx, int animalIdx) {
		return Math.abs(huntingGun.get(gunIdx) - animalLoc[animalIdx][0]) + animalLoc[animalIdx][1];
	}

}
