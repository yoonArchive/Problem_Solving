package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_16920_확장게임 {

    public static final char EMPTY = '.';
    public static final char WALL = '#';

    public static int N, M, players;
    public static int[] S, castles;
    public static char[][] board;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class Space implements Comparable<Space> {

        int r;
        int c;
        int turn;
        int moveCount;
        char player;

        public Space(int r, int c, int turn, int moveCount, char player) {
            this.r = r;
            this.c = c;
            this.turn = turn;
            this.moveCount = moveCount;
            this.player = player;
        }

        public int compareTo(Space s) {
            if (this.turn == s.turn) {
                if (this.player == s.player) {
                    return this.moveCount - s.moveCount;
                }
                return this.player - s.player;
            }
            return this.turn - s.turn;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        players = Integer.parseInt(st.nextToken());
        S = new int[players + 1];
        castles = new int[players + 1];
        board = new char[N][M];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= players; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        PriorityQueue<Space> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char current = line.charAt(j);
                board[i][j] = current;
                if (current != EMPTY && current != WALL) {
                    queue.offer(new Space(i, j, 0, 0, current));
                    castles[current - '0']++;
                }
            }
        }
        play(queue);
        for (int player = 1; player <= players; player++) {
            sb.append(castles[player]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    private static void play(Queue<Space> queue) {
        while (!queue.isEmpty()) {
            Space space = queue.poll();
            int r = space.r;
            int c = space.c;
            int turn = space.turn;
            int moveCount = space.moveCount;
            char player = space.player;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || board[nr][nc] != EMPTY) {
                    continue;
                }
                board[nr][nc] = player;
                castles[player - '0']++;
                if (moveCount < S[player - '0'] - 1) {
                    queue.offer(new Space(nr, nc, turn, moveCount + 1, player));
                } else if (moveCount == S[player - '0'] - 1) {
                    queue.offer(new Space(nr, nc, turn + 1, 0, player));
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
