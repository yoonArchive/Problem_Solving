package BOJ.Shortest_Path;

import java.io.*;
import java.util.*;

public class Main_BJ_11403_경로찾기_ver_DFS {
	static int n;
	static int map[][];
	static boolean visit[];
	static int result[][];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void dfs(int i, int v) throws IOException {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 1 && visit[j] == false) {
				visit[j] = true;
				result[v][j] = 1;
				dfs(j, v);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n];
		result = new int[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			dfs(i, i);
			Arrays.fill(visit, false);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				bw.write(Integer.toString(result[i][j]) + " ");
			}
			bw.newLine();
		}

		br.close();
		bw.flush();
		bw.close();
	}
}
