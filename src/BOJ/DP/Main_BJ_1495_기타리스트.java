package BOJ.DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1495_기타리스트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] diffs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diffs[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] volumes = new boolean[N + 1][M + 1];
        volumes[0][S] = true;
        for (int song = 1; song < N + 1; song++) {
            for (int volume = 0; volume < M + 1; volume++) {
                int diff = diffs[song - 1];
                if (volumes[song - 1][volume]) {
                    if (volume + diff <= M) {
                        volumes[song][volume + diff] = true;
                    }
                    if (volume - diff >= 0) {
                        volumes[song][volume - diff] = true;
                    }
                }
            }
        }
        int result = -1;
        for (int i = M; i >= 0; i--) {
            if (volumes[N][i]) {
                result = i;
                break;
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }
}
