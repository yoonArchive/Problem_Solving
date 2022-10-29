package BOJ.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16918_봄버맨 {

    public static final char EMPTY = '.';
    public static final char BOMB = 'O';
    public static final int EXPLODE_TIME = 3;

    public static int R, C, N;
    public static char[][] map;
    public static Queue<Bomb> bombQueue;
    public static int[] dr = {-1, 0, 0, 1};
    public static int[] dc = {0, 1, -1, 0};

    public static class Bomb {
        int r;
        int c;
        int time;

        public Bomb(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        bombQueue = new LinkedList<>();
        char c;
        int time = 1;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                c = line.charAt(j);
                map[i][j] = c;
                if (c == BOMB) {
                    bombQueue.offer(new Bomb(i, j, time));
                }
            }
        }
        while (time < N) {
            ++time;
            increaseBombTime();
            installBomb();
            if (time == N) {
                break;
            }
            ++time;
            increaseBombTime();
            explode();
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void increaseBombTime() {
        int size = bombQueue.size();
        while (size-- > 0) {
            Bomb bomb = bombQueue.poll();
            int r = bomb.r;
            int c = bomb.c;
            int time = bomb.time;
            bombQueue.offer(new Bomb(r, c, time + 1));
        }
    }

    private static void installBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = BOMB;
                    bombQueue.offer(new Bomb(i, j, 0));
                }
            }
        }
    }

    private static void explode() {
        int size = bombQueue.size();
        while (size-- > 0) {
            Bomb bomb = bombQueue.poll();
            int r = bomb.r;
            int c = bomb.c;
            int time = bomb.time;
            if (time == EXPLODE_TIME) {
                map[r][c] = EMPTY;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!isIn(nr, nc) || map[nr][nc] == EMPTY) {
                        continue;
                    }
                    map[nr][nc] = EMPTY;
                }
            } else if (map[r][c] == BOMB) {
                bombQueue.offer(new Bomb(r, c, time));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
