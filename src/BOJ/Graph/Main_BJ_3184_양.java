package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3184_양 {

    public static final char FENCE = '#';
    public static final char SHEEP = 'o';
    public static final char WOLF = 'v';

    public static int R, C, sheepCount, wolfCount;
    public static char[][] yard;
    public static boolean[][] isVisited;
    public static Queue<Node> wolfQueue;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, -1, 0, 1};

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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        yard = new char[R][C];
        isVisited = new boolean[R][C];
        wolfQueue = new LinkedList<>();
        char c;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                c = line.charAt(j);
                yard[i][j] = c;
                if (c == SHEEP) {
                    sheepCount++;
                } else if (c == WOLF) {
                    wolfQueue.offer(new Node(i, j));
                    wolfCount++;
                }
            }
        }
        findWolf();
        sb.append(sheepCount).append(" ").append(wolfCount);
        System.out.println(sb);
    }

    private static void findWolf() {
        while (!wolfQueue.isEmpty()) {
            Node node = wolfQueue.poll();
            int r = node.r;
            int c = node.c;
            if (!isVisited[r][c]) {
                fight(r, c);
            }
        }
    }

    private static void fight(int sr, int sc) {
        int currentWolfCount = 1, currentSheepCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sr, sc));
        isVisited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || yard[nr][nc] == FENCE) {
                    continue;
                }
                if (yard[nr][nc] == SHEEP) {
                    currentSheepCount++;
                } else if (yard[nr][nc] == WOLF) {
                    currentWolfCount++;
                }
                queue.offer(new Node(nr, nc));
                isVisited[nr][nc] = true;
            }
        }
        if (currentSheepCount > currentWolfCount) {
            wolfCount -= currentWolfCount;
        } else {
            sheepCount -= currentSheepCount;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
