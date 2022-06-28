package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_2644_촌수계산 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n, m;
	static int p1, p2;
	static ArrayList<ArrayList<Integer>> list;
	static boolean visit[];
	static int ans;

	public static class Person {
		int p_num;
		int chon;

		public Person(int p_num, int chon) {
			this.p_num = p_num;
			this.chon = chon;
		}
	}

	public static int bfs(int start, int end) {
		Queue<Person> q = new LinkedList<>();
		q.offer(new Person(start, 0));
		visit[start] = true;
		while (!q.isEmpty()) {
			Person p = q.poll();
			if (p.p_num == end) {
				ans = p.chon;
				break;
			}
			for (int i = 0; i < list.get(p.p_num).size(); i++) {
				int temp = list.get(p.p_num).get(i);
				if (visit[temp] == false) {
					q.offer(new Person(temp, p.chon + 1));
					visit[temp] = true;
				}
			}
		}
		return ans;

	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < m; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		visit = new boolean[n + 1];
		bfs(p1, p2);
		if (ans == 0)
			bw.write("-1");
		else
			bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}
}
