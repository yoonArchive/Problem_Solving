package BOJ.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_20300_서강근육맨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long[] losses = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            losses[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(losses);
        long loss = losses[N - 1];
        int subtract = N % 2 == 0 ? 1 : 2;
        for (int i = 0; i < N / 2; i++) {
            loss = Math.max(loss, losses[i] + losses[N - i - subtract]);
        }
        bw.write(Long.toString(loss));
        br.close();
        bw.flush();
        bw.close();
    }
}
