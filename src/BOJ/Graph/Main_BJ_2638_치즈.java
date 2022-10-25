package BOJ.Graph;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2638_치즈 {

    public static final int AIR = 0;
    public static final int CHEESE = 1;

    public static int N, M, cheeseCount, time;
    public static int[][] board, contactCounts;
    public static int[] dr = {-1, 0, 0, 1};
    public static int[] dc = {0, 1, -1, 0};

    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        contactCounts = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int current = Integer.parseInt(st.nextToken());
                board[i][j] = current;
                if (current == CHEESE) {
                    cheeseCount++;
                }
            }
        }
        while (cheeseCount > 0) {
            time++;
            outer:
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] == AIR) {
                        findOutsideAir(r, c);
                        break outer;
                    }
                }
            }
            melt();
            reset();
        }
        bw.write(Integer.toString(time));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void findOutsideAir(int sr, int sc) {
        Queue<Node> outsideAirQueue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][M];
        outsideAirQueue.offer(new Node(sr, sc));
        isVisited[sr][sc] = true;
        while (!outsideAirQueue.isEmpty()) {
            Node node = outsideAirQueue.poll();
            int r = node.r;
            int c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || board[nr][nc] != AIR) {
                    continue;
                }
                findContactCheese(nr, nc);
                board[nr][nc] = AIR;
                outsideAirQueue.offer(new Node(nr, nc));
                isVisited[nr][nc] = true;
            }
        }
    }

    private static void findContactCheese(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isIn(nr, nc) || board[nr][nc] != CHEESE) {
                continue;
            }
            contactCounts[nr][nc]++;
        }
    }

    private static void melt() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (contactCounts[r][c] >= 2) {
                    board[r][c] = AIR;
                    cheeseCount--;
                }
            }
        }
    }

    private static void reset() {
        for (int r = 0; r < N; r++) {
            Arrays.fill(contactCounts[r], 0);
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
