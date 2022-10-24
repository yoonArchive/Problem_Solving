package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1743_음식물피하기 {

    public static final int TRASH = 1;
    public static final int LUMPED = 2;

    public static int N, M, maxTrashSize;
    public static int[][] map;
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
        int K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = TRASH;
        }
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (map[r][c] == TRASH) {
                    lump(r, c);
                }
            }
        }
        bw.write(Integer.toString(maxTrashSize));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void lump(int sr, int sc) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sr, sc));
        map[sr][sc] = LUMPED;
        int trashSize = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isIn(nr, nc) && map[nr][nc] == TRASH) {
                    queue.offer(new Node(nr, nc));
                    map[nr][nc] = LUMPED;
                    trashSize++;
                }
            }
        }
        maxTrashSize = Math.max(maxTrashSize, trashSize);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r <= N && c <= M;
    }
}
