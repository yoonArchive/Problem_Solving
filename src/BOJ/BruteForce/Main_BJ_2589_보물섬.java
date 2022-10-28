package BOJ.BruteForce;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2589_보물섬 {

    public static final char LAND = 'L';

    public static int R, C, maxDistance;
    public static char[][] map;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == LAND) {
                    findTreasure(i, j);
                }
            }
        }
        bw.write(Integer.toString(maxDistance));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void findTreasure(int sr, int sc) {
        Queue<Node> queue = new LinkedList<>();
        boolean isVisited[][] = new boolean[R][C];
        queue.offer(new Node(sr, sc, 0));
        isVisited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            int distance = node.distance;
            maxDistance = Math.max(maxDistance, distance);
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || map[nr][nc] != LAND || isVisited[nr][nc]) {
                    continue;
                }
                queue.offer(new Node(nr, nc, distance + 1));
                isVisited[nr][nc] = true;
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
