package BOJ.BruteForce;

import java.io.*;

public class Main_BJ_3085_사탕게임 {

    public static int N, maxCount;
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
        bw.write(Integer.toString(maxCount));
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
        for (int i = 0; i < N; i++) {
            checkVertical(i);
            checkHorizontal(i);
        }
    }

    private static void checkVertical(int r) {
        int count = 1;
        for (int c = 0; c < N - 1; c++) {
            if (candies[r][c] == candies[r][c + 1]) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        maxCount = Math.max(maxCount, count);
    }

    private static void checkHorizontal(int c) {
        int count = 1;
        for (int r = 0; r < N - 1; r++) {
            if (candies[r][c] == candies[r + 1][c]) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        maxCount = Math.max(maxCount, count);
    }
}
