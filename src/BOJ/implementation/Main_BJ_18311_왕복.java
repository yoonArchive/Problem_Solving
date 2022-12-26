package BOJ.implementation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_18311_왕복 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        int[] length = new int[N];
        long[] cumulatives = new long[2 * N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            length[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1, size = 2 * N; i <= size; i++) {
            cumulatives[i] = cumulatives[i - 1] + (i <= N ? length[i - 1] : length[i - (2 * (i - N))]);
            if (K < cumulatives[i]) {
                bw.write(Integer.toString(i <= N ? i : i - (2 * (i - N)) + 1));
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
