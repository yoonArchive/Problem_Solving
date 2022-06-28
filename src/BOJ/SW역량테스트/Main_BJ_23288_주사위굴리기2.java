package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_23288_주사위굴리기2 {
    static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 -> 동 -> 남-> 서 (시계방향 순)
    static int Top = 1;
    static int Back = 2;
    static int Right = 3;
    static int Left = 4;
    static int Front = 5;
    static int Bottom = 6;
    static int N, M, r, c, direction;
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 처음 주사위의 이동 방향은 동쪽
        direction = 1;

        // 처음 주사위의 위치는 (1,1)
        r = c = 1;

        int score = 0;
        while (K-- > 0) { // 이동 횟수만큼 아래 과정 반복

            // step 01. 주사위가 이동 방향으로 한 칸 굴러간다.
            rollDice();

            // step 02. 주사위가 도착한 칸 (x,y)에 대한 점수를 획득한다.
            score += getScore();

            // step 03. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x,y)에 있는 정수를 비교하여 이동 방향을 결정한다.
            if (Bottom > map[r][c]) // 시계방향 90도
                direction = (direction + 1) % 4;
            else if (Bottom < map[r][c]) // 반시계방향 90도
                direction = (direction + 4 - 1) % 4;
        }

        bw.write(Integer.toString(score));
        br.close();
        bw.flush();
        bw.close();

    }

    private static void rollDice() {
        // 주사위 위치 이동
        r += DELTAS[direction][0];
        c += DELTAS[direction][1];

        // 이동 방향에 칸이 없다면
        if (!isIn(r, c)) {
            // 방향을 반대로 전환
            direction = (direction + 2) % 4;
            // 주사위 위치도 방향에 맞게 갱신
            r = r + (DELTAS[direction][0] * 2);
            c = c + (DELTAS[direction][1] * 2);
        }

        // 이동 방향에 맞게 전개도 update
        update();
    }

    private static void update() {
        int tmp = Top;
        if (direction == 0) { // 북
            Top = Front;
            Front = Bottom;
            Bottom = Back;
            Back = tmp;
        } else if (direction == 1) { // 동
            Top = Left;
            Left = Bottom;
            Bottom = Right;
            Right = tmp;
        } else if (direction == 2) { // 남
            Top = Back;
            Back = Bottom;
            Bottom = Front;
            Front = tmp;
        } else { // 서
            Top = Right;
            Right = Bottom;
            Bottom = Left;
            Left = tmp;
        }

    }

    private static int getScore() {
        int num = map[r][c];
        int count = 0;

        Queue<int[]> q = new LinkedList<>();
        boolean isVisited[][] = new boolean[N + 1][M + 1];
        q.offer(new int[]{r, c});
        isVisited[r][c] = true;
        count++;

        // 해당 칸에서 BFS 탐색을 통해 점수를 구한다.
        while (!q.isEmpty()) {
            int pair[] = q.poll();
            int cr = pair[0];
            int cc = pair[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + DELTAS[d][0];
                int nc = cc + DELTAS[d][1];
                if (isIn(nr, nc) && !isVisited[nr][nc] && map[nr][nc] == num) {
                    q.offer(new int[]{nr, nc});
                    isVisited[nr][nc] = true;
                    count++;
                }
            }
        }

        return num * count;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 1 && nc >= 1 && nr <= N && nc <= M;
    }

}
