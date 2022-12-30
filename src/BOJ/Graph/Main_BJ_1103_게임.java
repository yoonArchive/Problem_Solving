package BOJ.Graph;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1103_게임 {

    public static final char HOLE = 'H';

    public static int N, M;
    public static char[][] board;
    public static int[][] counts;
    public static boolean[][] isVisited;
    public static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        counts = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        move(0, 0);
        bw.write(Integer.toString(counts[0][0]));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(int r, int c) {
        if (counts[r][c] != 0) {
            return counts[r][c];
        }
        isVisited[r][c] = true;
        int number = board[r][c] - '0';
        int count = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + (deltas[d][0] * number);
            int nc = c + (deltas[d][1] * number);
            if (!isIn(nr, nc) || board[nr][nc] == HOLE) {
                continue;
            }
            if (isVisited[nr][nc]) {
                System.out.println(-1);
                System.exit(0);
            }
            count = Math.max(count, move(nr, nc) + 1);
        }
        isVisited[r][c] = false;
        return counts[r][c] = count;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
