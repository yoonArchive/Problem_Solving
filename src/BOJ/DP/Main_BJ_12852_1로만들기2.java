package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_12852_1로만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            sb.append("0").append("\n").append("1");
        } else {
            int[] counts = new int[N + 1];
            int[] nexts = new int[N + 1];
            Arrays.fill(counts, N + 1);
            counts[N] = 0;
            for (int i = N; i >= 2; i--) {
                if (i % 3 == 0 && counts[i / 3] > counts[i] + 1) {
                    counts[i / 3] = counts[i] + 1;
                    nexts[i / 3] = i;
                }
                if (i % 2 == 0 && counts[i / 2] > counts[i] + 1) {
                    counts[i / 2] = counts[i] + 1;
                    nexts[i / 2] = i;
                }
                if (counts[i - 1] > counts[i] + 1) {
                    counts[i - 1] = counts[i] + 1;
                    nexts[i - 1] = i;
                }
            }
            sb.append(counts[1]).append("\n");
            StringBuilder order = new StringBuilder("1");
            int next = nexts[1];
            while (next != 0) {
                order.insert(0," ").insert(0, next);
                next = nexts[next];
            }
            sb.append(order);
        }
        System.out.println(sb);
        br.close();
    }
}
