package BOJ.Shortest_Path;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_6087_레이저통신 {
    static final char C = 'C';
    static final char WALL = '*';
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    private static class Point implements Comparable<Point> {
        int r;
        int c;
        int mirror;
        int dir;

        public Point(int r, int c, int mirror, int dir) {
            this.r = r;
            this.c = c;
            this.mirror = mirror;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            return this.mirror - o.mirror;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        char[][] map = new char[H][W];
        int[][] visit = new int[H][W];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int endR = 0;
        int endC = 0;
        boolean flag = false;
        for (int i = 0; i < H; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == C) {
                    if (!flag) {
                        pq.add(new Point(i, j, 0, 0));
                        pq.add(new Point(i, j, 0, 1));
                        pq.add(new Point(i, j, 0, 2));
                        pq.add(new Point(i, j, 0, 3));
                        flag = true;
                        visit[i][j] = 0;
                    } else {
                        endR = i;
                        endC = j;
                    }
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Point point = pq.poll();
            int r = point.r;
            int c = point.c;
            int mirror = point.mirror;
            int dir = point.dir;
            if (r == endR && c == endC) {
                answer = mirror;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == WALL) {
                    continue;
                }
                if (d == dir) {
                    if (visit[nr][nc] >= mirror) {
                        visit[nr][nc] = mirror;
                        pq.add(new Point(nr, nc, mirror, d));
                    }
                } else {
                    if (visit[nr][nc] > mirror) {
                        visit[nr][nc] = mirror + 1;
                        pq.add(new Point(nr, nc, mirror + 1, d));
                    }
                }
            }
        }
        bw.write(Integer.toString(answer));
        br.close();
        bw.flush();
        bw.close();
    }

}
