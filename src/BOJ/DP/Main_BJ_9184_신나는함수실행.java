package BOJ.DP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_9184_신나는함수실행 { // 다시풀기
	static final int SIZE = 21; // a,b,c 중 하나라도 0 이하이면 1을 리턴, 20 이상인 경우 w(20,20,20)을 리턴하므로 1~20의 경우만 배열에 저장한다.
	static int[][][] dp = new int[SIZE][SIZE][SIZE]; 
	
	public static int w(int a, int b, int c) {
		
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;

		if (a > 20 || b > 20 || c > 20) {
			return w(20, 20, 20);
		}
		
		if (dp[a][b][c] != 0) { // 이미 저장되어 있으면 그 값 반환
			return dp[a][b][c];
		}

		if (a < b && b < c) {
			dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
			return dp[a][b][c];
		}

		dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
		return dp[a][b][c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == -1 && b == -1 && c == -1)
				break;

			sb.append("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c) + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
