package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1003_피보나치함수 {

    public static int[][] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        counts = new int[41][2];
        counts[0][0] = 1;
        counts[1][1] = 1;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            fibonacci(N);
            sb.append(counts[N][0] + " " + counts[N][1]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int[] fibonacci(int n) {
        if (counts[n][0] != 0) {
            return counts[n];
        }
        if (n == 0 || n == 1) {
            return counts[n];
        } else {
            int[] result1 = fibonacci(n - 1);
            int[] result2 = fibonacci(n - 2);
            counts[n][0] = result1[0] + result2[0];
            counts[n][1] = result1[1] + result2[1];
        }
        return counts[n];
    }
}
