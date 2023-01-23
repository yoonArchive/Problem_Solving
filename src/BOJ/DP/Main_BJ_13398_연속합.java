package BOJ.DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_13398_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[][] sum = new int[n][2];
        int max = sum[0][0] = sum[0][1] = numbers[0];
        for (int i = 1; i < n; i++) {
            sum[i][0] = Math.max(sum[i - 1][0], 0) + numbers[i];
            sum[i][1] = Math.max(sum[i - 1][0], sum[i - 1][1] + numbers[i]);
            max = Math.max(Math.max(max, sum[i][0]), sum[i][1]);
        }
        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
    }
}
