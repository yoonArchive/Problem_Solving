package BOJ.DP;

import java.io.*;

public class Main_BJ_1309_동물원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] cage = new int[N + 1];
        cage[0] = 1;
        cage[1] = 3;
        for (int i = 2; i <= N; i++) {
            cage[i] = (cage[i - 2] + 2 * cage[i - 1]) % 9901;
        }
        bw.write(Integer.toString(cage[N]));
        br.close();
        bw.flush();
        bw.close();
    }
}
