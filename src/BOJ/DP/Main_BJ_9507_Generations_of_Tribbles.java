package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_9507_Generations_of_Tribbles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] index = new int[t];
        int max = 3;
        for (int i = 0; i < t; i++) {
            index[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, index[i]);
        }
        long[] koongFibonacci = new long[max + 1];
        makeKoongFibonacci(koongFibonacci, max);
        for (int i = 0; i < t; i++) {
            sb.append(koongFibonacci[index[i]]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void makeKoongFibonacci(long[] koongFibonacci, int max) {
        koongFibonacci[0] = koongFibonacci[1] = 1;
        koongFibonacci[2] = 2;
        koongFibonacci[3] = 4;
        for (int i = 4; i <= max; i++) {
            koongFibonacci[i] = koongFibonacci[i - 1] + koongFibonacci[i - 2] + koongFibonacci[i - 3] + koongFibonacci[i - 4];
        }
    }
}
