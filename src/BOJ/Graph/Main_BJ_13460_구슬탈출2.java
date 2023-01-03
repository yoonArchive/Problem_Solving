package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13460_구슬탈출2 {

    public static final char RED = 'R';
    public static final char BLUE = 'B';
    public static final char HOLE = 'O';
    public static final char WALL = '#';

    public static int N, M;
    public static char[][] board;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class BoardStatus {
        int redR;
        int redC;
        int blueR;
        int blueC;
        int count;

        public BoardStatus(int redR, int redC, int blueR, int blueC, int count) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        char current;
        BoardStatus boardStatus = new BoardStatus(0, 0, 0, 0, 0);
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                current = line.charAt(j);
                if (current == RED) {
                    boardStatus.redR = i;
                    boardStatus.redC = j;
                } else if (current == BLUE) {
                    boardStatus.blueR = i;
                    boardStatus.blueC = j;
                }
                board[i][j] = current;
            }
        }
        bw.write(Integer.toString(startGame(boardStatus)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int startGame(BoardStatus startBoardStatus) {
        int size = N * M;
        Queue<BoardStatus> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[size][size];
        queue.offer(startBoardStatus);
        isVisited[startBoardStatus.redR * M + (startBoardStatus.redC - 1)][startBoardStatus.blueR * M + (startBoardStatus.blueC - 1)] = true;
        int[] nextLocation = new int[4];
        while (!queue.isEmpty()) {
            BoardStatus boardStatus = queue.poll();
            int redR = boardStatus.redR;
            int redC = boardStatus.redC;
            int blueR = boardStatus.blueR;
            int blueC = boardStatus.blueC;
            int count = boardStatus.count;
            if (board[redR][redC] == HOLE) {
                return count;
            }
            if (count > 10) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                nextLocation = tilt(redR, redC, blueR, blueC, d, nextLocation);
                int nRedR = nextLocation[0];
                int nRedC = nextLocation[1];
                int nBlueR = nextLocation[2];
                int nBlueC = nextLocation[3];
                if (isVisited[nRedR * M + nRedC - 1][nBlueR * M + nBlueC - 1] || board[nBlueR][nBlueC] == HOLE) {
                    continue;
                }
                queue.offer(new BoardStatus(nRedR, nRedC, nBlueR, nBlueC, count + 1));
                isVisited[nRedR * M + nRedC - 1][nBlueR * M + nBlueC - 1] = true;
            }
        }
        return -1;
    }

    private static int[] tilt(int preRedR, int preRedC, int preBlueR, int preBlueC, int d, int[] nextLocation) {
        int nRedR = preRedR, nRedC = preRedC, nBlueR = preBlueR, nBlueC = preBlueC;
        boolean isRedBallInHole = false;
        while (true) {
            if (!isRedBallInHole) {
                nRedR += deltas[d][0];
                nRedC += deltas[d][1];
            }
            nBlueR += deltas[d][0];
            nBlueC += deltas[d][1];
            if (isWall(nRedR, nRedC)) {
                nRedR -= deltas[d][0];
                nRedC -= deltas[d][1];
            }
            if (isWall(nBlueR, nBlueC)) {
                nBlueR -= deltas[d][0];
                nBlueC -= deltas[d][1];
            }
            if (isSameLocation(nRedR, nRedC, nBlueR, nBlueC)) {
                if (board[nRedR][nRedC] == HOLE) {
                    break;
                }
                if (isSameLocation(preRedR, preRedC, nRedR, nRedC)) {
                    nBlueR -= deltas[d][0];
                    nBlueC -= deltas[d][1];
                } else if (isSameLocation(preBlueR, preBlueC, nBlueR, nBlueC)) {
                    nRedR -= deltas[d][0];
                    nRedC -= deltas[d][1];
                }
            }
            if (board[nRedR][nRedC] == HOLE) {
                isRedBallInHole = true;
            }
            if (board[nBlueR][nBlueC] == HOLE || (isSameLocation(preRedR, preRedC, nRedR, nRedC) && isSameLocation(preBlueR, preBlueC, nBlueR, nBlueC))) {
                break;
            }
            preRedR = nRedR;
            preRedC = nRedC;
            preBlueR = nBlueR;
            preBlueC = nBlueC;
        }
        nextLocation[0] = nRedR;
        nextLocation[1] = nRedC;
        nextLocation[2] = nBlueR;
        nextLocation[3] = nBlueC;
        return nextLocation;
    }

    private static boolean isSameLocation(int r, int c, int nr, int nc) {
        return r == nr && c == nc;
    }

    private static boolean isWall(int r, int c) {
        return board[r][c] == WALL;
    }
}
