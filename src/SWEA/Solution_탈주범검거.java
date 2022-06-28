package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_탈주범검거 {
    static int N, M;
    static int map[][];
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    static int[][] dir = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
    static int[][] dx = {{0}, {0, 0, -1, 1}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
    static int[][] dy = {{0}, {-1, 1, 0, 0}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};

    public static class Structure {
        int r, c, type;

        public Structure(int r, int c, int type) {
            super();
            this.r = r;
            this.c = c;
            this.type = type;
        }

    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("1953.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#" + tc + " " + search(R, C, L)).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static int search(int sr, int sc, int L) {
        Queue<Structure> q = new LinkedList<>();
        boolean isChecked[][] = new boolean[N][M];
        q.offer(new Structure(sr, sc, map[sr][sc]));
        isChecked[sr][sc] = true;
        int time = 0;
        int res = 1;

        while (!q.isEmpty()) {
            if (++time == L)
                break;
            int qSize = q.size();
            while (qSize-- > 0) {
                Structure s = q.poll();
                int r = s.r;
                int c = s.c;
                int type = s.type;
                for (int i = 0, length = dir[type].length; i < length; i++) {
                    int nr = r + deltas[dir[type][i]][0];
                    int nc = c + deltas[dir[type][i]][1];
                    if (!isIn(nr, nc) || map[nr][nc] == 0 || isChecked[nr][nc] || !isConnected(r, c, nr, nc))
                        continue;
                    q.offer(new Structure(nr, nc, map[nr][nc]));
                    isChecked[nr][nc] = true;
                    res++;
                }
            }
        }
        return res;

    }

    private static boolean isConnected(int r, int c, int nr, int nc) {
        if (map[nr][nc] == 1)
            return true;

        int curType = map[nr][nc];
        for (int i = 0, length = dx[curType].length; i < length; i++) {
            int rr = nr + dx[curType][i];
            int cc = nc + dy[curType][i];
            if (!isIn(rr, cc))
                continue;
            if (rr == r && cc == c)
                return true;
        }
        return false;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < M;
    }

}
