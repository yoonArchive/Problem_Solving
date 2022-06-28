package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main_BJ_16922_로마숫자만들기 {
	static int NUMERALS[] = { 1, 5, 10, 50 };
	static HashSet<Integer> set;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		makeNumber(0, 0, 0);
		bw.write(Integer.toString(set.size()));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void makeNumber(int count, int start, int sum) {
		if (count == N) {
			set.add(sum);
			return;
		}
		for (int i = start; i < 4; i++) {
			makeNumber(count + 1, i, sum + NUMERALS[i]);
		}

	}

}
