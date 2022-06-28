package BOJ.MST.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_16398_행성연결 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int cost[][] = new int[N + 1][N + 1];
        int minEdge[] = new int[N + 1];
        boolean isVisited[] = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            minEdge[i] = Integer.MAX_VALUE;
        }

        long result = 0;
        minEdge[1] = 0;
        for (int i = 1; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            int minVertex = 0;
            for (int j = 1; j <= N; j++) {
                if (!isVisited[j] && min > minEdge[j]) {
                    min = minEdge[j];
                    minVertex = j;
                }
            }
            isVisited[minVertex] = true;
            result += min;
            for (int j = 1; j <= N; j++) {
                if (!isVisited[j] && cost[minVertex][j] != 0 && minEdge[j] > cost[minVertex][j]) {
                    minEdge[j] = cost[minVertex][j];
                }
            }
        }
        bw.write(Long.toString(result));
        br.close();
        bw.flush();
        bw.close();

    }

}
