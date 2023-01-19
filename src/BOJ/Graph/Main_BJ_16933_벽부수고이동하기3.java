package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16933_벽부수고이동하기3 {

    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static int N, M, K;
    public static int[][] map, breakCounts;

    public static class MoveInfo {
        int r;
        int c;
        int cost;
        int breakCount;
        boolean isAfternoon;

        public MoveInfo(int r, int c, int cost, int breakCount, boolean isAfternoon) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.breakCount = breakCount;
            this.isAfternoon = isAfternoon;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        breakCounts = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                breakCounts[i][j] = Integer.MAX_VALUE;
            }
        }
        bw.write(Integer.toString(move()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        Queue<MoveInfo> queue = new LinkedList<>();
        queue.offer(new MoveInfo(0, 0, 1, 0, true));
        breakCounts[0][0] = 0;
        while (!queue.isEmpty()) {
            MoveInfo moveInfo = queue.poll();
            int r = moveInfo.r;
            int c = moveInfo.c;
            int cost = moveInfo.cost;
            int breakCount = moveInfo.breakCount;
            boolean isAfternoon = moveInfo.isAfternoon;
            if (r == N - 1 && c == M - 1) {
                return cost;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];
                if (!isIn(nr, nc) || breakCounts[nr][nc] <= breakCount) {
                    continue;
                }
                if (map[nr][nc] == WALL) {
                    if (breakCount >= K) {
                        continue;
                    }
                    if (isAfternoon) {
                        queue.offer(new MoveInfo(nr, nc, cost + 1, breakCount + 1, false));
                        breakCounts[nr][nc] = breakCount + 1;
                    } else {
                        queue.offer(new MoveInfo(r, c, cost + 1, breakCount, true));
                    }
                } else {
                    queue.offer(new MoveInfo(nr, nc, cost + 1, breakCount, !isAfternoon));
                    breakCounts[nr][nc] = breakCount;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
