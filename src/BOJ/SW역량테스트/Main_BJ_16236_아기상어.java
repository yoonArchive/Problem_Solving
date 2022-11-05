package BOJ.SW역량테스트;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_16236_아기상어 {

    public static final int EMPTY = 0;

    public static int N, time, sharkR, sharkC, eatCount, sharkSize;
    public static int[][] map;
    public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static class Shark implements Comparable<Shark> {
        int r;
        int c;
        int distance;

        public Shark(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        public int compareTo(Shark o) {
            if (this.distance == o.distance) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sharkSize = 2;
        int current;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                current = Integer.parseInt(st.nextToken());
                map[i][j] = current;
                if (current == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = EMPTY;
                }
            }
        }
        findEdibleFish();
        bw.write(Integer.toString(time));
        br.close();
        bw.flush();
        bw.close();
    }


    private static void findEdibleFish() {
        PriorityQueue<Shark> sharkQueue = new PriorityQueue<>();
        while (true) {
            sharkQueue.offer(new Shark(sharkR, sharkC, 0));
            boolean[][] isVisited = new boolean[N][N];
            isVisited[sharkR][sharkC] = true;
            boolean findFlag = false;
            while (!sharkQueue.isEmpty()) {
                Shark shark = sharkQueue.poll();
                int r = shark.r;
                int c = shark.c;
                int distance = shark.distance;
                if (map[r][c] > 0 && map[r][c] < sharkSize) {
                    findFlag = true;
                    eatFish(r, c, distance);
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];
                    if (!isIn(nr, nc) || isVisited[nr][nc] || map[nr][nc] > sharkSize) {
                        continue;
                    }
                    sharkQueue.offer(new Shark(nr, nc, distance + 1));
                    isVisited[nr][nc] = true;
                }
            }
            if (!findFlag) {
                break;
            }
            sharkQueue.clear();
        }
    }

    private static void eatFish(int r, int c, int distance) {
        eatCount++;
        time += distance;
        map[r][c] = EMPTY;
        sharkR = r;
        sharkC = c;
        if (eatCount == sharkSize) {
            eatCount = 0;
            sharkSize++;
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
