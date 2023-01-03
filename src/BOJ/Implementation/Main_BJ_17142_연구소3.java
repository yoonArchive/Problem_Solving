package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17142_연구소3 {
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, blankCnt, time, ans;
    static int[][] map;
    static int[] selected;
    static Queue<int[]> q = new LinkedList<>();
    static ArrayList<int[]> candidates = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        selected = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == BLANK) {
                    blankCnt++;
                } else if (map[i][j] == VIRUS) {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        ans = Integer.MAX_VALUE;
        if (blankCnt == 0)
            ans = 0;
        else {
            selectM(0, 0);
            if (ans == Integer.MAX_VALUE)
                ans = -1;
        }

        bw.write(Integer.toString(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void selectM(int start, int cnt) {
        if (cnt == M) {
            boolean visited[][] = new boolean[N][N];
            for (int i = 0, length = selected.length; i < length; i++) {
                int[] cur = candidates.get(selected[i]);
                visited[cur[0]][cur[1]] = true;
                q.offer(new int[]{cur[0], cur[1]});
            }
            if (spread(visited)) // 모든 빈칸에 바이러스를 퍼뜨렸다면
                ans = Math.min(ans, time); // 최소 시간 갱신
            return;
        }

        for (int i = start, size = candidates.size(); i < size; i++) {
            selected[cnt] = i;
            selectM(i + 1, cnt + 1);
        }
    }

    private static boolean spread(boolean visited[][]) {
        int cnt = 0;
        time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) { // depth별 탐색
                int[] pair = q.poll();
                int r = pair[0];
                int c = pair[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != WALL) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        if (map[nr][nc] == BLANK)
                            cnt++;
                    }
                }
                if (cnt == blankCnt) {
                    time++;
                    q.clear();
                    return true;
                }
            }
            time++;
        }
        return false;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}