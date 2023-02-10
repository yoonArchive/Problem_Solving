package BOJ.DP;

import java.io.*;

public class Main_BJ_13301_타일장식물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long[] rounds = new long[N];
        rounds[0] = 4;
        if (N > 1) {
            rounds[1] = 6;
            for (int i = 2; i < N; i++) {
                rounds[i] = rounds[i - 2] + rounds[i - 1];
            }
        }
        bw.write(Long.toString(rounds[N - 1]));
        br.close();
        bw.flush();
        bw.close();
    }
}
