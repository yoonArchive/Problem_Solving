package BOJ.SW역량테스트;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16236_아기상어 {

    public static final int EMPTY = 0;
    public static final int CAUGHT = -1;

    public static int N, time, eatCount, sharkR, sharkC, sharkSize;
    public static int[][] map;
    public static PriorityQueue<Fish> fishQueue;
    public static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static class Fish implements Comparable<Fish> {
        int r;
        int c;
        int size;
        int distanceFromShark;

        public Fish(int r, int c, int size, int distanceFromShark) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.distanceFromShark = distanceFromShark;
        }

        public int compareTo(Fish o) {
            if (this.distanceFromShark == o.distanceFromShark) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.distanceFromShark - o.distanceFromShark;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fishQueue = new PriorityQueue<>();
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
        while (true) {
            getEdibleFish();
            if (fishQueue.size() == 0) {
                break;
            }
            eatFish();
            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }
        bw.write(Integer.toString(time));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void eatFish() {
        Fish fish = fishQueue.poll();
        int r = fish.r;
        int c = fish.c;
        int distanceFromShark = fish.distanceFromShark;
        map[r][c] = CAUGHT;
        time += distanceFromShark;
        eatCount++;
        sharkR = r;
        sharkC = c;
    }

    private static void getEdibleFish() {
        int current;
        fishQueue.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                current = map[i][j];
                if (current == EMPTY || current == CAUGHT) {
                    continue;
                }
                if (current < sharkSize) {
                    int distance = getDistanceFromShark(i, j);
                    if (distance != Integer.MAX_VALUE) {
                        fishQueue.offer(new Fish(i, j, current, distance));
                    }
                }
            }
        }
    }

    private static int getDistanceFromShark(int fishR, int fishC) {
        Queue<int[]> queue = new LinkedList<>();
        boolean isVisited[][] = new boolean[N][N];
        queue.offer(new int[]{sharkR, sharkC, 0});
        isVisited[sharkR][sharkC] = true;
        int result = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0];
            int c = node[1];
            int distance = node[2];
            if (r == fishR && c == fishC) {
                result = Math.min(result, distance);
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!inIn(nr, nc) || isVisited[nr][nc] || map[nr][nc] > sharkSize) {
                    continue;
                }
                queue.offer(new int[]{nr, nc, distance + 1});
                isVisited[nr][nc] = true;
            }
        }
        return result;
    }

    private static boolean inIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
