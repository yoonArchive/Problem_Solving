package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16930_달리기 {

    public static final char EMPTY = '.';
    public static final char WALL = '#';

    public static int N, M, K, x1, x2, y1, y2;
    public static char[][] gym;
    public static int[][] visit;
    public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static class MoveInfo {
        int r;
        int c;
        int cost;

        public MoveInfo(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gym = new char[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            gym[i] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        bw.write(Integer.toString(run()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int run() {
        Queue<MoveInfo> queue = new LinkedList<>();
        queue.offer(new MoveInfo(x1, y1, 0));
        while (!queue.isEmpty()) {
            MoveInfo moveInfo = queue.poll();
            int r = moveInfo.r;
            int c = moveInfo.c;
            int cost = moveInfo.cost;
            if (r == x2 && c == y2) {
                return cost;
            }
            for (int d = 0; d < 4; d++) {
                for (int l = 1; l <= K; l++) {
                    int nr = r + (l * deltas[d][0]);
                    int nc = c + (l * deltas[d][1]);
                    if (!isIn(nr, nc) || gym[nr][nc] == WALL) {
                        break;
                    }
                    if (visit[nr][nc] == 0) {
                        visit[nr][nc] = cost + 1;
                        queue.offer(new MoveInfo(nr, nc, cost + 1));
                    } else if (visit[nr][nc] <= visit[r][c]) {
                        break;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
