package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_17086_아기상어2 {

    public static final int EXIST = 1;
    public static final int NOT_EXIST = 0;

    public static int N, M;
    public static int[][] map;
    public static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};

    public static class Shark implements Comparable<Shark> {
        int r;
        int c;
        int distance;

        public Shark(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        public int compareTo(Shark o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxSafeDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == NOT_EXIST) {
                    maxSafeDistance = Math.max(maxSafeDistance, getSafeDistance(i, j));
                }
            }
        }
        bw.write(Integer.toString(maxSafeDistance));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int getSafeDistance(int sr, int sc) {
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[N][M];
        pq.offer(new Shark(sr, sc, 0));
        isVisited[sr][sc] = true;
        while (!pq.isEmpty()) {
            Shark shark = pq.poll();
            int r = shark.r;
            int c = shark.c;
            int distance = shark.distance;
            if (map[r][c] == EXIST) {
                return distance;
            }
            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc]) {
                    continue;
                }
                pq.offer(new Shark(nr, nc, distance + 1));
                isVisited[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
