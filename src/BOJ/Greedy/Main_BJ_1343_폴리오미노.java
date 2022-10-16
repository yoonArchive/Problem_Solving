package BOJ.Greedy;

import java.io.*;

public class Main_BJ_1343_폴리오미노 {

    public static final String POLYOMINO_A = "AAAA";
    public static final String POLYOMINO_B = "BB";
    public static final char X = 'X';
    public static final char POINT = '.';

    public static char[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = br.readLine().toCharArray();
        int length = board.length;
        int index = 0;
        boolean isPossible = true;
        while (index < length) {
            if (board[index] == X) {
                int end = findEnd(index, length);
                int currentXlen = end - index + 1;
                if (currentXlen % 2 != 0) {
                    isPossible = false;
                    break;
                }
                changeBoard(index, end, currentXlen);
                index = end + 1;
                continue;
            }
            index++;
        }
        if (!isPossible) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < length; i++) {
                System.out.print(board[i]);
            }
        }
        br.close();
    }

    private static void changeBoard(int start, int end, int xLen) {
        if (xLen == POLYOMINO_A.length()) {
            changeToPolyA(start, end);
        } else if (xLen == POLYOMINO_B.length()) {
            changeToPolyB(start, end);
        } else {
            int endIndexOfPolyA = start + (xLen / POLYOMINO_A.length()) * POLYOMINO_A.length() - 1;
            changeToPolyA(start, endIndexOfPolyA);
            changeToPolyB(endIndexOfPolyA + 1, end);
        }
    }

    private static void changeToPolyA(int start, int end) {
        for (int i = start; i <= end; i++) {
            board[i] = 'A';
        }
    }

    private static void changeToPolyB(int start, int end) {
        for (int i = start; i <= end; i++) {
            board[i] = 'B';
        }
    }

    private static int findEnd(int index, int length) {
        for (int i = index + 1; i < length; i++) {
            if (board[i] == POINT) {
                return i - 1;
            }
            if (i == length - 1) {
                return length - 1;
            }
        }
        return index;
    }
}
