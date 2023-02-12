package BOJ.DP;

import java.io.*;

public class Main_BJ_2302_극장좌석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] isFixedSeat = new boolean[N + 2];
        int[] counts = new int[N + 1];
        for (int i = 0; i < M; i++) {
            isFixedSeat[Integer.parseInt(br.readLine())] = true;
        }
        isFixedSeat[N + 1] = true;
        getCounts(counts, N);
        int start = 1, count = 1;
        for (int i = 1; i <= N + 1; i++) {
            if (isFixedSeat[i]) {
                count *= counts[i - start];
                start = i + 1;
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
    }

    private static void getCounts(int[] counts, int N) {
        counts[0] = 1;
        counts[1] = 1;
        if (N > 1) {
            for (int i = 2; i <= N; i++) {
                counts[i] = counts[i - 2] + counts[i - 1];
            }
        }
    }
}
