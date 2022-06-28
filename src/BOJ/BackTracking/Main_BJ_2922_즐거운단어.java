package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_2922_즐거운단어 {
	static final char REPRESENTATIVE_CONSONANT = 'C';
	static final char REPRESENTATIVE_VOWEL = 'A';
	static long ans;
	static int len;
	static char[] unhappyWord;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		len = input.length();
		unhappyWord = new char[len];
		boolean hasL = false;
		for (int i = 0; i < len; i++) {
			unhappyWord[i] = input.charAt(i);
			if (unhappyWord[i] == 'L')
				hasL = true;
		}
		makeHappyWord(0, 0, 0, hasL, 1);
		bw.write(Long.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void makeHappyWord(int idx, int cCnt, int vCnt, boolean hasL, long cnt) {
		// Purning
		if (cCnt == 3 || vCnt == 3)
			return;

		if (idx == len) {
			if (hasL)
				ans += cnt;
			return;
		}

		// case 1. 언더바인 경우
		if (unhappyWord[idx] == '_') {
			unhappyWord[idx] = REPRESENTATIVE_CONSONANT;
			makeHappyWord(idx + 1, cCnt + 1, 0, hasL, cnt * 20);
			unhappyWord[idx] = REPRESENTATIVE_VOWEL;
			makeHappyWord(idx + 1, 0, vCnt + 1, hasL, cnt * 5);
			unhappyWord[idx] = 'L';
			makeHappyWord(idx + 1, cCnt + 1, 0, true, cnt);
			unhappyWord[idx] = '_';
		}
		// case 2. 모음인 경우
		else if (isVowel(unhappyWord[idx]))
			makeHappyWord(idx + 1, 0, vCnt + 1, hasL, cnt);
		// case 3. 자음인 경우
		else
			makeHappyWord(idx + 1, cCnt + 1, 0, hasL, cnt);
	}

	private static boolean isVowel(char c) {
		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
