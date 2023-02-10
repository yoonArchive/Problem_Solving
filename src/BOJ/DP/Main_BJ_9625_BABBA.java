package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_9625_BABBA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        int[] counts = new int[2];
        counts[0] = 0;
        counts[1] = 1;
        for (int i = 1; i < K; i++) {
            int tmp = counts[0];
            counts[0] = counts[1];
            counts[1] += tmp;
        }
        sb.append(counts[0]).append(" ").append(counts[1]);
        System.out.println(sb);
        br.close();
    }
}
