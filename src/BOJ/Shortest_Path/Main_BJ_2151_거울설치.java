package BOJ.Shortest_Path;

import java.io.*;
import java.util.PriorityQueue;

public class Main_BJ_2151_거울설치 {

    public static final char DOOR = '#';
    public static final char EMPTY = '.';
    public static final char WALL = '*';
    public static final char INSTALLABLE = '!';
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public static int N;
    public static char[][] home;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static class Space implements Comparable<Space> {
        int r;
        int c;
        int count;
        int direction;
        int mirrorDirection; // /:-1, 반대 : 1, 거울 없음 :0

        public Space(int r, int c, int count, int direction, int mirrorDirection) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.direction = direction;
            this.mirrorDirection = mirrorDirection;
        }

        public int compareTo(Space s) {
            return this.count - s.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        home = new char[N][N];
        PriorityQueue<Space> pq = new PriorityQueue<Space>();
        boolean[][][] isVisited = new boolean[N][N][4];
        boolean findStartDoor = false;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                home[i][j] = c;
                if (c == DOOR && !findStartDoor) {
                    findStartDoor = true;
                    for (int d = 0; d < 4; d++) {
                        pq.offer(new Space(i, j, 0, d, 0));
                        isVisited[i][j][d] = true;
                    }
                    home[i][j] = WALL;
                }
            }
        }
        bw.write(Integer.toString(installMirrors(pq, isVisited)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int installMirrors(PriorityQueue<Space> pq, boolean[][][] isVisited) {
        int answer = 0;
        while (!pq.isEmpty()) {
            Space space = pq.poll();
            int r = space.r;
            int c = space.c;
            int count = space.count;
            int direction = space.direction;
            int mirrorDirection = space.mirrorDirection;
            char current = home[r][c];
            if (current == DOOR) {
                answer = count;
                break;
            } else if (current == INSTALLABLE) {
                int nDirection;
                if (mirrorDirection == 1) { // 거울이 / 방향으로 설치된 경우
                    if (direction == NORTH) {
                        nDirection = EAST;
                    } else if (direction == EAST) {
                        nDirection = NORTH;
                    } else if (direction == SOUTH) {
                        nDirection = WEST;
                    } else {
                        nDirection = SOUTH;
                    }
                } else if (mirrorDirection == -1) { // 거울이 \ 방향으로 설치된 경우
                    if (direction == NORTH) {
                        nDirection = WEST;
                    } else if (direction == WEST) {
                        nDirection = NORTH;
                    } else if (direction == SOUTH) {
                        nDirection = EAST;
                    } else {
                        nDirection = SOUTH;
                    }
                } else {
                    nDirection = direction;
                }
                int nr = r + deltas[nDirection][0];
                int nc = c + deltas[nDirection][1];
                if (!isIn(nr, nc) || isVisited[nr][nc][nDirection] || home[nr][nc] == WALL) {
                    continue;
                }
                if (home[nr][nc] == INSTALLABLE) {
                    pq.offer(new Space(nr, nc, count + 1, nDirection, 1));
                    pq.offer(new Space(nr, nc, count + 1, nDirection, -1));
                    pq.offer(new Space(nr, nc, count, nDirection, 0));
                } else {
                    pq.offer(new Space(nr, nc, count, nDirection, 0));
                }
                isVisited[nr][nc][nDirection] = true;
            } else { // 현재 칸이 empty
                int nr = r + deltas[direction][0];
                int nc = c + deltas[direction][1];
                if (!isIn(nr, nc) || isVisited[nr][nc][direction] || home[nr][nc] == WALL) {
                    continue;
                }
                if (home[nr][nc] == INSTALLABLE) {
                    pq.offer(new Space(nr, nc, count + 1, direction, 1));
                    pq.offer(new Space(nr, nc, count + 1, direction, -1));
                    pq.offer(new Space(nr, nc, count, direction, 0));
                } else {
                    pq.offer(new Space(nr, nc, count, direction, 0));
                }
                isVisited[nr][nc][direction] = true;
            }
        }
        return answer;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
