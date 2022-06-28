package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_15685_드래곤커브 {
    static final int SIZE = 101;
    static int N;
    static boolean grid[][];
    static int[][] deltas = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 우 상 좌 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        grid = new boolean[SIZE][SIZE];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            getCurveDir(x, y, d, g);
        }

        bw.write(Integer.toString(searchVertex()));
        br.close();
        bw.flush();
        bw.close();
    }

    // step 01. 선분을 그리기 위해 모든 선분의 방향을 저장한다.
    private static void getCurveDir(int x, int y, int d, int g) {
        int dirLen = (int) Math.pow(2, g); // 커브 방향의 총 개수
        int direction[] = new int[dirLen + 1];
        boolean getDirection[] = new boolean[dirLen + 1];
        int iter = dirLen / 2;

        direction[1] = d;
        getDirection[1] = true;

        for (int i = 1; i <= iter; i++) {
            int dir = (direction[i] + 1) % 4; // direction[i]를 90도 회전한 방향
            for (int j = g; j > 0; j--) { //g:세대 1
                int addedLine = (int) Math.pow(2, j); // ..., 8, 4, 2
                int idx = addedLine - i + 1;
                if (idx > 0 && !getDirection[idx]) {
                    direction[idx] = dir;
                    getDirection[idx] = true;
                }
            }
        }
        drawCurve(direction, x, y);
    }

    // step 02. direction 배열을 따라 선분을 그린다
    private static void drawCurve(int[] direction, int x, int y) {
        grid[x][y] = true;
        for (int i = 1, length = direction.length; i < length; i++) {
            int dir = direction[i];
            int nx = x + deltas[dir][1];
            int ny = y + deltas[dir][0];
            if (!isIn(nx, ny))
                continue;
            grid[nx][ny] = true;
            x = nx;
            y = ny;
        }
    }

    // step 03. 네 꼭지점이 모두 드래곤 커브의 일부인 정사각형 개수를 구한다.
    private static int searchVertex() {
        int res = 0;
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (grid[i][j] && grid[i + 1][j] && grid[i][j + 1] && grid[i + 1][j + 1]) // 현재 칸, 우, 하, 우하
                    res++;
            }
        }
        return res;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < SIZE && ny < SIZE;
    }
}
