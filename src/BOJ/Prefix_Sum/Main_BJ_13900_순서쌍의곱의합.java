package BOJ.Prefix_Sum;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_13900_순서쌍의곱의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long[] numbers = new long[N + 1];
        long[] sum = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i - 1] + numbers[i];
        }
        long result = 0;
        for (int i = 2; i <= N; i++) {
            result += numbers[i] * sum[i - 1];
        }
        bw.write(Long.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }
}
