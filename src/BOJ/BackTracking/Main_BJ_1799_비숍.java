package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1799_비숍 {
    static final int BISHOP = -1;
    static int N, nr, nc, color;
    static boolean[][] boardColor; // true: black, false: white
    static int[][] board;
    static int[] maxCnt;
    static int dr[] = {-1, -1};
    static int dc[] = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        boardColor = new boolean[N][N];
        board = new int[N][N];
        maxCnt = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (i % 2 == 0 && j % 2 == 0)
                    boardColor[i][j] = true;
                else if (i % 2 == 1 && j % 2 == 1)
                    boardColor[i][j] = true;
            }
        }
        setBishop(0, 0, true); // 배열 인덱스, 놓은 비숍의 개수, 체스판 색상(0: black, 1:white)
        setBishop(1, 0, false);
        bw.write(Integer.toString(maxCnt[0] + maxCnt[1]));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void setBishop(int index, int count, boolean isBlack) {
        if (index >= N * N) {
            color = isBlack == true ? 0 : 1;
            maxCnt[color] = Math.max(maxCnt[color], count);
            return;
        }
        int r = index / N;
        int c = index % N;
        if (boardColor[r][c] == isBlack && board[r][c] == 1) {
            if (isPossible(r, c)) {
                board[r][c] = BISHOP;
                setBishop(index + 1, count + 1, isBlack);
                board[r][c] = 1;
            }
        }
        setBishop(index + 1, count, isBlack);
    }

    private static boolean isPossible(int r, int c) {
        // 대각선에 이미 비숍이 놓여있으면 false
        for (int i = 1; i <= r; i++) {
            for (int d = 0; d < 2; d++) {
                nr = r + dr[d] * i;
                nc = c + dc[d] * i;
                if (nr >= 0 && nc >= 0 && nr < N && nc < N && board[nr][nc] == BISHOP)
                    return false;
            }
        }
        return true;
    }

}