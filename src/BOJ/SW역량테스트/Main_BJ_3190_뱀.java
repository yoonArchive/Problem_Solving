package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3190_뱀 {
    final static String RIGHT = "D";
    final static String LEFT = "L";
    final static int APPLE = 1;
    final static int SNAKE = -1;
    final static int BLANK = 0;
    final static int DELTAS[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, second;
    static boolean hasApple[][];
    static boolean isSnakeIn[][];

    public static class Pair {
        int r, c;

        public Pair(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

    }

    public static class DirInfo {
        int sec;
        String dir;

        public DirInfo(int sec, String dir) {
            super();
            this.sec = sec;
            this.dir = dir;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        hasApple = new boolean[N + 1][N + 1];
        isSnakeIn = new boolean[N + 1][N + 1];
        StringTokenizer st = null;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            hasApple[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<DirInfo> dirQ = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            dirQ.offer(new DirInfo(sec, dir));
        }
        dirQ.offer(new DirInfo(Integer.MAX_VALUE, RIGHT));

        moveSnake(dirQ);
        bw.write(Integer.toString(second));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void moveSnake(Queue<DirInfo> dirQ) {
        String dir = RIGHT;
        Queue<Pair> snakeQ = new LinkedList<>();

        int nr = 1, nc = 1;
        snakeQ.offer(new Pair(nr, nc));
        isSnakeIn[nr][nc] = true;

        int dirIdx = 0;
        DirInfo info = dirQ.poll();
        int changeSec = info.sec;
        String changeDir = info.dir;

        while (true) {
            // step 01. 초를 증가시키고 뱀 이동
            ++second;
            nr += DELTAS[dirIdx][0];
            nc += DELTAS[dirIdx][1];

            // step 02. 벽 또는 자신의 몸과 부딪히는지 검사
            if (!isIn(nr, nc) || isBumped(nr, nc))
                break;

            // step 03. 뱀 이동 규칙
            snakeQ.offer(new Pair(nr, nc)); // 머리를 다음칸에 위치시킨다.
            isSnakeIn[nr][nc] = true;

            if (eatApple(nr, nc)) {
                hasApple[nr][nc] = false; // 사과가 없어진다.
            } else {
                Pair p = snakeQ.poll(); // 몸 길이를 줄여 꼬리가 위치한 칸을 비워준다.
                isSnakeIn[p.r][p.c] = false;
            }

            // step 04. 방향 전환이 필요한지 확인
            if (second == changeSec) {
                dirIdx = changeDelta(changeDir, dirIdx);
                dir = changeDir;
                info = dirQ.poll();
                changeSec = info.sec;
                changeDir = info.dir;
            }
        }
    }

    private static boolean eatApple(int r, int c) {
        if (hasApple[r][c])
            return true;
        return false;
    }

    private static int changeDelta(String next, int idx) {
        if (next.equals(RIGHT))
            return (idx + 1) % 4;
        else
            return (idx + 3) % 4;
    }

    private static boolean isIn(int r, int c) {
        if (r < 1 || r > N || c < 1 || c > N)
            return false;
        return true;
    }

    private static boolean isBumped(int r, int c) {
        if (isSnakeIn[r][c])
            return true;
        return false;
    }

}
