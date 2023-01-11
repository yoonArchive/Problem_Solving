package BOJ.Implementation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_12100_2048 {

    public static final int EMPTY = 0;

    public static int N, maxNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        play2048(0, board);
        bw.write(Integer.toString(maxNumber));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void play2048(int count, int[][] currentBoard) {
        if (count == 5) {
            updateMaxNumber(currentBoard);
            return;
        }
        ++count;
        play2048(count, moveBlocksUp(copy(currentBoard)));
        play2048(count, moveBlocksDown(copy(currentBoard)));
        play2048(count, moveBlocksLeft(copy(currentBoard)));
        play2048(count, moveBlocksRight(currentBoard));
    }

    private static int[][] copy(int[][] currentBoard) {
        int copy[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = currentBoard[i][j];
            }
        }
        return copy;
    }

    private static int[][] moveBlocksUp(int[][] board) {
        boolean[][] isVisited = new boolean[N][N];
        // 숫자 합치기
        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N - 1; r++) {
                if (isVisited[r][c]) {
                    continue;
                }
                int currentNumber = board[r][c];
                int nr = r + 1;
                boolean findFlag = false;
                while (nr < N) {
                    if (board[nr][c] == currentNumber) {
                        findFlag = true;
                        break;
                    }
                    if (board[nr][c] == EMPTY) {
                        nr++;
                    } else {
                        break;
                    }
                }
                if (findFlag) {
                    board[r][c] *= 2;
                    board[nr][c] = EMPTY;
                    isVisited[nr][c] = true;
                }
            }
        }
        // 위로 올리기
        for (int c = 0; c < N; c++) {
            for (int r = 1; r <= N - 1; r++) {
                if (board[r - 1][c] == EMPTY) {
                    int nr = r - 1;
                    while (nr - 1 >= 0) {
                        --nr;
                        if (board[nr][c] != EMPTY) {
                            ++nr;
                            break;
                        }
                    }
                    board[nr][c] = board[r][c];
                    board[r][c] = EMPTY;
                }
            }
        }
        return board;
    }

    private static int[][] moveBlocksDown(int[][] board) {
        boolean[][] isVisited = new boolean[N][N];
        // 숫자 합치기
        for (int c = 0; c < N; c++) {
            for (int r = N - 1; r > 0; r--) {
                if (isVisited[r][c]) {
                    continue;
                }
                int currentNumber = board[r][c];
                int nr = r - 1;
                boolean findFlag = false;
                while (nr >= 0) {
                    if (board[nr][c] == currentNumber) {
                        findFlag = true;
                        break;
                    }
                    if (board[nr][c] == EMPTY) {
                        nr--;
                    } else {
                        break;
                    }
                }
                if (findFlag) {
                    board[r][c] *= 2;
                    board[nr][c] = EMPTY;
                    isVisited[nr][c] = true;
                }
            }
        }
        // 아래로 내리기
        for (int c = 0; c < N; c++) {
            for (int r = N - 2; r >= 0; r--) {
                if (board[r + 1][c] == EMPTY) {
                    int nr = r + 1;
                    while (nr + 1 < N) {
                        ++nr;
                        if (board[nr][c] != EMPTY) {
                            --nr;
                            break;
                        }
                    }
                    board[nr][c] = board[r][c];
                    board[r][c] = EMPTY;
                }
            }
        }
        return board;
    }

    private static int[][] moveBlocksLeft(int[][] board) {
        boolean[][] isVisited = new boolean[N][N];
        // 숫자 합치기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N - 1; c++) {
                if (isVisited[r][c]) {
                    continue;
                }
                int currentNumber = board[r][c];
                int nc = c + 1;
                boolean findFlag = false;
                while (nc < N) {
                    if (board[r][nc] == currentNumber) {
                        findFlag = true;
                        break;
                    }
                    if (board[r][nc] == EMPTY) {
                        nc++;
                    } else {
                        break;
                    }
                }
                if (findFlag) {
                    board[r][c] *= 2;
                    board[r][nc] = EMPTY;
                    isVisited[r][nc] = true;
                }
            }
        }
        // 왼쪽으로 이동
        for (int r = 0; r < N; r++) {
            for (int c = 1; c <= N - 1; c++) {
                if (board[r][c - 1] == EMPTY) {
                    int nc = c - 1;
                    while (nc - 1 >= 0) {
                        --nc;
                        if (board[r][nc] != EMPTY) {
                            ++nc;
                            break;
                        }
                    }
                    board[r][nc] = board[r][c];
                    board[r][c] = EMPTY;
                }
            }
        }
        return board;
    }

    private static int[][] moveBlocksRight(int[][] board) {
        boolean[][] isVisited = new boolean[N][N];
        // 숫자 합치기
        for (int r = 0; r < N; r++) {
            for (int c = N - 1; c > 0; c--) {
                if (isVisited[r][c]) {
                    continue;
                }
                int currentNumber = board[r][c];
                int nc = c - 1;
                boolean findFlag = false;
                while (nc >= 0) {
                    if (board[r][nc] == currentNumber) {
                        findFlag = true;
                        break;
                    }
                    if (board[r][nc] == EMPTY) {
                        nc--;
                    } else {
                        break;
                    }
                }
                if (findFlag) {
                    board[r][c] *= 2;
                    board[r][nc] = EMPTY;
                    isVisited[r][nc] = true;
                }
            }
        }
        // 오른쪽으로 이동
        for (int r = 0; r < N; r++) {
            for (int c = N - 2; c >= 0; c--) {
                if (board[r][c + 1] == EMPTY) {
                    int nc = c + 1;
                    while (nc + 1 < N) {
                        ++nc;
                        if (board[r][nc] != EMPTY) {
                            --nc;
                            break;
                        }
                    }
                    board[r][nc] = board[r][c];
                    board[r][c] = EMPTY;
                }
            }
        }
        return board;
    }

    private static void updateMaxNumber(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > maxNumber) {
                    maxNumber = board[i][j];
                }
            }
        }
    }
}
