package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1303_전쟁 {
    public static final char BLUE = 'B';
    public static final char VISITED = '.';

    public static int N, M, count;
    public static char[][] colors;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        colors = new char[N][M];
        for (int i = 0; i < N; i++) {
            colors[i] = br.readLine().toCharArray();
        }
        int bluePower = 0, whitePower = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char currentColor = colors[i][j];
                if (currentColor == VISITED) {
                    continue;
                }
                unite(currentColor, i, j);
                if (currentColor == BLUE) {
                    bluePower += count * count;
                } else {
                    whitePower += count * count;
                }
                count = 0;
            }
        }
        sb.append(whitePower).append(" ").append(bluePower);
        System.out.println(sb);
        br.close();
    }

    private static void unite(char color, int r, int c) {
        colors[r][c] = VISITED;
        count++;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isIn(nr, nc) || colors[nr][nc] == VISITED || colors[nr][nc] != color) {
                continue;
            }
            unite(color, nr, nc);
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
