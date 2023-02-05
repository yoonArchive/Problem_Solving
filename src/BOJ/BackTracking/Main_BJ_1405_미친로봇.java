package BOJ.BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1405_미친로봇 {

    public static final int LIMIT = 14;

    public static int N;
    public static double answer;
    public static double[] probabilityOfMoving; // 동 서 남 북
    public static boolean[][] isVisited;
    public static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        probabilityOfMoving = new double[4];
        isVisited = new boolean[LIMIT * 2 + 1][LIMIT * 2 + 1];
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            probabilityOfMoving[i] = Double.parseDouble(st.nextToken()) / 100;
        }
        isVisited[LIMIT][LIMIT] = true;
        move(0, 1, 0, 0);
        bw.write(Double.toString(answer));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void move(int count, double probability, int r, int c) {
        if (count == N) {
            answer += probability;
            return;
        }
        for (int d = 0; d < 4; d++) {
            if (probabilityOfMoving[d] == 0) {
                continue;
            }
            int nr = r + deltas[d][0] + LIMIT;
            int nc = c + deltas[d][1] + LIMIT;
            if (!isVisited[nr][nc]) {
                isVisited[nr][nc] = true;
                move(count + 1, probability * probabilityOfMoving[d], nr - LIMIT, nc - LIMIT);
                isVisited[nr][nc] = false;
            }
        }
    }
}
