package BOJ.Implementation;

import java.io.*;
import java.util.*;

public class Main_BJ_23289_온풍기안녕 {

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int INVESTIGATION = 4;

    public static int R, C, K, W, chocolates;
    public static int[][] temperature;
    public static boolean[][][] isWallExist;
    public static Queue<Heater> heaterQueue;
    public static List<int[]> investigationSpaces;
    public static int[][] deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public static int[][][] risingInfo = {{{-1, 1}, {0, 1}, {1, 1}}, {{-1, -1}, {0, -1}, {1, -1}}, {{-1, -1}, {-1, 0}, {-1, 1}}, {{1, -1}, {1, 0}, {1, 1}}};

    public static class Heater {
        int r;
        int c;
        int direction;

        public Heater(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        temperature = new int[R][C];
        isWallExist = new boolean[R][C][4];
        heaterQueue = new LinkedList<>();
        investigationSpaces = new ArrayList<>();
        int current;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                current = Integer.parseInt(st.nextToken()) - 1;
                if (current == INVESTIGATION) {
                    investigationSpaces.add(new int[]{i, j});
                } else if (current >= RIGHT) {
                    heaterQueue.offer(new Heater(i, j, current));
                }
            }
        }
        W = Integer.parseInt(br.readLine());
        int x, y, t;
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken());
            if (t == 0) {
                isWallExist[x][y][2] = true;
                isWallExist[x - 1][y][3] = true;
            } else {
                isWallExist[x][y][0] = true;
                isWallExist[x][y + 1][1] = true;
            }
        }
        while (true) {
            operateHeaters();
            controlTemperature();
            reduceOutsideTemperature();
            eatChocolate();
            if (chocolates > 100 || investigate()) {
                break;
            }
        }
        bw.write(Integer.toString(chocolates));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void operateHeaters() {
        int heaterCount = heaterQueue.size();
        while (heaterCount-- > 0) {
            Heater heater = heaterQueue.poll();
            int r = heater.r;
            int c = heater.c;
            int direction = heater.direction;
            blow(r, c, direction);
            heaterQueue.offer(heater);
        }
    }

    private static void blow(int heaterR, int heaterC, int direction) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[R][C];
        int[] riseInfo = {heaterR + deltas[direction][0], heaterC + deltas[direction][1], 5};
        queue.offer(riseInfo);
        while (!queue.isEmpty()) {
            riseInfo = queue.poll();
            int r = riseInfo[0];
            int c = riseInfo[1];
            int rising = riseInfo[2];
            temperature[r][c] += rising;
            if (rising == 0) {
                break;
            }
            for (int d = 0; d < 3; d++) {
                int nr = r + risingInfo[direction][d][0];
                int nc = c + risingInfo[direction][d][1];
                if (!isIn(nr, nc) || isVisited[nr][nc] || checkWall(r, c, direction, d)) {
                    continue;
                }
                queue.offer(new int[]{nr, nc, rising - 1});
                isVisited[nr][nc] = true;
            }
        }
    }

    private static boolean checkWall(int r, int c, int direction, int order) {
        if (direction == LEFT) {
            if (order == 0) {
                if (isWallExist[r][c][UP] || isWallExist[r - 1][c][LEFT]) {
                    return true;
                }
            } else if (order == 1) {
                if (isWallExist[r][c][LEFT]) {
                    return true;
                }
            } else {
                if (isWallExist[r][c][DOWN] || isWallExist[r + 1][c][LEFT]) {
                    return true;
                }
            }
        } else if (direction == RIGHT) {
            if (order == 0) {
                if (isWallExist[r][c][UP] || isWallExist[r - 1][c][RIGHT]) {
                    return true;
                }
            } else if (order == 1) {
                if (isWallExist[r][c][RIGHT]) {
                    return true;
                }
            } else {
                if (isWallExist[r][c][DOWN] || isWallExist[r + 1][c][RIGHT]) {
                    return true;
                }
            }
        } else if (direction == UP) {
            if (order == 0) {
                if (isWallExist[r][c][LEFT] || isWallExist[r][c - 1][UP]) {
                    return true;
                }
            } else if (order == 1) {
                if (isWallExist[r][c][UP]) {
                    return true;
                }
            } else {
                if (isWallExist[r][c][RIGHT] || isWallExist[r][c + 1][UP]) {
                    return true;
                }
            }
        } else if (direction == DOWN) {
            if (order == 0) {
                if (isWallExist[r][c][LEFT] || isWallExist[r][c - 1][DOWN]) {
                    return true;
                }
            } else if (order == 1) {
                if (isWallExist[r][c][DOWN]) {
                    return true;
                }
            } else {
                if (isWallExist[r][c][RIGHT] || isWallExist[r][c + 1][DOWN]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void controlTemperature() {
        int[][] controlInfo = new int[R][C];
        boolean[][][] isVisited = new boolean[R][C][4];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];
                    if (!isIn(nr, nc) || isVisited[r][c][d] || isWallExist[r][c][d]) {
                        continue;
                    }
                    int control = Math.abs(temperature[r][c] - temperature[nr][nc]) / 4;
                    if (temperature[r][c] > temperature[nr][nc]) {
                        controlInfo[r][c] -= control;
                        controlInfo[nr][nc] += control;
                    } else {
                        controlInfo[r][c] += control;
                        controlInfo[nr][nc] -= control;
                    }
                    isVisited[r][c][d] = true;
                    isVisited[nr][nc][d == 0 ? 1 : (d == 1 ? 0 : (d == 2 ? 3 : 2))] = true;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temperature[i][j] += controlInfo[i][j];
            }
        }
    }

    private static void reduceOutsideTemperature() {
        for (int i = 0; i < R; i += R - 1) {
            for (int j = 0; j < C; j++) {
                if (temperature[i][j] > 0) {
                    temperature[i][j]--;
                }
            }
        }
        for (int i = 0; i < C; i += C - 1) {
            for (int j = 1; j < R - 1; j++) {
                if (temperature[j][i] > 0) {
                    temperature[j][i]--;
                }
            }
        }
    }

    private static void eatChocolate() {
        chocolates++;
    }

    private static boolean investigate() {
        for (int[] space : investigationSpaces) {
            if (temperature[space[0]][space[1]] < K) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
