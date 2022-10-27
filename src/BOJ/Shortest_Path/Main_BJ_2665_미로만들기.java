package BOJ.Shortest_Path;

import java.io.*;
import java.util.PriorityQueue;

public class Main_BJ_2665_미로만들기 {

    public static final int BLACK = 0;

    public static int N;
    public static int[][] rooms;
    public static int[] dr = {-1, 0, 0, 1};
    public static int[] dc = {0, 1, -1, 0};

    public static class Room implements Comparable<Room> {
        int r;
        int c;
        int switchCount;

        public Room(int r, int c, int switchCount) {
            this.r = r;
            this.c = c;
            this.switchCount = switchCount;
        }

        public int compareTo(Room o) {
            return this.switchCount - o.switchCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        rooms = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                rooms[i][j] = line.charAt(j) - '0';
            }
        }
        bw.write(Integer.toString(move()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        PriorityQueue<Room> pq = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[N][N];
        pq.offer(new Room(0, 0, 0));
        isVisited[0][0] = true;
        while (!pq.isEmpty()) {
            Room room = pq.poll();
            int r = room.r;
            int c = room.c;
            int switchCount = room.switchCount;
            if (r == N - 1 && c == N - 1) {
                return switchCount;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc) || isVisited[nr][nc]) {
                    continue;
                }
                if (rooms[nr][nc] == BLACK) {
                    pq.offer(new Room(nr, nc, switchCount + 1));
                } else {
                    pq.offer(new Room(nr, nc, switchCount));
                }
                isVisited[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
