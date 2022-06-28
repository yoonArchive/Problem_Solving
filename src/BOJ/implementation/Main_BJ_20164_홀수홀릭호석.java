package BOJ.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_20164_홀수홀릭호석 {
	static int minCnt = Integer.MAX_VALUE, maxCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String N = br.readLine();
		getNextNumber(N, 0);
		sb.append(minCnt).append(" ").append(maxCnt);
		System.out.println(sb.toString());
		br.close();
	}

	private static void getNextNumber(String N, int count) {
		int length = N.length();
		count += addOddCnt(N, length);
		if (length == 1) {
			if (maxCnt < count)
				maxCnt = count;
			if (minCnt > count)
				minCnt = count;
			return;
		} else if (length == 2) {
			getNextNumber(Integer.toString((N.charAt(0) - '0') + (N.charAt(1) - '0')), count);
		} else {
			int i = 0;
			for (int j = 1; j < length; j++) {
				for (int k = j + 1; k < length; k++) {
					getNextNumber(Integer.toString(Integer.parseInt(N.substring(i, j))
							+ Integer.parseInt(N.substring(j, k)) + Integer.parseInt(N.substring(k, length))), count);
				}
			}
		}
	}

	private static int addOddCnt(String n, int length) {
		int count = 0;
		for (int i = 0; i < length; i++) {
			if ((n.charAt(i) - '0') % 2 == 1)
				count++;
		}
		return count;
	}

}
