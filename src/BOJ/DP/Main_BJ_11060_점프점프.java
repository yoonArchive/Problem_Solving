package BOJ.DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_11060_점프점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[] counts = new int[N];
        Arrays.fill(counts, Integer.MAX_VALUE);
        counts[0] = 0;
        for (int i = 0; i < N; i++) {
            if (counts[i] == Integer.MAX_VALUE) {
                continue;
            }
            int number = numbers[i];
            for (int j = 1; j <= number; j++) {
                if (i + j >= N) {
                    continue;
                }
                counts[i + j] = Math.min(counts[i + j], counts[i] + 1);
            }
        }
        if (counts[N - 1] == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(counts[N - 1]));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
