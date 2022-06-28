package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14500_테트로미노 {
    static int[][] paper;
    static int N, M;
    static int maxSum;
    static int dr[] = {0, 1, -1, 0};
    static int dc[] = {1, 0, 0, -1};

    public static class Pair {
        int r, c;

        public Pair(int r, int c) {
            super();
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
        paper = new int[N][M];
        boolean isVisited[][] = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++)
                    Arrays.fill(isVisited[k], false);
                isVisited[i][j] = true;
                dfs(i, j, 1, paper[i][j], isVisited);

            }
        }

        int barSize = 3;
        for (int i = 0; i <= N - barSize; i++) {
            for (int j = 0; j < M; j++) {
                putVerticalBar(i, j, 0, 0);

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M - barSize; j++) {
                putHorizontalBar(i, j, 0, 0);
            }
        }

        bw.write(Integer.toString(maxSum));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int r, int c, int count, int sum, boolean isVisited[][]) {
        if (count == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }
        isVisited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || isVisited[nr][nc])
                continue;
            isVisited[nr][nc] = true;
            dfs(nr, nc, count + 1, sum + paper[nr][nc], isVisited);
            isVisited[nr][nc] = false;
        }
    }

    public static void putVerticalBar(int r, int c, int count, int sum) {
        while (true) {
            sum += paper[r++][c];
            if (++count == 3) break;
        }
        for (int i = 0; i < 2; i++) {
            int midR = r - 2;
            int newC = 0;
            if (i == 0) newC = c - 1;
            else newC = c + 1;
            if (newC < 0 || newC >= M)
                continue;
            maxSum = Math.max(maxSum, sum + paper[midR][newC]);
        }
    }

    public static void putHorizontalBar(int r, int c, int count, int sum) {
        while (true) {
            sum += paper[r][c++];
            if (++count == 3) break;
        }
        for (int i = 0; i < 2; i++) {
            int midC = c - 2;
            int newR = 0;
            if (i == 0) newR = r - 1;
            else newR = r + 1;
            if (newR < 0 || newR >= N)
                continue;
            maxSum = Math.max(maxSum, sum + paper[newR][midC]);
        }
    }
}
