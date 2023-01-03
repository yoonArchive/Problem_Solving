package BOJ.implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16234_인구이동 {

    public static int N, L, R;
    public static int[][] countries;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

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
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int day = 0, count = 0;
        while (true) {
            count = 0;
            boolean[][] isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j]) {
                        continue;
                    }
                    count += makeUnion(i, j, isVisited);
                }
            }
            if (count == 0) {
                break;
            }
            day++;
        }
        bw.write(Integer.toString(day));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int makeUnion(int sr, int sc, boolean[][] isVisited) {
        Queue<Node> searchQueue = new LinkedList<>();
        Queue<Node> changeQueue = new LinkedList<>();
        searchQueue.offer(new Node(sr, sc));
        changeQueue.offer(new Node(sr, sc));
        isVisited[sr][sc] = true;
        int sum = countries[sr][sc];
        int count = 1;
        while (!searchQueue.isEmpty()) {
            Node node = searchQueue.poll();
            int r = node.r;
            int c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc)) {
                    continue;
                }
                int gap = Math.abs(countries[r][c] - countries[nr][nc]);
                if (!isVisited[nr][nc] && gap >= L && gap <= R) {
                    searchQueue.offer(new Node(nr, nc));
                    changeQueue.offer(new Node(nr, nc));
                    isVisited[nr][nc] = true;
                    sum += countries[nr][nc];
                    count++;
                }
            }
        }
        if (count > 1) {
            move(changeQueue, sum / count);
            return 1;
        } else {
            return 0;
        }
    }

    private static void move(Queue<Node> changeQueue, int value) {
        while (!changeQueue.isEmpty()) {
            Node node = changeQueue.poll();
            countries[node.r][node.c] = value;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
