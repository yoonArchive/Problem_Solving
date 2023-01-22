package BOJ.Implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_19238_스타트택시 {

    public static int N, M, fuel, taxiR, taxiC, currentPassenger;
    public static boolean[][] isWall;
    public static Passenger[][] passengers;
    public static PriorityQueue<Passenger> pq;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static class Passenger implements Comparable<Passenger> {
        int r;
        int c;
        int number;
        int distance;
        boolean canMove;

        public Passenger(int r, int c, int number, int distance) {
            this.r = r;
            this.c = c;
            this.number = number;
            this.distance = distance;
            this.canMove = true;
        }

        public int compareTo(Passenger p) {
            if (this.distance == p.distance) {
                if (this.r == p.r) {
                    return this.c - p.c;
                }
                return this.r - p.r;
            }
            return this.distance - p.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        isWall = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    isWall[i][j] = true;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;
        passengers = new Passenger[M + 1][2];
        pq = new PriorityQueue<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            passengers[i][0] = new Passenger(r, c, i, getDistance(r, c, i, true));
            if (passengers[i][0].distance == Integer.MAX_VALUE) {
                passengers[i][0].canMove = false;
            } else {
                pq.offer(passengers[i][0]);
            }
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            passengers[i][1] = new Passenger(r, c, i, getDistance(r, c, i, false));
        }
        int count = 0;
        boolean isMovedAll = false;
        while (true) {
            currentPassenger = pickUp();
            if (currentPassenger == -1) { // 승객 출발지에 도달할 수 없는 경우
                break;
            }
            move();
            if (fuel < 0) { // 이동하며 연료가 모두 소진된 경우
                break;
            }
            count++;
            charge();
            if (count == M) {
                isMovedAll = true;
                break;
            }
            updateDistanceFromTaxi();
        }
        if (!isMovedAll) {
            fuel = -1;
        }
        bw.write(Integer.toString(fuel));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int getDistance(int goalR, int goalC, int passenger, boolean isDistanceBetweenTaxiAndPassenger) {
        int sr, sc;
        if (isDistanceBetweenTaxiAndPassenger) { // 택시와 승객 사이 거리
            sr = taxiR;
            sc = taxiC;
        } else { // 출발지와 목적지 사이 거리
            sr = passengers[passenger][0].r;
            sc = passengers[passenger][0].c;
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][N];
        queue.offer(new int[]{sr, sc, 0});
        isVisited[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] moveInfo = queue.poll();
            int r = moveInfo[0];
            int c = moveInfo[1];
            int distance = moveInfo[2];
            if (r == goalR && c == goalC) {
                return distance;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || isVisited[nr][nc] || isWall[nr][nc]) {
                    continue;
                }
                queue.offer(new int[]{nr, nc, distance + 1});
                isVisited[nr][nc] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int pickUp() {
        return pq.isEmpty() ? -1 : pq.poll().number;
    }

    private static void move() {
        fuel -= (passengers[currentPassenger][0].distance + passengers[currentPassenger][1].distance);
        passengers[currentPassenger][0].canMove = false;
        taxiR = passengers[currentPassenger][1].r;
        taxiC = passengers[currentPassenger][1].c;
    }

    private static void charge() {
        fuel += passengers[currentPassenger][1].distance * 2;
    }

    private static void updateDistanceFromTaxi() {
        pq.clear();
        for (int i = 1; i <= M; i++) {
            if (passengers[i][0].canMove) {
                passengers[i][0].distance = getDistance(passengers[i][0].r, passengers[i][0].c, i, true);
                pq.offer(passengers[i][0]);
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
