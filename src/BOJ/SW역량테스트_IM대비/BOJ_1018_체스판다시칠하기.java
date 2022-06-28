package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1018_체스판다시칠하기 {
    static char[][] map;
    static int N, M;
    static char right_arr1[][];
    static char right_arr2[][];

    public static int boardSearch(int r, int c) { // 시작 좌표 (r,c), 다시 칠해야 하는 칸의 개수를 반환
        int min_count = 32;// 정답의 최대값으로 초기화
        int count1 = 0;
        int count2 = 0;

        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c + 8; j++) {
                if (map[i][j] != right_arr1[i - r][j - c]) { //[r][c]~[r+7][c+7]   [0][0]~[7][7]
                    count1++;
                }
                if (map[i][j] != right_arr2[i - r][j - c]) {
                    count2++;
                }
                if (i == r + 7 && j == c + 7) {
                    min_count = Math.min(count1, count2);
                    count1 = 0;
                    count2 = 0;
                }
            }
        }
        return min_count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 정답배열 만들기
        String s1 = "BWBWBWBW";
        String s2 = "WBWBWBWB";
        right_arr1 = new char[8][8];
        right_arr2 = new char[8][8];
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                right_arr1[i] = s1.toCharArray();
                right_arr2[i] = s2.toCharArray();
            } else {
                right_arr1[i] = s2.toCharArray();
                right_arr2[i] = s1.toCharArray();
            }
        }

        int result = 32; // 정답의 최대값으로 초기화(8*8/2)
        for (int i = 0; i <= N - 8; i++) { //범위 넘어가지 않게 -8
            for (int j = 0; j <= M - 8; j++) {
                result = Math.min(result, boardSearch(i, j));
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

}
