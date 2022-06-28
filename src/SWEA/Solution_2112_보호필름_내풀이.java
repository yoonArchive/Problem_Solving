package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_내풀이 {
    static int D, W, K;
    static int[][] film;
    static int minCnt;
    static int selected[];
    static boolean canStop;
    static int res;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("2112.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            canStop = false;
            minCnt = Integer.MAX_VALUE;
            selected = new int[D];
            for (int r = 0; r <= D; r++) // D개 중에 r개 선택
                dCr(0, 0, r);
            sb.append("#" + tc + " " + res).append("\n");
        }
        System.out.println(sb.toString());
        br.close();

    }

    // d개의 막 중 약을 투입할 r개를 선택한다.
    private static void dCr(int count, int start, int r) {
        if (count == r) {
            int[][] copyFilm = copy();
            insert(0, r, copyFilm);
            return;
        }
        for (int i = start; i < D; i++) {
            selected[count] = i;
            dCr(count + 1, i + 1, r);
            selected[count] = i;
        }
    }

    private static void insert(int count, int r, int[][] copyFilm) {
        if (canStop)
            return;
        if (count == r) {
            if (check(copyFilm)) { // 성능 검사
                if (r == 0)
                    res = 0;
                else
                    res = r;
                canStop = true;
            }
            return;
        }
        int floor = selected[count];

        // 해당 막을 A(0)로 바꾸기
        for (int i = 0; i < W; i++)
            copyFilm[floor][i] = 0;
        insert(count + 1, r, copyFilm);

        // 해당 막을 B로 바꾸기
        for (int i = 0; i < W; i++)
            copyFilm[floor][i] = 1;
        insert(count + 1, r, copyFilm);

    }

    private static boolean check(int[][] copyFilm) {
        // 열 고정 행 탐색하며 연속된 셀의 같은 특성이 K개 이상인지 검사
        for (int c = 0; c < W; c++) {
            int count = 1;
            int before = copyFilm[0][c]; // 수직의 첫번째
            for (int r = 1; r < D; r++) {
                int current = copyFilm[r][c];
                if (before == current) {
                    count++;
                    if (count == K)
                        break;
                } else {
                    before = current;
                    count = 1;
                }
            } // 하나의 열을 고정해서 수직 검사
            if (count < K)
                return false;
        }
        return true;
    }

    private static int[][] copy() {
        int[][] copyFilm = new int[D][W];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                copyFilm[i][j] = film[i][j];
            }
        }
        return copyFilm;
    }

}
