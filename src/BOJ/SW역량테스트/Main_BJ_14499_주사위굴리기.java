package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14499_주사위굴리기 {
    static final int[][] DELTAS = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static final int DICEFACES = 6;

    static final int TOP = 0;
    static final int RIGHT = 1;
    static final int FRONT = 2;
    static final int BOTTOM = 3;
    static final int LEFT = 4;
    static final int BACK = 5;

    static int map[][];
    static int diceNum[];
    static int N, M, r, c;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        diceNum = new int[DICEFACES];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            moveDice(Integer.parseInt(st.nextToken()));
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static void moveDice(int dir) {
        int originR = r, originC = c;
        r = r + DELTAS[dir][0];
        c = c + DELTAS[dir][1];
        if (!isIn(r, c)) {
            r = originR;
            c = originC;
            return;
        }

        // 주사위 굴리기
        rollDice(dir);

        // 이동한 칸에 쓰여있는 수가 0이면 주사위의 바닥면에 쓰여 있는 수를 칸에 복사
        if (map[r][c] == 0) {
            map[r][c] = diceNum[BOTTOM];
        } else { // 이동한 칸에 쓰여있는 수가 0이 아니면 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            diceNum[BOTTOM] = map[r][c];
            map[r][c] = 0;
        }
        sb.append(diceNum[TOP]).append("\n");
    }

    public static void rollDice(int dir) {
        int idx, tmp, next;

        switch (dir) {
            case 1: // 동
                idx = LEFT;
                tmp = diceNum[idx];
                while (idx > 0) {
                    next = idx - 1;
                    if (isFrontOrBack(next)) {
                        next--;
                        diceNum[idx] = diceNum[next];
                        idx -= 2;
                        continue;
                    }
                    diceNum[idx] = diceNum[next];
                    idx--;
                }
                diceNum[TOP] = tmp;
                break;

            case 2: // 서
                idx = TOP;
                tmp = diceNum[idx];
                while (idx < LEFT) {
                    next = idx + 1;
                    if (isFrontOrBack(next)) {
                        next++;
                        diceNum[idx] = diceNum[next];
                        idx += 2;
                        continue;
                    }
                    diceNum[idx] = diceNum[next];
                    idx++;
                }
                diceNum[LEFT] = tmp;
                break;

            case 3: // 북
                idx = TOP;
                tmp = diceNum[idx];
                while (idx < DICEFACES - 1) {
                    next = idx + 1;
                    if (isRightOrLeft(next)) {
                        next++;
                        diceNum[idx] = diceNum[next];
                        idx += 2;
                        continue;
                    }
                    diceNum[idx] = diceNum[next];
                    idx++;
                }
                diceNum[BACK] = tmp;
                break;

            case 4: // 남
                idx = BACK;
                tmp = diceNum[idx];
                while (idx > 0) {
                    next = idx - 1;
                    if (isRightOrLeft(next)) {
                        next--;
                        diceNum[idx] = diceNum[next];
                        idx -= 2;
                        continue;
                    }
                    diceNum[idx] = diceNum[next];
                    idx--;
                }
                diceNum[TOP] = tmp;
                break;
        }
    }

    public static boolean isFrontOrBack(int num) {
        if (num == FRONT || num == BACK)
            return true;
        return false;
    }

    public static boolean isRightOrLeft(int num) {
        if (num == RIGHT || num == LEFT)
            return true;
        return false;
    }

    public static boolean isIn(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= M)
            return false;
        return true;
    }

}
