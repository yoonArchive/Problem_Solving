package BOJ.Greedy;

import java.io.*;
import java.util.Arrays;

public class Main_BJ_1758_알바생강호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] tips = new int[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(tips);
        int subtractNumber = N - 1;
        int index = 0, current = 0;
        long tip = 0;
        while (index < N) {
            current = tips[index++] - subtractNumber;
            if (current > 0) {
                tip += current;
            }
            subtractNumber--;
        }
        bw.write(Long.toString(tip));
        br.close();
        bw.flush();
        bw.close();
    }
}
