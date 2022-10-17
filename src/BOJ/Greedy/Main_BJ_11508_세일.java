package BOJ.Greedy;

import java.io.*;
import java.util.Arrays;

public class Main_BJ_11508_세일 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] dairyProducts = new int[N];
        for (int i = 0; i < N; i++) {
            dairyProducts[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(dairyProducts);
        int cost = 0, count = 0;
        while (count < N) {
            count++;
            if (count % 3 != 0) {
                cost += dairyProducts[N - count];
            }
        }
        bw.write(Integer.toString(cost));
        br.close();
        bw.flush();
        bw.close();
    }
}
