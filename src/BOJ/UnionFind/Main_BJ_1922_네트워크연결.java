package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BJ_1922_네트워크연결 {

	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		int cost[][] = new int[M][3];
		parents = new int[N + 1];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cost, new Comparator<int[]>() { // cost배열의 2열(비용) 기준 오름차순 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		makeSet(N);

		int connected = 0;
		int minCost = 0;
		for (int i = 0; i < M; i++) {
			if (connected == M)
				break;
			if (findSet(cost[i][0]) != findSet(cost[i][1])) {
				union(cost[i][0], cost[i][1]);
				minCost += cost[i][2];
				connected++;
			}
		}

		bw.write(Integer.toString(minCost));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void makeSet(int N) {
		for (int i = 1; i <= N; i++)
			parents[i] = i;
	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		else
			return parents[a] = findSet(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		else
			parents[bRoot] = aRoot;
		return true;
	}

}
