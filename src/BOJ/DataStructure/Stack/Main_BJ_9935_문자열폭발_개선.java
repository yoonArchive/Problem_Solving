package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발_개선 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력 문자열
		String input = br.readLine();
		int length = input.length();

		// 폭발 문자열
		String bomb = br.readLine();
		String reverse = new StringBuilder(bomb).reverse().toString();
		int bombLength = bomb.length();

		Stack<Character> popStack = new Stack<>();

		for (int i = 0; i < length; i++) // 문자열 앞에서부터 스택에 push
			popStack.push(input.charAt(i));

		StringBuilder baseSb = new StringBuilder(); // 폭발 문자열인지 검사하기 위한 StringBuilder

		while (!popStack.isEmpty()) {
			char c = popStack.pop(); // popStack에서 pop한 문자를
			baseSb.append(c); // baseSb에 쌓는다.
			int sbSize = baseSb.length();
			if (sbSize >= bombLength) { 
				if (baseSb.substring(sbSize - bombLength, sbSize).equals(reverse)) {// 폭발 문자열 발견
					baseSb.setLength(sbSize = sbSize - bombLength); // baseSb에서 폭발 문자열을 삭제
				}
			}
		}

		if (baseSb.length() == 0)
			sb.append("FRULA");
		else
			sb.append(baseSb.reverse());

		System.out.println(sb.toString());
		br.close();
	}
}
