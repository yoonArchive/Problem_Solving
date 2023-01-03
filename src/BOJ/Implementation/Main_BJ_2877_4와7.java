package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2877_4와7 {

    public static int K;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        sb = new StringBuilder('4');
        while (true) {
            insertNumber(K);
            K = (K - 1) / 2;
            if (K == 0) {
                break;
            }
        }
        System.out.println(sb);
        br.close();
    }

    private static void insertNumber(int k) {
        sb.insert(0, k % 2 == 0 ? '7' : '4');
    }
}
