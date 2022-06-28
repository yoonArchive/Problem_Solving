package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16234_인구이동 {
    static int population[][];
    static boolean isBorderOpened[][];
    static int N, L, R;
    static int dr[] = {1, 0, -1, 0};
    static int dc[] = {0, 1, 0, -1};
    static int movable, count, num;
    static ArrayList<Pair> pairList = new ArrayList<>();
    static ArrayList<MoveInfo> moveList = new ArrayList<>();

    public static class MoveInfo {
        int moveNum, areaCnt;

        public MoveInfo(int moveNum, int areaCnt) {
            super();
            this.moveNum = moveNum;
            this.areaCnt = areaCnt;
        }

    }

    public static class Pair {
        int r, c, num;

        public Pair(int r, int c, int num) {
            super();
            this.r = r;
            this.c = c;
            this.num = num;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        population = new int[N][N];
        isBorderOpened = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while (true) {
            boolean isOpened = false;
            num = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (canOpen(r, c, nr, nc)) {
                            openBorders(r, c, nr, nc);
                            isOpened = true;
                            num++;
                        }
                    }
                }
            }
            if (!isOpened) break;
            else move();

            days += 1;
            pairList.clear();
            moveList.clear();
            reset();
        }

        bw.write(Integer.toString(days));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void openBorders(int r, int c, int nr, int nc) {
        movable = 0;
        count = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c, num));
        q.offer(new Pair(nr, nc, num));
        pairList.add(new Pair(r, c, num));
        pairList.add(new Pair(nr, nc, num));
        isBorderOpened[r][c] = true;
        isBorderOpened[nr][nc] = true;
        movable += (population[r][c] + population[nr][nc]);
        count = 2;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            r = p.r;
            c = p.c;
            for (int d = 0; d < 4; d++) {
                nr = r + dr[d];
                nc = c + dc[d];
                if (canOpen(r, c, nr, nc)) {
                    q.offer(new Pair(nr, nc, num));
                    pairList.add(new Pair(nr, nc, num));
                    isBorderOpened[nr][nc] = true;
                    movable += population[nr][nc];
                    count++;
                }
            }
        }
        moveList.add(new MoveInfo(movable, count));
    }

    public static void move() {
        int i = 0;
        while (i < pairList.size()) {
            int num = pairList.get(i).num;
            int movableNum = moveList.get(num).moveNum;
            int areaCnt = moveList.get(num).areaCnt;
            int moveResult = movableNum / areaCnt;
            for (int j = 0; j < areaCnt; j++) {
                int r = pairList.get(i).r;
                int c = pairList.get(i).c;
                population[r][c] = moveResult;
                i++;
            }
        }
    }

    public static boolean canOpen(int r, int c, int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < N && !isBorderOpened[nr][nc]) {
            int curDiff = Math.abs(population[r][c] - population[nr][nc]);
            if (curDiff >= L && curDiff <= R)
                return true;
        }
        return false;
    }

    public static void reset() {
        for (int i = 0; i < N; i++)
            Arrays.fill(isBorderOpened[i], false);
    }

}
