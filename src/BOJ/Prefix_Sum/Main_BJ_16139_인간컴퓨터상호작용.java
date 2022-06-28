package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16139_인간컴퓨터상호작용 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String S = br.readLine();
		int length = S.length();
		int cnt[][] = new int[length]['z' - 'a' + 1];
		cnt[0][S.charAt(0) - 'a']++;
		char cur;
		for (int i = 1; i < length; i++) {
			for (int j = 0, colLen = cnt[i].length; j < colLen; j++)
				cnt[i][j] = cnt[i - 1][j];
			cur = S.charAt(i);
			cnt[i][cur - 'a']++;
		}

		int q = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int c = 0, l = 0, r = 0;
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			c = st.nextToken().charAt(0) - 'a';
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			if (l == 0)
				sb.append(cnt[r][c]).append("\n");
			else
				sb.append(cnt[r][c] - cnt[l - 1][c]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
