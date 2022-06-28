package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14888_연산자끼워넣기 {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int numbers[], operatorCnt[]; // +, -, x, ÷ 순

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operatorCnt = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++)
			operatorCnt[i] = Integer.parseInt(st.nextToken());

		// 식 만들기
		makeExpression(0, numbers[0]);
		sb.append(max).append("\n").append(min);
		System.out.println(sb.toString());
		br.close();
	}

	private static void makeExpression(int count, int result) {
		if (count == N - 1) {
			if (result >= max)
				max = result;
			if (result <= min)
				min = result;
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (operatorCnt[i] > 0) {
				operatorCnt[i] -= 1;
				makeExpression(count + 1, calculate(result, numbers[count + 1], i));
				operatorCnt[i] += 1;
			}
		}
	}

	private static int calculate(int operand1, int operand2, int operator) {
		int result = 0;
		switch (operator) {
		case 0:
			result = operand1 + operand2;
			break;
		case 1:
			result = operand1 - operand2;
			break;
		case 2:
			result = operand1 * operand2;
			break;
		case 3:
			result = operand1 / operand2;
			break;
		}
		return result;
	}

}
