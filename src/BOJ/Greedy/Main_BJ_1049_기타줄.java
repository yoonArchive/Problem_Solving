package BOJ.Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1049_기타줄 {

    public static final int PACKAGE = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int setCost = 1001, singleCost = 1001;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            setCost = Math.min(setCost, Integer.parseInt(st.nextToken()));
            singleCost = Math.min(singleCost, Integer.parseInt(st.nextToken()));
        }
        boolean isSetCheaper = setCost / 6 < singleCost ? true : false;
        int cost = 0;
        if (!isSetCheaper) {
            cost = N * singleCost;
        } else {
            while (N > 0) {
                if (N / PACKAGE > 0) {
                    cost += setCost;
                    N -= PACKAGE;
                } else {
                    cost += Math.min(setCost, N * singleCost);
                    break;
                }
            }
        }
        bw.write(Integer.toString(cost));
        br.close();
        bw.flush();
        bw.close();
    }
}
