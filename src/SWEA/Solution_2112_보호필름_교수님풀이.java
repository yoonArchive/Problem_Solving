package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_교수님풀이 {
    static final int A = 0, B = 1;
    static int D, W, K;
    static int[][] film;
    static int min;
    static int[] drugA, drugB; // A or B 약품으로 채워진 막

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
            drugA = new int[W];
            drugB = new int[W];
            min = Integer.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Arrays.fill(drugA, A); // int[] 기본 초기화가 0이므로 생략 가능 부분! 그래도 명시적으로 해두자~
            Arrays.fill(drugB, B);

            process(0, 0);

            sb.append("#" + tc + " " + min).append("\n");
        }
        System.out.println(sb.toString());

    }

    /**
     * 각 막에 부분집합으로 약품 비투여, 약품 A 투여, 약품 B 투여
     *
     * @param row    약품을 투여할 막
     * @param useCnt 그때까지의 약품 수
     * @return
     */
    private static boolean process(int row, int useCnt) {

        if (row == D) { // 기저조건
            if (check()) { // 성능 검사에 통과했다면
                min = Math.min(min, useCnt);
                return min == 0; // 약품을 하나도 사용하지 않았으면 true, 사용했으면 false
            }
            return false; // 성능 검사에 통과하지 않았다면 무조건 false
        }

        if (useCnt >= min) // 기존 임시 최적해의 최소 약품수보다 현재까지 사용한 약품수가 같거나 크면 의미 없으므로 리턴
            return false;

        int[] backup = film[row]; // 현재 막의 상태배열 기억

        // 약품 비투여
        process(row + 1, useCnt);

        // 약품 A 투여
        film[row] = drugA;
        process(row + 1, useCnt + 1);

        // 약품 B 투여
        film[row] = drugB;
        process(row + 1, useCnt + 1);

        film[row] = backup; // 기존 막의 상태로 다시 되돌리기
        return false;

    }

    private static boolean check() {// 성능 검사
        // 열 고정 행 탐색하며 연속된 셀의 같은 특성이 K개 이상인지 검사
        for (int c = 0; c < W; c++) {
            int count = 1;
            int before = film[0][c]; // 수직의 첫번째
            for (int r = 1; r < D; r++) {
                int current = film[r][c];
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

}
