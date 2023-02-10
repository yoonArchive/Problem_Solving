package BOJ.DP;

import java.io.*;

public class Main_BJ_9655_돌게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 == 0) {
            bw.write("CY");
        } else {
            bw.write("SK");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
