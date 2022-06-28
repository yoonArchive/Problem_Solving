package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_6443_애너그램 {
	static final int SIZE = 'z' - 'a' + 1;
	static StringBuilder sb;
	static int charCnt[], length;
	static char[] anagram;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		charCnt = new int[SIZE];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			length = input.length();
			anagram = new char[length];
			for (int j = 0; j < length; j++)
				charCnt[input.charAt(j) - 'a']++;
			makeAnagram(0);
			Arrays.fill(charCnt, 0);
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void makeAnagram(int count) {
		if (count == length) {
			for (int i = 0; i < count; i++)
				sb.append(anagram[i]);
			sb.append("\n");
			return;
		}
		for (int i = 0; i < SIZE; i++) {
			if (charCnt[i] == 0)
				continue;
			charCnt[i]--;
			anagram[count] = ((char) (i + 'a'));
			makeAnagram(count + 1);
			charCnt[i]++;
		}
	}

}
