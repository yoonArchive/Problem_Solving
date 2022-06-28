package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1976_여행가자 {
	public static void makeSet(int[] parents, int N) {
		for (int i = 1; i <= N; i++)
			parents[i] = i;
	}

	public static int findSet(int[] parents, int n) {
		if (parents[n] == n) return n;
		return parents[n] = findSet(parents, parents[n]);
	}

	public static void union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 도시의 수
		Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시의 수 -> 뒤에서 안쓰이므로 그냥 날린다
		int parents[] = new int[N + 1];
		makeSet(parents, N);
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					union(parents, i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int root = findSet(parents, Integer.parseInt(st.nextToken()));
		boolean isPossible = true;
		while (st.hasMoreTokens()) {
			int curRoot = findSet(parents, Integer.parseInt(st.nextToken()));
			if (root != curRoot) {
				isPossible = false;
				break;
			}
			root = curRoot;
		}
		if (isPossible) bw.write("YES");
		else bw.write("NO");

		br.close();
		bw.flush();
		bw.close();

	}

}
