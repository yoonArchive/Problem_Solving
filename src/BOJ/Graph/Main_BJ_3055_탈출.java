package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3055_탈출 {

    public static final char EMPTY = '.';
    public static final char WATER = '*';
    public static final char STONE = 'X';
    public static final char BEAVER = 'D';
    public static final char HEDGEHOG = 'S';
    public static final String IMPOSSIBLE = "KAKTUS";
    public static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int R, C;
    public static char[][] map;
    public static boolean[][] isVisited;
    public static Queue<Node> waterQueue;
    public static PriorityQueue<Node> moveQueue;

    public static class Node implements Comparable<Node> {
        int r;
        int c;
        int time;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        moveQueue = new PriorityQueue<>();
        waterQueue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == HEDGEHOG) {
                    moveQueue.offer(new Node(i, j, 0));
                } else if (c == WATER) {
                    waterQueue.offer(new Node(i, j));
                }
            }
        }
        int answer = move();
        if (answer == -1) {
            bw.write(IMPOSSIBLE);
        } else {
            bw.write(Integer.toString(answer));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        while (!moveQueue.isEmpty()) {
            sink();
            int moveQueueSize = moveQueue.size();
            while (moveQueueSize-- > 0) {
                Node node = moveQueue.poll();
                int r = node.r;
                int c = node.c;
                int time = node.time;
                for (int d = 0; d < 4; d++) {
                    int nr = r + DELTAS[d][0];
                    int nc = c + DELTAS[d][1];
                    if (!isIn(nr, nc)) {
                        continue;
                    }
                    if (map[nr][nc] == BEAVER) {
                        return time + 1;
                    } else if (map[nr][nc] == EMPTY) {
                        map[nr][nc] = HEDGEHOG;
                        moveQueue.add(new Node(nr, nc, time + 1));
                    }
                }
            }
        }
        return -1;
    }

    private static void sink() {
        int waterQueueSize = waterQueue.size();
        while (waterQueueSize-- > 0) {
            Node node = waterQueue.poll();
            int r = node.r;
            int c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];
                if (!isIn(nr, nc) || map[nr][nc] != EMPTY) {
                    continue;
                }
                map[nr][nc] = WATER;
                waterQueue.offer(new Node(nr, nc));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
