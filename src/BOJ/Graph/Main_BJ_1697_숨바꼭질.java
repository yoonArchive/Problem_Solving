package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_1697_숨바꼭질 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if (n < k) {
			int map[] = new int[k + 2];
			Queue<Integer> q = new LinkedList<>();
			q.offer(n);

			while (true) {
				int num = q.poll();
				int pre_val = map[num];
				int now_val = pre_val + 1;
				if (num - 1 >= 0 && map[num - 1] == 0) {
					map[num - 1] = now_val;
					q.offer(num - 1);
				}
				if (num + 1 < k + 2) {
					if (map[num + 1] == 0) {
						map[num + 1] = now_val;
						q.offer(num + 1);
					}
				}
				if (num * 2 < k + 2) {
					if (map[num * 2] == 0) {
						map[num * 2] = now_val;
						q.offer(num * 2);
					}
				}
				if (num + 1 == k) {
					break;
				} else if (num * 2 == k) {
					break;
				}
			}
			bw.write(Integer.toString(map[k]));
		} else if (n > k) {
			bw.write(Integer.toString(n - k));
		} else {
			bw.write("0");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}
