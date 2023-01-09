package BOJ.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2141_우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] townInfo = new int[N][2];
        long half = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            townInfo[i][0] = Integer.parseInt(st.nextToken());
            townInfo[i][1] = Integer.parseInt(st.nextToken());
            half += townInfo[i][1];
        }
        Arrays.sort(townInfo, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        long currentTotal = 0;
        half = (half + 1) / 2;
        for (int i = 0; i < N; i++) {
            currentTotal += townInfo[i][1];
            if (currentTotal >= half) {
                bw.write(Integer.toString(townInfo[i][0]));
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
