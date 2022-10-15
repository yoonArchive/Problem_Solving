package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_7569_토마토 {

    public static final int UNRIPED_TOMATO = 0;
    public static final int RIPED_TOMATO = 1;

    public static int M, N, H, unripedCount, minDay;
    public static int[][][] tomatoes;
    public static PriorityQueue<Tomato> pq;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static class Tomato implements Comparable<Tomato> {
        int r;
        int c;
        int h;
        int day;

        public Tomato(int r, int c, int h, int day) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.day = day;
        }

        public int compareTo(Tomato o) {
            return this.day - o.day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatoes = new int[N][M][H];
        pq = new PriorityQueue<>();
        int current = 0;
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    current = Integer.parseInt(st.nextToken());
                    tomatoes[r][c][h] = current;
                    if (current == UNRIPED_TOMATO) {
                        unripedCount++;
                    } else if (current == RIPED_TOMATO) {
                        pq.offer(new Tomato(r, c, h, 0));
                    }
                }
            }
        }
        if (unripedCount == 0) {
            bw.write("0");
        } else {
            spread();
            if (unripedCount > 0) {
                bw.write("-1");
            } else {
                bw.write(Integer.toString(minDay + 1));
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void spread() {
        while (!pq.isEmpty()) {
            Tomato tomato = pq.poll();
            int r = tomato.r;
            int c = tomato.c;
            int h = tomato.h;
            int day = tomato.day;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc, h) || tomatoes[nr][nc][h] != UNRIPED_TOMATO) {
                    continue;
                }
                pq.offer(new Tomato(nr, nc, h, day + 1));
                tomatoes[nr][nc][h] = RIPED_TOMATO;
                unripedCount--;
            }
            for (int d = -1; d <= 1; d++) {
                if (d == 0) {
                    continue;
                }
                int nh = h + d;
                if (!isIn(r, c, nh) || tomatoes[r][c][nh] != UNRIPED_TOMATO) {
                    continue;
                }
                pq.offer(new Tomato(r, c, nh, day + 1));
                tomatoes[r][c][nh] = RIPED_TOMATO;
                unripedCount--;
            }
            if (unripedCount <= 0) {
                minDay = day;
                break;
            }
        }
    }

    private static boolean isIn(int r, int c, int h) {
        return r >= 0 && c >= 0 && h >= 0 && r < N && c < M && h < H;
    }
}
