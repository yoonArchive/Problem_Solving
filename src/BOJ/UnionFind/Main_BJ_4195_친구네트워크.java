package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BJ_4195_친구네트워크 {
	static HashMap<String, String> idParentsMap;
	static HashMap<String, Integer> countMap;

	public static String findSet(String s) {
		if (idParentsMap.get(s).equals(s))
			return s;
		String parent = findSet(idParentsMap.get(s));
		idParentsMap.put(s, parent);
		return parent;
	}

	public static void union(String a, String b) {
		String aRoot = findSet(a);
		String bRoot = findSet(b);
		if (aRoot.equals(bRoot))
			return;
		idParentsMap.put(bRoot, aRoot);
		countMap.put(aRoot, countMap.get(bRoot) + countMap.get(aRoot));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			idParentsMap = new HashMap<>();
			countMap = new HashMap<>();
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String id1 = st.nextToken();
				String id2 = st.nextToken();

				if (!idParentsMap.containsKey(id1)) {
					idParentsMap.put(id1, id1);
					countMap.put(id1, 1);
				}
				if (!idParentsMap.containsKey(id2)) {
					idParentsMap.put(id2, id2);
					countMap.put(id2, 1);
				}

				union(id1, id2);
				sb.append(countMap.get(findSet(id1))).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}
