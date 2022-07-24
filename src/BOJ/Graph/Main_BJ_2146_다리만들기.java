package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_2146_다리만들기 {
    private static final int SEA = 0;
    private static final int LAND = 1;
    static int N, min;
    static int[][] map;
    static boolean[][] isVisited;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static class Pair implements Comparable<Pair> {
        int r, c, count;

        public Pair(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        // 각 육지에 번호 매겨 분리
        int number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == LAND && !isVisited[i][j]) {
                    setNumberOfLand(number, i, j);
                    number++;
                }
            }
        }
        // 다리 놓기
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    initIsVisited();
                    setBridge(map[i][j], i, j);
                }
            }
        }
        bw.write(Integer.toString(min - 1));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void setNumberOfLand(int number, int sr, int sc) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(sr, sc, 0));
        isVisited[sr][sc] = true;
        map[sr][sc] = number;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.r;
            int c = p.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || map[nr][nc] != LAND) continue;
                isVisited[nr][nc] = true;
                map[nr][nc] = number;
                q.offer(new Pair(nr, nc, 0));
            }
        }
    }

    private static void setBridge(int number, int r, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(r, c, 0));
        isVisited[r][c] = true;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            r = p.r;
            c = p.c;
            int count = p.count;
            if (map[r][c] != SEA && map[r][c] != number) { // 다른 육지를 만나면 break
                min = Math.min(min, count);
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || map[nr][nc] == number) continue;
                isVisited[nr][nc] = true;
                pq.offer(new Pair(nr, nc, count + 1));
            }
        }
    }

    private static void initIsVisited() {
        for (int i = 0; i < N; i++)
            Arrays.fill(isVisited[i], false);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
