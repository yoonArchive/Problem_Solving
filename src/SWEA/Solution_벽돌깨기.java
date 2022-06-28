package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_벽돌깨기 {
    static int N, W, H;
    static int originBricks[][];
    static int[] selected;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, -1, 0, 1};
    static int res;

    public static class Brick {
        int r, c, num;

        public Brick(int r, int c, int num) {
            super();
            this.r = r;
            this.c = c;
            this.num = num;
        }

    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            originBricks = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    originBricks[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            selected = new int[N];
            res = Integer.MAX_VALUE;
            selectCols(0);
            sb.append("#" + tc + " " + res).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static void selectCols(int cnt) {
        if (cnt == N) {
            shoot();
            return;
        }
        for (int i = 0; i < W; i++) {
            selected[cnt] = i;
            selectCols(cnt + 1);
        }
    }

    private static void shoot() {
        int[][] copyMap = copy();
        for (int i = 0; i < N; i++) {
            int row = -1, col = selected[i];
            boolean empty = true;
            for (int j = 0; j < H; j++) {
                if (copyMap[j][col] != 0) {
                    row = j;
                    empty = false;
                    break;
                }
            }
            if (empty)
                continue;

            Queue<Brick> q = new LinkedList<>();
            q.offer(new Brick(row, col, copyMap[row][col]));
            copyMap[row][col] = 0;

            while (!q.isEmpty()) {
                Brick b = q.poll();
                int r = b.r;
                int c = b.c;
                int num = b.num;
                for (int iter = 0; iter < num; iter++) {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + (iter * dr[d]);
                        int nc = c + (iter * dc[d]);
                        if (!isIn(nr, nc) || copyMap[nr][nc] == 0)
                            continue;
                        if (copyMap[nr][nc] > 1)
                            q.offer(new Brick(nr, nc, copyMap[nr][nc]));
                        copyMap[nr][nc] = 0;
                    }
                }
            }
            fallBricks(copyMap); // 밑으로 떨어뜨리기
            res = Math.min(res, remainBricks(copyMap));
            if (res == 0)
                break;
        }

    }

    private static void fallBricks(int map[][]) {
        Queue<Brick> q = new LinkedList<>();
        for (int i = 0; i < W; i++) {
            for (int j = H - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    q.offer(new Brick(j, i, map[j][i]));
                    map[j][i] = 0;
                }
            }
            // 큐에서 꺼내면서 밑에서부터 쌓기
            int cRow = H - 1;
            while (!q.isEmpty()) {
                Brick b = q.poll();
                int num = b.num;
                map[cRow--][i] = num;
            }
        }
    }

    private static int remainBricks(int map[][]) {
        int remains = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0)
                    remains++;
            }
        }
        return remains;
    }

    private static int[][] copy() {
        int[][] copyMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copyMap[i][j] = originBricks[i][j];
            }
        }
        return copyMap;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < H && nc < W;
    }

}
