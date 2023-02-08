package BOJ.Shortest_Path;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_22255_호석사우루스 {

    public static int N, M;
    public static int[][] damages;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static class MoveInfo implements Comparable<MoveInfo> {
        int r;
        int c;
        int moveType;
        int damage;

        public MoveInfo(int r, int c, int moveType, int damage) {
            this.r = r;
            this.c = c;
            this.moveType = moveType;
            this.damage = damage;
        }

        public int compareTo(MoveInfo m) {
            return this.damage - m.damage;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        damages = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        int er = Integer.parseInt(st.nextToken()) - 1;
        int ec = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                damages[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bw.write(Integer.toString(move(sr, sc, er, ec)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(int sr, int sc, int er, int ec) {
        PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
        boolean[][][] isVisited = new boolean[N][M][3];
        pq.offer(new MoveInfo(sr, sc, 0, 0));
        while (!pq.isEmpty()) {
            MoveInfo moveInfo = pq.poll();
            int r = moveInfo.r;
            int c = moveInfo.c;
            int damage = moveInfo.damage;
            if (r == er && c == ec) {
                return damage;
            }
            int moveType = (moveInfo.moveType + 1) % 3;
            int p = moveType == 0 ? 1 : 2;
            for (int d = moveType == 2 ? 1 : 0; d < 4; d += p) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || isVisited[nr][nc][moveType] || damages[nr][nc] == -1) {
                    continue;
                }
                pq.offer(new MoveInfo(nr, nc, moveType, damage + damages[nr][nc]));
                isVisited[nr][nc][moveType] = true;
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
