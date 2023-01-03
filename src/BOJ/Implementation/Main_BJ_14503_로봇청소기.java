package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_14503_로봇청소기 {
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int CLEANED = -1;
    static int map[][];
    static int N, M;
    static int cleanedPart;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌

    public static class Cleaner {
        int r, c, d;

        public Cleaner(int r, int c, int d) {
            super();
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void work(Cleaner cleaner) {
        // step 1
        if (map[cleaner.r][cleaner.c] == BLANK) {
            cleanedPart++;
            map[cleaner.r][cleaner.c] = CLEANED;
        }
        int originD = cleaner.d;

        // step 2
        int nd = cleaner.d;
        for (int i = 0, length = delta.length; i < length; i++) {
            nd = nd == 0 ? 3 : nd - 1;
            int nr = cleaner.r + delta[nd][0];
            int nc = cleaner.c + delta[nd][1];
            if (map[nr][nc] == BLANK) { // case A
                cleaner.r = nr;
                cleaner.c = nc;
                cleaner.d = nd;
                work(cleaner);
                return;
            }
        }

        int backR = cleaner.r - delta[nd][0];
        int backC = cleaner.c - delta[nd][1];
        if (map[backR][backC] != WALL) { // case C
            cleaner.r = backR;
            cleaner.c = backC;
            cleaner.d = originD;
            work(cleaner);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int cleanerR = Integer.parseInt(st.nextToken());
        int cleanerC = Integer.parseInt(st.nextToken());
        int cleanerD = Integer.parseInt(st.nextToken());
        Cleaner cleaner = new Cleaner(cleanerR, cleanerC, cleanerD);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        work(cleaner);
        bw.write(Integer.toString(cleanedPart));
        br.close();
        bw.flush();
        bw.close();

    }

}
