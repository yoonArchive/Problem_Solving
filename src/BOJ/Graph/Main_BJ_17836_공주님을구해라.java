package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_17836_공주님을구해라 {

    public static final int WALL = 1;
    public static final int GRAM = 2;
    public static final String FAIL = "Fail";

    public static int N, M, T;
    public static int[][] castle;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static class Node implements Comparable<Node> {
        int r;
        int c;
        int time;
        int hasGram;

        public Node(int r, int c, int time, int hasGram) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.hasGram = hasGram;
        }

        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        castle = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = findPrincess();
        if (result == -1) {
            bw.write(FAIL);
        } else {
            bw.write(Integer.toString(result));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static int findPrincess() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][][] isVisited = new boolean[N][M][2];
        pq.offer(new Node(0, 0, 0, 0));
        isVisited[0][0][0] = true;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int r = node.r;
            int c = node.c;
            int time = node.time;
            int hasGram = node.hasGram;
            if (r == N - 1 && c == M - 1) {
                return time;
            }
            if (time > T) {
                return -1;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nHasGram = hasGram;
                if (!isIn(nr, nc) || isVisited[nr][nc][hasGram]) {
                    continue;
                }
                if (hasGram == 0 && castle[nr][nc] == WALL) {
                    continue;
                }
                if (castle[nr][nc] == GRAM) {
                    nHasGram = 1;
                }
                pq.offer(new Node(nr, nc, time + 1, nHasGram));
                isVisited[nr][nc][hasGram] = true;
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
