package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14940_쉬운최단거리 {

    public static final int GOAL = 2;
    public static final int LAND = 1;
    public static final int EMPTY = 0;

    public static int N, M;
    public static int[][] map, dist;
    public static boolean[][] isVisited;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static class Node implements Comparable<Node> {
        int r;
        int c;
        int distance;

        public Node(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        isVisited = new boolean[N][M];
        int sr = 0, sc = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == GOAL) {
                    sr = i;
                    sc = j;
                }
            }
        }
        move(sr, sc);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j] && map[i][j] == LAND) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void move(int sr, int sc) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(sr, sc, 0));
        isVisited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            int distance = node.distance;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isIn(nr, nc) && map[nr][nc] != EMPTY && !isVisited[nr][nc]) {
                    queue.offer(new Node(nr, nc, distance + 1));
                    dist[nr][nc] = distance + 1;
                    isVisited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
