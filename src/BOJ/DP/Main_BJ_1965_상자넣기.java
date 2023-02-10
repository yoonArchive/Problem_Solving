package BOJ.DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1965_상자넣기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] size = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }
        int[] counts = new int[n];
        counts[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (size[i] > size[j]) {
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }
            }
            if (counts[i] == 0) {
                counts[i] = 1;
            }
        }
        Arrays.sort(counts);
        bw.write(Integer.toString(counts[n - 1]));
        br.close();
        bw.flush();
        bw.close();
    }
}
