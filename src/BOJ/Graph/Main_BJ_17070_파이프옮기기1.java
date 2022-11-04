package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1 {

    public static final int WALL = 1;
    public static final char HORIZONTAL = 'H';
    public static final char VERTICAL = 'B';
    public static final char DIAGONAL = 'D';


    public static int N;
    public static int[][] board;
    public static Queue<Pipe> queue = new LinkedList<>();
    public static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static class Pipe {
        int r;
        int c;
        char direction;

        public Pipe(int r, int c, char direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (board[N - 1][N - 1] == WALL) {
            bw.write("0");
        } else {
            movePipe();
            bw.write(Integer.toString(movePipe()));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static int movePipe() {
        queue = new LinkedList<>();
        queue.offer(new Pipe(0, 1, HORIZONTAL));
        int count = 0;
        while (!queue.isEmpty()) {
            Pipe pipe = queue.poll();
            int r = pipe.r;
            int c = pipe.c;
            char direction = pipe.direction;
            if (r == N - 1 && c == N - 1) {
                count++;
                continue;
            }
            switch (direction) {
                case HORIZONTAL:
                    offer(r, c + 1, HORIZONTAL);
                    offer(r + 1, c + 1, DIAGONAL);
                    break;
                case VERTICAL:
                    offer(r + 1, c, VERTICAL);
                    offer(r + 1, c + 1, DIAGONAL);
                    break;
                case DIAGONAL:
                    offer(r, c + 1, HORIZONTAL);
                    offer(r + 1, c, VERTICAL);
                    offer(r + 1, c + 1, DIAGONAL);
                    break;
            }
        }
        return count;
    }

    private static void offer(int r, int c, char direction) {
        switch (direction) {
            case HORIZONTAL:
                if (c < N && board[r][c] != WALL) {
                    queue.offer(new Pipe(r, c, direction));
                }
                break;
            case VERTICAL:
                if (r < N && board[r][c] != WALL) {
                    queue.offer(new Pipe(r, c, direction));
                }
                break;
            case DIAGONAL:
                if (r < N && c < N && board[r - 1][c] != WALL && board[r][c - 1] != WALL && board[r ][c ] != WALL) {
                    queue.offer(new Pipe(r, c, direction));
                }
                break;
        }
    }
}
