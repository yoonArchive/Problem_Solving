package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_17141_연구소2 {

    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int CANDIDATE = 2;

    public static int N, M, emptyCount, minTime;
    public static int[][] lab;
    public static List<Space> candidates;
    public static Space[] selected;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class Space {
        int r;
        int c;
        int time;

        public Space(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][N];
        candidates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int current = Integer.parseInt(st.nextToken());
                if (current == CANDIDATE) {
                    candidates.add(new Space(i, j, 0));
                    emptyCount++;
                } else if (current == EMPTY) {
                    emptyCount++;
                }
                lab[i][j] = current;
            }
        }
        minTime = Integer.MAX_VALUE;
        selected = new Space[M];
        setVirus(0, 0);
        bw.write(Integer.toString(minTime == Integer.MAX_VALUE ? -1 : minTime));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void setVirus(int index, int count) {
        if (count == M) {
            minTime = Math.min(minTime, spreadVirus());
            return;
        }
        for (int i = index; i < candidates.size(); i++) {
            selected[count] = candidates.get(i);
            setVirus(i + 1, count + 1);
        }
    }

    private static int spreadVirus() {
        int count = emptyCount;
        Queue<Space> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][N];
        for (Space space : selected) {
            queue.offer(space);
            isVisited[space.r][space.c] = true;
        }
        while (!queue.isEmpty()) {
            Space space = queue.poll();
            int r = space.r;
            int c = space.c;
            int time = space.time;
            if (lab[r][c] == EMPTY || lab[r][c] == CANDIDATE) {
                count--;
            }
            if (count == 0) {
                return time;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || isVisited[nr][nc] || lab[nr][nc] == WALL) {
                    continue;
                }
                isVisited[nr][nc] = true;
                queue.offer(new Space(nr, nc, time + 1));
            }

        }
        return Integer.MAX_VALUE;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
