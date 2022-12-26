package BOJ.Greedy;

import java.io.*;

public class Main_BJ_2138_전구와스위치 {

    public static int N, count;
    public static int[][] bulbs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        bulbs = new int[3][N];
        for (int i = 0; i < 2; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                bulbs[i][j] = line.charAt(j) - '0';
            }
        }
        int result = 0;
        // 0번째 전구의 상태를 변경하는 경우
        count = 1;
        bulbs[2] = bulbs[0].clone();
        for (int i = 0; i <= 1; i++) {
            bulbs[2][i] = bulbs[2][i] ^ 1;
        }
        changeBulbStatus();
        if (isSame()) {
            result = count;
        }
        // 0번째 전구의 상태를 변경하지 않는 경우
        count = 0;
        bulbs[2] = bulbs[0].clone();
        changeBulbStatus();
        if (isSame()) {
            bw.write(Integer.toString(result == 0 ? count : Math.min(count, result)));
        } else {
            bw.write(Integer.toString(result == 0 ? -1 : result));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void changeBulbStatus() {
        for (int i = 1; i < N; i++) {
            if (bulbs[2][i - 1] != bulbs[1][i - 1]) {
                count++;
                pressSwitch(i);
            }
        }
    }

    private static void pressSwitch(int i) {
        bulbs[2][i - 1] ^= 1;
        bulbs[2][i] ^= 1;
        if (i + 1 < N) {
            bulbs[2][i + 1] ^= 1;
        }
    }

    private static boolean isSame() {
        for (int i = 0; i < N; i++) {
            if (bulbs[2][i] != bulbs[1][i]) {
                return false;
            }
        }
        return true;
    }
}
