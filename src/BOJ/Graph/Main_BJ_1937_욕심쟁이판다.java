package BOJ.Graph;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1937_욕심쟁이판다 {

    public static int N;
    public static int[][] trees, counts;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        trees = new int[N][N];
        counts = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                trees[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxCount = Math.max(maxCount, move(i, j));
            }
        }
        bw.write(Integer.toString(maxCount));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(int r, int c) {
        if (counts[r][c] != 0) {
            return counts[r][c];
        }
        int count = 1;
        int currentTree = trees[r][c];
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isIn(nr, nc) || currentTree >= trees[nr][nc]) {
                continue;
            }
            count = Math.max(count, move(nr, nc) + 1);
        }
        return counts[r][c] = count;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
