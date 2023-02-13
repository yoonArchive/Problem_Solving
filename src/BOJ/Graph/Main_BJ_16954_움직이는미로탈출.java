package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_16954_움직이는미로탈출 {

    public static final int SIZE = 8;
    public static final char EMPTY = '.';
    public static final char WALL = '#';

    public static char[][] board;
    public static int r, c;
    public static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static class Object {
        int r;
        int c;
        int time;

        public Object(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Object(int r, int c, int time) {
            this(r, c);
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        board = new char[SIZE][SIZE];
        Queue<Object> characterInfo = new LinkedList<>();
        Queue<Object> walls = new LinkedList<>();
        characterInfo.offer(new Object(SIZE - 1, 0, 0));
        for (int i = 0; i < SIZE; i++) {
            board[i] = br.readLine().toCharArray();
        }
        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == WALL) {
                    walls.offer(new Object(i, j));
                }
            }
        }
        int canEscape = 0;
        boolean[][][] isVisited = new boolean[SIZE][SIZE][8];
        while (true) {
            int queueSize = characterInfo.size();
            if (queueSize == 0) {
                break;
            }
            if (characterMove(characterInfo, isVisited, queueSize)) {
                canEscape = 1;
                break;
            }
            wallMove(walls);
        }
        bw.write(Integer.toString(canEscape));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean characterMove(Queue<Object> characterInfo, boolean[][][] isVisited, int queueSize) {
        while (queueSize-- > 0) {
            Object character = characterInfo.poll();
            int r = character.r;
            int c = character.c;
            int time = character.time;
            if (r == 0 && c == SIZE - 1) {
                return true;
            } else if (board[r][c] == WALL) {
                continue;
            }
            for (int d = 0; d < 8; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || board[nr][nc] != EMPTY || isVisited[nr][nc][d]) {
                    continue;
                }
                characterInfo.offer(new Object(nr, nc, time + 1));
                isVisited[nr][nc][d] = true;
            }
            characterInfo.offer(new Object(r, c, time + 1));
        }
        return false;
    }

    private static void wallMove(Queue<Object> walls) {
        int queueSize = walls.size();
        while (queueSize-- > 0) {
            Object wall = walls.poll();
            int r = wall.r;
            int c = wall.c;
            int nr = r + deltas[4][0];
            if (!isIn(nr, c)) {
                continue;
            }
            walls.offer(new Object(nr, c));
            board[r][c] = EMPTY;
            board[nr][c] = WALL;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < SIZE && c < SIZE;
    }
}
