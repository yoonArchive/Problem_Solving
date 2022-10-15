package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2178_미로탐색_2 {

    static final int MOVABLE = 1;
    static int N, M;
    static int arr[][];
    static int dr[] = {-1, 0, 0, 1};
    static int dc[] = {0, 1, -1, 0};

    public static class Node {
        int r;
        int c;
        int count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = line.charAt(j - 1) - '0';
            }
        }
        bw.write(Integer.toString(move()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        Queue<Node> queue = new LinkedList<>();
        boolean isVisited[][] = new boolean[N + 1][M + 1];
        queue.offer(new Node(1, 1, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            int count = node.count;
            if (r == N && c == M) {
                return count + 1;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc] || arr[nr][nc] != MOVABLE) {
                    continue;
                }
                isVisited[nr][nc] = true;
                queue.offer(new Node(nr, nc, count + 1));
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= M;
    }
}
