package BOJ.Two_Pointer;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1981_배열에서이동 {

    public static final int INF = 200;

    public static int N;
    public static int[][] array;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = INF, min = 0, max = 0;
        while (min <= max && max <= INF) {
            if (canMove(min, max)) {
                answer = Math.min(answer, max - min);
                min++;
            } else {
                max++;
            }
        }
        bw.write(Integer.toString(answer));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean canMove(int min, int max) {
        if (!isValidate(array[0][0], min, max)) {
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][N];
        queue.add(new int[]{0, 0});
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            if (r == N - 1 && c == N - 1) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (isIn(nr, nc) && !isVisited[nr][nc] && isValidate(array[nr][nc], min, max)) {
                    queue.add(new int[]{nr, nc});
                    isVisited[nr][nc] = true;
                }
            }
        }
        return false;
    }

    private static boolean isValidate(int number, int min, int max) {
        return number >= min && number <= max;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}