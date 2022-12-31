package BOJ.Graph;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_17090_미로탈출하기 {

    public static final boolean POSSIBLE = true;

    public static int N, M;
    public static char[][] map;
    public static boolean[][] canEscape, isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        canEscape = new boolean[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (escape(i, j)) {
                    count++;
                }
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean escape(int r, int c) {
        if (!isIn(r, c) || canEscape[r][c]) {
            return POSSIBLE;
        }
        if (isVisited[r][c]) {
            return canEscape[r][c];
        }
        isVisited[r][c] = true;
        char currentChar = map[r][c];
        boolean currentEscapeStatus;
        int nr = r, nc = c;
        if (currentChar == 'U') {
            nr--;
        } else if (currentChar == 'R') {
            nc++;
        } else if (currentChar == 'D') {
            nr++;
        } else {
            nc--;
        }
        currentEscapeStatus = escape(nr, nc);
        isVisited[r][c] = false;
        return canEscape[r][c] = currentEscapeStatus;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
