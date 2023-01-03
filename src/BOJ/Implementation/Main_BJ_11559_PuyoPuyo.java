package BOJ.Implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_11559_PuyoPuyo {

    public static final int ROW = 12;
    public static final int COLUMN = 6;
    public static final int MINIMUM_SAME_COLOR = 4;
    public static final char EMPTY = '.';

    public static char[][] field;
    public static int chain;
    public static boolean isExplode;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        field = new char[ROW][COLUMN];
        for (int i = 0; i < ROW; i++) {
            field[i] = br.readLine().toCharArray();
        }
        while (true) {
            findExplode();
            if (!isExplode) {
                break;
            }
            chain++;
            moveDown();
            isExplode = false;
        }
        bw.write(Integer.toString(chain));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void findExplode() {
        boolean[][] isVisited = new boolean[ROW][COLUMN];
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COLUMN; c++) {
                if (!isVisited[r][c] && field[r][c] != EMPTY) {
                    findSameColor(r, c, isVisited);
                }
            }
        }
    }

    private static void findSameColor(int r, int c, boolean[][] isVisited) {
        char color = field[r][c];
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> explodeQueue = new LinkedList<>();
        queue.offer(new Node(r, c));
        explodeQueue.offer(new Node(r, c));
        isVisited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            r = node.r;
            c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || field[nr][nc] != color) {
                    continue;
                }
                queue.offer(new Node(nr, nc));
                explodeQueue.offer(new Node(nr, nc));
                isVisited[nr][nc] = true;
            }
        }
        if (explodeQueue.size() >= MINIMUM_SAME_COLOR) {
            isExplode = true;
            explode(explodeQueue);
        }
    }

    private static void explode(Queue<Node> explodeQueue) {
        while (!explodeQueue.isEmpty()) {
            Node node = explodeQueue.poll();
            int r = node.r;
            int c = node.c;
            field[r][c] = EMPTY;
        }
    }

    private static void moveDown() {
        for (int i = ROW - 2; i >= 0; i--) {
            for (int j = 0; j < COLUMN; j++) {
                if (field[i][j] == EMPTY) {
                    continue;
                }
                if (i + 1 < ROW && field[i + 1][j] == EMPTY) {
                    int downSize = getDownSize(i, j);
                    field[i + downSize][j] = field[i][j];
                    field[i][j] = EMPTY;
                }
            }
        }
    }

    private static int getDownSize(int r, int c) {
        int size = 1;
        for (int i = r + 2; i < ROW; i++) {
            if (field[i][c] == EMPTY) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < ROW && c >= 0 && c < COLUMN;
    }
}
