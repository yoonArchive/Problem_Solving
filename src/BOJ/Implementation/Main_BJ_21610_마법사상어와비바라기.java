package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_21610_마법사상어와비바라기 {

    static int dr[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int dc[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int N;
    static int board[][];
    static boolean isCloudExisted[][];
    static Queue<Cloud> cloudQ;

    public static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloudQ = new LinkedList<>();
        // 비바라기 시전
        for (int i = N - 1; i <= N; i++) {
            for (int j = 1; j <= 2; j++) {
                cloudQ.offer(new Cloud(i, j));
            }
        }

        // 이동 명령의 수 만큼 절차 수행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int direction = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            isCloudExisted = new boolean[N + 1][N + 1];

            // step 1. 구름 이동
            moveCloud(direction, dist);

            // step 2. 구름에서 비가 내려 바구니 물 증가
            rain();

            // step 3, 4. 물 복사 버그 -> 물복사 하면서 3단계 구름 제거 같이
            waterCopy();

            // step 5. 구름 생성
            makeCloud();
        }

        bw.write(Integer.toString(getWaterSum()));
        br.close();
        bw.flush();
        bw.close();

    }

    private static void moveCloud(int direction, int dist) {
        int qSize = cloudQ.size();
        while (qSize-- > 0) {
            Cloud cloud = cloudQ.poll();
            int nr = (cloud.r + (dr[direction] * dist % N));
            int nc = (cloud.c + (dc[direction] * dist % N));

            nr = nr <= 0 ? (nr == 0 ? N : nr + N) : (nr > N) ? nr - N : nr;
            nc = nc <= 0 ? (nc == 0 ? N : nc + N) : (nc > N) ? nc - N : nc;

            cloudQ.offer(new Cloud(nr, nc));
        }
    }

    private static void rain() {
        int qSize = cloudQ.size();
        while (qSize-- > 0) {
            Cloud cloud = cloudQ.poll();
            int r = cloud.r;
            int c = cloud.c;
            board[r][c]++;
            isCloudExisted[r][c] = true;
            cloudQ.offer(new Cloud(r, c));
        }
    }

    private static void waterCopy() {
        while (!cloudQ.isEmpty()) {
            Cloud cloud = cloudQ.poll();
            int r = cloud.r;
            int c = cloud.c;
            int basket = 0;
            for (int d = 2; d <= 8; d += 2) { // 대각선 탐색
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isIn(nr, nc) && board[nr][nc] > 0)
                    basket++;
            }
            board[r][c] += basket; // 대각선 방향에 있는 바구니의 수만큼 물의 양 증가
        }
    }

    private static void makeCloud() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (isCloudExisted[r][c])
                    continue;
                if (board[r][c] >= 2) {
                    board[r][c] -= 2;
                    cloudQ.offer(new Cloud(r, c));
                }
            }
        }
    }

    private static int getWaterSum() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                sum += board[i][j];
        }
        return sum;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 1 && nc >= 1 && nr <= N && nc <= N;
    }

}
