package BOJ.BruteForce;

import java.io.*;

public class Main_BJ_3085_사탕게임 {

    public static int N, count;
    public static char[][] candies;
    public static int[][] deltas = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        candies = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                candies[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (candies[i][j] != candies[i + 1][j]) {
                    exchange(i, j, i + 1, j);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (candies[i][j] != candies[i][j + 1]) {
                    exchange(i, j, i, j + 1);
                }
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void exchange(int r1, int c1, int r2, int c2) {
        char tmp = candies[r1][c1];
        candies[r1][c1] = candies[r2][c2];
        candies[r2][c2] = tmp;
        eatCandies();
        candies[r2][c2] = candies[r1][c1];
        candies[r1][c1] = tmp;
    }

    private static void eatCandies() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                count = Math.max(count, checkVertical(r, c));
                count = Math.max(count, checkHorizontal(r, c));
            }
        }
    }

    private static int checkVertical(int r, int c) {
        char candy = candies[r][c];
        int same = 0, nr = r;
        while (--nr >= 0) {
            if (candies[nr][c] != candy) {
                break;
            }
            same++;
        }
        nr = r;
        while (++nr < N) {
            if (candies[nr][c] != candy) {
                break;
            }
            same++;
        }
        return same + 1;
    }

    private static int checkHorizontal(int r, int c) {
        char candy = candies[r][c];
        int same = 0, nc = c;
        while (--nc >= 0) {
            if (candies[r][nc] != candy) {
                break;
            }
            same++;
        }
        nc = c;
        while (++nc < N) {
            if (candies[r][nc] != candy) {
                break;
            }
            same++;
        }
        return same + 1;
    }
}
