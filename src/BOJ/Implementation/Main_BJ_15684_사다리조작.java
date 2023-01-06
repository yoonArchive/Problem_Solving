package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15684_사다리조작 {

    public static int N, M, H;
    public static boolean[][] isConnected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        isConnected = new boolean[H + 1][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            isConnected[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        int result = -1;
        if (canChangeResult()) {
            result = 0;
        } else {
            for (int i = 1; i <= 3; i++) {
                addLine(i, 0, N + 1);
            }
        }
        System.out.println(result);
        br.close();
    }

    private static void addLine(int limit, int count, int index) {
        if (count == limit) {
            if (canChangeResult()) {
                System.out.println(count);
                System.exit(0);
            }
            return;
        }
        if (index >= N * (H + 1)) {
            return;
        }
        int r = index / N;
        int c = index % N;
        if (c == 0 || isConnected[r][c] || isConnected[r][c - 1] || (c + 1 < N && isConnected[r][c + 1])) {
            addLine(limit, count, index + 1);
        } else {
            isConnected[r][c] = true;
            addLine(limit, count + 1, index + 1);
            isConnected[r][c] = false;
            addLine(limit, count, index + 1);
        }
    }

    private static boolean canChangeResult() {
        for (int i = 1; i <= N; i++) {
            if (!canReachSameNumber(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canReachSameNumber(int startNumber) {
        int number = startNumber;
        for (int r = 1; r <= H; r++) {
            if (number - 1 >= 1 && isConnected[r][number - 1]) {
                number--;
            } else if (number < N && isConnected[r][number]) {
                number++;
            }
        }
        return number == startNumber ? true : false;
    }
}
