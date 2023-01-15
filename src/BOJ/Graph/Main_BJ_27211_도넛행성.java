package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_27211_도넛행성 {

    public static final int EMPTY = 0;
    public static final int VISITED = 2;
    public static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static int N, M;
    public static int[][] planet;

    public static class Space {
        int r;
        int c;

        public Space(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        planet = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                planet[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (planet[i][j] == EMPTY) {
                    explore(i, j);
                    count++;
                }
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void explore(int sr, int sc) {
        Queue<Space> queue = new LinkedList<>();
        queue.offer(new Space(sr, sc));
        planet[sr][sc] = VISITED;
        while (!queue.isEmpty()) {
            Space space = queue.poll();
            int r = space.r;
            int c = space.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];
                if (!isIn(nr, true)) {
                    nr = convert(nr, true);
                }
                if (!isIn(nc, false)) {
                    nc = convert(nc, false);
                }
                if (planet[nr][nc] == VISITED || planet[nr][nc] != EMPTY) {
                    continue;
                }
                queue.offer(new Space(nr, nc));
                planet[nr][nc] = VISITED;
            }
        }
    }

    private static boolean isIn(int index, boolean isRow) {
        return isRow ? index >= 0 && index < N : index >= 0 && index < M;
    }

    private static int convert(int index, boolean isRow) {
        if (isRow) {
            return index < 0 ? N - 1 : 0;
        } else {
            return index < 0 ? M - 1 : 0;
        }
    }
}
