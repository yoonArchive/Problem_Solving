package Programmers;

public class Programmers_summer_winter_internship_배달 {
    static final int INF = 500001;

    public static void main(String[] args) {
        int N = 6;
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int K = 4;
        System.out.println(solution(N, road, K));
    }

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] deliveryTime = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    deliveryTime[i][j] = 0;
                    continue;
                }
                deliveryTime[i][j] = INF;
            }
        }

        for (int i = 0, len = road.length; i < len; i++) {
            int v1 = road[i][0];
            int v2 = road[i][1];
            int time = road[i][2];
            if (time < deliveryTime[v1][v2])
                deliveryTime[v1][v2] = deliveryTime[v2][v1] = time;
        }

        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                if (mid == start || deliveryTime[start][mid] == INF)
                    continue;
                for (int end = 1; end <= N; end++) {
                    if (end == mid || end == start || deliveryTime[mid][end] == INF)
                        continue;
                    deliveryTime[start][end] = Math.min(deliveryTime[start][end],
                            deliveryTime[start][mid] + deliveryTime[mid][end]);
                }
            }
        }

        for (int to = 1; to <= N; to++) {
            if (deliveryTime[1][to] <= K)
                answer++;
        }

        return answer;
    }

}
