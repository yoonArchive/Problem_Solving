package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13459_구슬탈출 {

    public static final char EMPTY = '.';
    public static final char OBSTACLE = '#';
    public static final char HOLE = 'O';
    public static final char RED = 'R';
    public static final char BLUE = 'B';

    public static int N, M;
    public static char[][] board;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static class Balls {
        Ball redBall;
        Ball blueBall;
        int count;

        public Balls(Ball redBall, Ball blueBall, int count) {
            this.redBall = redBall;
            this.blueBall = blueBall;
            this.count = count;
        }

        public boolean equals(Object o) {
            Balls b = (Balls) o;
            return redBall.equals(b.redBall) && blueBall.equals(b.blueBall);
        }
    }

    public static class Ball {
        int r;
        int c;

        public Ball(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object o) {
            Ball b = (Ball) o;
            return r == b.r && c == b.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        Ball redBall = null, blueBall = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == RED) {
                    redBall = new Ball(i, j);
                } else if (c == BLUE) {
                    blueBall = new Ball(i, j);
                }
                board[i][j] = c;
            }
        }
        int size = N * M;
        Queue<Balls> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[size - 1][size - 1];
        Balls balls = new Balls(redBall, blueBall, 0);
        queue.offer(balls);
        isVisited[getIndex(redBall)][getIndex(blueBall)] = true;
        bw.write(Integer.toString(move(queue, isVisited)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(Queue<Balls> queue, boolean[][] isVisited) {
        while (!queue.isEmpty()) {
            Balls balls = queue.poll();
            Ball redBall = balls.redBall;
            Ball blueBall = balls.blueBall;
            int rr = redBall.r;
            int rc = redBall.c;
            int br = blueBall.r;
            int bc = blueBall.c;
            int count = balls.count;
            if (board[rr][rc] == HOLE) {
                return 1;
            }
            if (count == 10) {
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int[] nextLocation;
                nextLocation = getNextLocation(rr, rc, br, bc, d);
                if (board[nextLocation[2]][nextLocation[3]] == HOLE || (nextLocation[0] == nextLocation[2] && nextLocation[1] == nextLocation[3])) {
                    continue;
                }
                redBall = new Ball(nextLocation[0], nextLocation[1]);
                blueBall = new Ball(nextLocation[2], nextLocation[3]);
                Balls nBalls = new Balls(redBall, blueBall, count + 1);
                int rIndex = getIndex(redBall);
                int bIndex = getIndex(blueBall);
                if (!isVisited[rIndex][bIndex]) {
                    queue.offer(nBalls);
                    isVisited[rIndex][bIndex] = true;
                }
            }
        }
        return 0;
    }

    private static int[] getNextLocation(int rr, int rc, int br, int bc, int direction) {
        int nrr = rr, nrc = rc, nbr = br, nbc = bc;
        boolean redStop = false, blueStop = false;
        while (true) {
            if (redStop && blueStop) {
                break;
            }
            if (!redStop) {
                nrr += deltas[direction][0];
                nrc += deltas[direction][1];
            }
            if (!blueStop) {
                nbr += deltas[direction][0];
                nbc += deltas[direction][1];
            }

            if (board[nrr][nrc] == HOLE) {
                redStop = true;
            } else if (board[nrr][nrc] == OBSTACLE) {
                nrr -= deltas[direction][0];
                nrc -= deltas[direction][1];
                redStop = true;
            }
            if (board[nbr][nbc] == HOLE) {
                blueStop = true;
            } else if (board[nbr][nbc] == OBSTACLE) {
                nbr -= deltas[direction][0];
                nbc -= deltas[direction][1];
                blueStop = true;
            }
            if (nrr == nbr && nrc == nbc && board[nrr][nrc] != HOLE) {
                if (redStop) {
                    nbr -= deltas[direction][0];
                    nbc -= deltas[direction][1];
                    blueStop = true;
                } else if (blueStop) {
                    nrr -= deltas[direction][0];
                    nrc -= deltas[direction][1];
                    redStop = true;
                } else {
                    break;
                }
            }
        }
        return new int[]{nrr, nrc, nbr, nbc};
    }

    private static int getIndex(Ball ball) {
        return ball.r * M + ball.c;
    }
}
