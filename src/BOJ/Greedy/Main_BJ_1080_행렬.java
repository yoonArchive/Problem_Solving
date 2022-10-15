package BOJ.Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1080_행렬 {
    static BufferedReader br;
    static int N, M, count;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] matrixA = new int[N][M];
        int[][] matrixB = new int[N][M];
        setMatrix(matrixA);
        setMatrix(matrixB);
        reverse(matrixA, matrixB);
        if (!isEqual(matrixA, matrixB)) count = -1;
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void reverse(int[][] matrixA, int[][] matrixB) {
        for (int r = 0; r < N - 2; r++) {
            for (int c = 0; c < M - 2; c++) {
                if (matrixA[r][c] == matrixB[r][c]) {
                    continue;
                }
                count++;
                for (int sr = r; sr <= r + 2; sr++) {
                    for (int sc = c; sc <= c + 2; sc++) {
                        matrixA[sr][sc] = matrixA[sr][sc] == 0 ? 1 : 0;
                    }
                }
            }
        }
    }

    private static boolean isEqual(int[][] matrixA, int[][] matrixB) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (matrixA[r][c] != matrixB[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void setMatrix(int[][] matrix) throws IOException {
        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                matrix[r][c] = line.charAt(c) - '0';
            }
        }
    }
}
