package BOJ.MST.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1833_고속철도설계하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int cost[][] = new int[N][N];
        int minEdge[] = new int[N];
        boolean isVisited[] = new boolean[N];
        int installedCost = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int nowCost = Integer.parseInt(st.nextToken());
                cost[i][j] = nowCost;
                if (nowCost < 0) {
                    isVisited[i] = true;
                    isVisited[j] = true;
                    installedCost += nowCost;
                }
            }
            minEdge[i] = Integer.MAX_VALUE;
        }
        installedCost = (installedCost / 2) * (-1);

        int result = 0;
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                tmp = i;
                break;
            }
        }
        int count = 0;
        minEdge[tmp] = 0;
        for (int c = tmp; c < N; c++) {
            int min = Integer.MAX_VALUE;
            int minVertex = 0;
            for (int i = 0; i < N; i++) {
                if (!isVisited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                    count++;
                    System.out.println(c + " " + (i + 1));
                }
            }
            result += min;
            isVisited[minVertex] = true;
            for (int i = 0; i < N; i++) {
                if (!isVisited[i] && cost[minVertex][i] != 0 && minEdge[i] > cost[minVertex][i]) {
                    minEdge[i] = cost[minVertex][i];
                }
            }
        }
        System.out.println(result + " " + count);
    }

}
