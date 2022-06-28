package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1439_뒤집기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char S[] = br.readLine().toCharArray();
		int zeroUnit = 0, oneUnit = 0, idx = 0;
		int size = S.length;

		while (idx < size) {
			if (S[idx] == '0') {
				zeroUnit++;
				while (true) {
					if (idx >= size || S[idx] == '1')
						break;
					idx++;
				}
			} else {
				oneUnit++;
				while (true) {
					if (idx >= size || S[idx] == '0')
						break;
					idx++;
				}
			}
		}
		int ans = zeroUnit <= oneUnit ? zeroUnit : oneUnit;
		sb.append(ans);
		System.out.println(sb.toString());
	}
}
