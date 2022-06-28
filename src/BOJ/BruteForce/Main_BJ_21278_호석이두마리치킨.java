package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_21278_호석이두마리치킨 {
    static final int INF = 9999;
    static int N, M, minTime = Integer.MAX_VALUE;
    static int cost[][], selectedBuilding[], time[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int building1 = Integer.parseInt(st.nextToken());
            int building2 = Integer.parseInt(st.nextToken());
            cost[building1][building2] = 1;
            cost[building2][building1] = 1;
        }
        // 플로이드 워셜
        for (int mid = 1; mid <= N; mid++) {
            for (int from = 1; from <= N; from++) {
                if (mid == from)
                    continue;
                for (int to = 1; to <= N; to++) {
                    if (mid == to || from == to)
                        continue;
                    cost[from][to] = Math.min(cost[from][to], cost[from][mid] + cost[mid][to]);
                }
            }
        }
        // 건물 2개 선택하기
        selectedBuilding = new int[2];
        time = new int[N + 1];
        selectBuilding(1, 0);
        System.out.println(sb.toString());
        br.close();
    }

    private static void selectBuilding(int start, int count) {
        if (count == 2) {
            // 건물 2개 선택 완료했다면 왕복시간을 구한다.
            int totalTime = 0;
            for (int i = 1; i <= N; i++)
                totalTime += Math.min(cost[selectedBuilding[0]][i], cost[selectedBuilding[1]][i]) * 2;
            if (minTime > totalTime) {
                sb.setLength(0);
                sb.append(selectedBuilding[0]).append(" ").append(selectedBuilding[1]).append(" ").append(totalTime);
                minTime = totalTime;
            }
            return;
        }
        for (int i = start; i <= N; i++) {
            selectedBuilding[count] = i;
            selectBuilding(i + 1, count + 1);
        }
    }
}
