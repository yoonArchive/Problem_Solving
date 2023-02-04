package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1039_교환 {

    public static int N, K, nLength;

    public static class ExchangeInfo {
        int count;
        int number;

        public ExchangeInfo(int count, int number) {
            this.count = count;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nLength = Integer.toString(N).length();
        bw.write(Integer.toString(exchange()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int exchange() {
        Queue<ExchangeInfo> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[1000001][K + 1];
        queue.offer(new ExchangeInfo(0, N));
        isVisited[N][0] = true;
        int max = -1;
        while (!queue.isEmpty()) {
            ExchangeInfo exchangeInfo = queue.poll();
            int count = exchangeInfo.count;
            int number = exchangeInfo.number;
            if (count == K) {
                max = Math.max(max, number);
                continue;
            }
            StringBuilder numberSb = new StringBuilder(Integer.toString(number));
            for (int i = 0; i < nLength - 1; i++) {
                char a = numberSb.charAt(i);
                for (int j = i + 1; j < nLength; j++) {
                    StringBuilder nNumberSb = new StringBuilder(numberSb);
                    char b = numberSb.charAt(j);
                    nNumberSb.setCharAt(i, b);
                    nNumberSb.setCharAt(j, a);
                    int nNumber = Integer.parseInt(nNumberSb.toString());
                    if (nNumberSb.charAt(0) != '0' && !isVisited[nNumber][count + 1]) {
                        queue.offer(new ExchangeInfo(count + 1, nNumber));
                        isVisited[nNumber][count + 1] = true;
                    }
                }
            }
        }
        return max;
    }
}
