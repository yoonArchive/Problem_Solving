package BOJ.BruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_2003_수들의합2 {

    public static int N, M, count;
    public static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        getSum();
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void getSum() {
        int startIdx = 0, endIdx = 0;
        int sum = 0;
        while (startIdx < N) {
            if (sum > M || endIdx == N) {
                sum -= numbers[startIdx++];
            } else {
                sum += numbers[endIdx++];
            }
            if (sum == M) {
                count++;
            }
        }
    }
}
