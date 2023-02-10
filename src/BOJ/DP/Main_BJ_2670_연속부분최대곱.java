package BOJ.DP;

import java.io.*;

public class Main_BJ_2670_연속부분최대곱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        double[] maxResults = new double[N];
        maxResults[0] = Double.parseDouble(br.readLine());
        double max = maxResults[0];
        for (int i = 1; i < N; i++) {
            double current = Double.parseDouble(br.readLine());
            maxResults[i] = current * maxResults[i - 1] > current ? current * maxResults[i - 1] : current;
            max = Math.max(max, maxResults[i]);
        }
        bw.write(String.format("%.3f", max));
        br.close();
        bw.flush();
        bw.close();
    }
}
