package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_14442_벽부수고이동하기2 {

    public static final int WALL = 1;

    public static int N, M, K;
    public static int[][] map;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class MoveInfo {
        int r;
        int c;
        int moveCount;
        int breakCount;

        public MoveInfo(int r, int c, int moveCount, int breakCount) {
            this.r = r;
            this.c = c;
            this.moveCount = moveCount;
            this.breakCount = breakCount;
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
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        bw.write(Integer.toString(move()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        boolean[][][] isVisited = new boolean[N][M][K + 1];
        Queue<MoveInfo> queue = new LinkedList<>();
        queue.offer(new MoveInfo(0, 0, 1, 0));
        isVisited[0][0][0] = true;
        while (!queue.isEmpty()) {
            MoveInfo moveInfo = queue.poll();
            int r = moveInfo.r;
            int c = moveInfo.c;
            int moveCount = moveInfo.moveCount;
            int breakCount = moveInfo.breakCount;
            if (r == N - 1 && c == M - 1) {
                return moveCount;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc)) {
                    continue;
                }
                int nextBreakCount = map[nr][nc] == WALL ? breakCount + 1 : breakCount;
                if (nextBreakCount > K || isVisited[nr][nc][nextBreakCount]) {
                    continue;
                } else {
                    queue.offer(new MoveInfo(nr, nc, moveCount + 1, nextBreakCount));
                    isVisited[nr][nc][nextBreakCount] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
