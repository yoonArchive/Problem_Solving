package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_5427_불 {

    public static final char WALL = '#';
    public static final char START = '@';
    public static final char FIRE = '*';
    public static final char VISITED = 'v';

    public static int w, h;
    public static char[][] map;
    public static Queue<Space> fireQueue;
    public static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static class Space {
        int r;
        int c;
        int time;

        public Space(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Space(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            fireQueue = new LinkedList<>();
            int sr = 0, sc = 0;
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char current = line.charAt(j);
                    if (current == START) {
                        sr = i;
                        sc = j;
                    } else if (current == FIRE) {
                        fireQueue.offer(new Space(i, j));
                    }
                    map[i][j] = current;
                }
            }
            int result = escape(sr, sc);
            sb.append(result == -1 ? "IMPOSSIBLE" : result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int escape(int sr, int sc) {
        Queue<Space> moveQueue = new LinkedList<>();
        moveQueue.offer(new Space(sr, sc, 0));
        map[sr][sc] = VISITED;
        int queueSize = 0;
        while (!moveQueue.isEmpty()) {
            spreadFire();
            queueSize = moveQueue.size();
            while (queueSize-- > 0) {
                Space space = moveQueue.poll();
                int r = space.r;
                int c = space.c;
                int time = space.time;
                if (!isIn(r, c)) {
                    return time;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];
                    if (!isIn(nr, nc)) {
                        moveQueue.offer(new Space(nr, nc, time + 1));
                    }
                    if (!isIn(nr, nc) || map[nr][nc] == FIRE || map[nr][nc] == WALL || map[nr][nc] == VISITED) {
                        continue;
                    }
                    moveQueue.offer(new Space(nr, nc, time + 1));
                    map[nr][nc] = VISITED;
                }
            }
        }
        return -1;
    }

    private static void spreadFire() {
        int size = fireQueue.size();
        while (size-- > 0) {
            Space space = fireQueue.poll();
            int r = space.r;
            int c = space.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || map[nr][nc] == WALL || map[nr][nc] == FIRE) {
                    continue;
                }
                map[nr][nc] = FIRE;
                fireQueue.offer(new Space(nr, nc));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < h && c < w;
    }
}
