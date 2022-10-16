package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17086_아기상어2 {

    public static final int EXIST = 1;
    public static final int NOT_EXIST = 0;

    public static int N, M;
    public static int[][] map;
    public static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static Queue<Node> queue;

    public static class Node {
        int r;
        int c;
        int distance;

        public Node(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        queue = new LinkedList<>();
        int current = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                current = Integer.parseInt(st.nextToken());
                map[i][j] = current;
                if (current == EXIST) {
                    queue.offer(new Node(i, j, 0));
                }
            }
        }
        getSafeDistance();
        int maxSafeDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxSafeDistance = Math.max(maxSafeDistance, map[i][j]);
            }
        }
        bw.write(Integer.toString(maxSafeDistance));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void getSafeDistance() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            int distance = node.distance + 1;
            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc)) {
                    continue;
                }
                if (map[nr][nc] == NOT_EXIST || map[nr][nc] > distance) {
                    map[nr][nc] = distance;
                    queue.offer(new Node(nr, nc, distance));
                }
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
