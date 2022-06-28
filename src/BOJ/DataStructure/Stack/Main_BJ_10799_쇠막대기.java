package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_BJ_10799_쇠막대기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		int ans = 0;
		int idx = 0;
		while (idx != str.length()) {
			char now = str.charAt(idx);
			if (now == '(') {
				if (str.charAt(idx + 1) == ')') { // 레이저 발사
					ans += stack.size();
					idx++;
				} else { // 쇠막대기 왼쪽
					ans++;
					stack.push(now);
				}
			} else { // 쇠막대기 오른쪽
				stack.pop();
			}
			idx++;
		}
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		br.close();
	}

}
