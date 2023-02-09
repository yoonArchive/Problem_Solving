package BOJ.Shortest_Path;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_20926_얼음미로 {

    public static final char TERA = 'T';
    public static final char ROCK = 'R';
    public static final char HOLE = 'H';
    public static final char EXIT = 'E';

    public static int W, H;
    public static char[][] maze;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static class MoveInfo implements Comparable<MoveInfo> {
        int r;
        int c;
        int cost;
        int direction;

        public MoveInfo(int r, int c, int cost, int direction) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.direction = direction;
        }

        public int compareTo(MoveInfo m) {
            return this.cost - m.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        maze = new char[H][W];
        PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
        boolean[][][] isVisited = new boolean[H][W][4];
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                char c = line.charAt(j);
                maze[i][j] = c;
                if (c == TERA) {
                    for (int d = 0; d < 4; d++) {
                        pq.offer(new MoveInfo(i, j, 0, d));
                        isVisited[i][j][d] = true;
                    }
                }
            }
        }
        bw.write(Integer.toString(move(pq, isVisited)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(PriorityQueue<MoveInfo> pq, boolean[][][] isVisited) {
        while (!pq.isEmpty()) {
            MoveInfo moveInfo = pq.poll();
            int r = moveInfo.r;
            int c = moveInfo.c;
            int cost = moveInfo.cost;
            int direction = moveInfo.direction;
            char current = maze[r][c];
            if (current == EXIT) {
                return cost;
            } else if (current == ROCK) {
                int prevR = r - deltas[direction][0];
                int prevC = c - deltas[direction][1];
                for (int d = 0; d < 4; d++) {
                    if (d == direction) {
                        continue;
                    }
                    moveNext(pq, isVisited, prevR, prevC, cost, d);
                }
            } else {
                moveNext(pq, isVisited, r, c, cost, direction);
            }
        }
        return -1;
    }

    private static void moveNext(PriorityQueue<MoveInfo> pq, boolean[][][] isVisited, int r, int c, int cost, int direction) {
        int nr = r + deltas[direction][0];
        int nc = c + deltas[direction][1];
        if (!check(isVisited, nr, nc, direction)) {
            char next = maze[nr][nc];
            pq.offer(new MoveInfo(nr, nc, next == ROCK || next == EXIT ? cost : cost + (maze[nr][nc] - '0'), direction));
            isVisited[nr][nc][direction] = true;
        }
    }

    private static boolean check(boolean[][][] isVisited, int r, int c, int direction) {
        return !isIn(r, c) || isVisited[r][c][direction] || maze[r][c] == HOLE;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
