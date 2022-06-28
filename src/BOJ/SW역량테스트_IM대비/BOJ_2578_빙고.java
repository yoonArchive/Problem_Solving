package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2578_빙고 {
    private static final int SIZE = 5;
    private static int lineNum;
    private static int[] dr = {-1, -1, 0, 1, -1, 0, 1, 1}; //상 우상 우 우하   좌상 좌 좌하 하
    private static int[] dc = {0, 1, 1, 1, -1, -1, -1, 0};

    public static class boardInfo {
        int num;
        boolean isChecked;

        public boardInfo(int num, boolean isChecked) {
            super();
            this.num = num;
            this.isChecked = isChecked;
        }
    }

    public static int lineCheck(int r, int c, int d, boardInfo[][] board) {
        int nr = r, nc = c;
        int checkedNum = 0;
        while (true) {
            nr += dr[d];
            nc += dc[d];
            if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && board[nr][nc].isChecked)
                checkedNum++;
            else
                break;
        }
        return checkedNum;
    }

    public static int getLine(int a, int b, boardInfo[][] board) {
        int dirLength = dr.length;
        int line = 0;
        for (int d = 0; d < 4; d++) {
            if (lineCheck(a, b, d, board) + lineCheck(a, b, dirLength - d - 1, board) + 1 == 5)
                line++;
        }
        return line;
    }

    public static boolean playBingo(boardInfo[][] board, int number) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boardInfo info = board[i][j];
                if (info.num == number) {
                    info.isChecked = true;
                    lineNum += getLine(i, j, board);
                    if (lineNum >= 3)
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boardInfo[][] board = new boardInfo[SIZE][SIZE];
        StringTokenizer st;

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < SIZE; j++)
                board[i][j] = new boardInfo(Integer.parseInt(st.nextToken()), false);
        }

        int r, c = 0;
        for (r = 0; r < SIZE; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (c = 0; c < SIZE; c++) {
                if (playBingo(board, Integer.parseInt(st.nextToken()))) {
                    System.out.println(r * SIZE + c + 1);
                    return;
                }

            }
        }
        br.close();
    }

}
