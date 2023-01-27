package BOJ.DP;

import java.io.*;

public class Main_BJ_17626_FourSquares {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count[i] = count[i - 1] + 1;
            for (int j = 1; j * j <= i; j++) {
                count[i] = Math.min(count[i], count[i - j * j] + 1);
            }
        }
        bw.write(Integer.toString(count[n]));
        br.close();
        bw.flush();
        bw.close();
    }
}
