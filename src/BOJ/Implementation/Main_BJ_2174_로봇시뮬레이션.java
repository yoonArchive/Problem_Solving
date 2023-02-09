package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2174_로봇시뮬레이션 {

    public static final int CRASH_INTO_WALL = 1;
    public static final int CRASH_INRO_ROBOT = 2;

    public static int A, B, N, M, crashedRobot;
    public static Robot[] robots;
    public static int[][] locatedRobots;

    public static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서

    public static class Robot {
        int r;
        int c;
        int direction;

        public Robot(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots = new Robot[N + 1];
        locatedRobots = new int[B][A];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int r = (B - 1) - (Integer.parseInt(st.nextToken()) - 1);
            robots[i] = new Robot(r, c, setDirection(st.nextToken().charAt(0)));
            locatedRobots[r][c] = i;
        }
        StringBuilder result = new StringBuilder("OK");
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotId = Integer.parseInt(st.nextToken());
            int status = operate(robotId, st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
            if (status == CRASH_INTO_WALL) {
                result = new StringBuilder("Robot ").append(robotId).append(" crashes into the wall");
                break;
            } else if (status == CRASH_INRO_ROBOT) {
                result = new StringBuilder("Robot ").append(robotId).append(" crashes into robot ").append(crashedRobot);
                break;
            }
        }
        System.out.println(result);
        br.close();
    }

    private static int operate(int robotId, char command, int repeat) {
        int completeStatus = 0;
        while (repeat-- > 0) {
            Robot robot = robots[robotId];
            int r = robot.r;
            int c = robot.c;
            int direction = robot.direction;
            if (command == 'L') {
                robots[robotId].direction = (--direction + 4) % 4;
            } else if (command == 'R') {
                robots[robotId].direction = (++direction + 4) % 4;
            } else if (command == 'F') {
                int nr = r + deltas[direction][0];
                int nc = c + deltas[direction][1];
                if (crashIntoWall(nr, nc)) {
                    completeStatus = CRASH_INTO_WALL;
                    break;
                } else if (crashIntoRobot(nr, nc)) {
                    completeStatus = CRASH_INRO_ROBOT;
                    crashedRobot = locatedRobots[nr][nc];
                    break;
                } else {
                    robots[robotId].r = nr;
                    robots[robotId].c = nc;
                    locatedRobots[r][c] = 0;
                    locatedRobots[nr][nc] = robotId;
                }
            }
        }
        return completeStatus;
    }

    private static boolean crashIntoWall(int r, int c) {
        return r < 0 || c < 0 || r >= B || c >= A;
    }

    private static boolean crashIntoRobot(int r, int c) {
        return locatedRobots[r][c] != 0;
    }

    private static int setDirection(char direction) {
        if (direction == 'N') {
            return 0;
        } else if (direction == 'E') {
            return 1;
        } else if (direction == 'S') {
            return 2;
        } else {
            return 3;
        }
    }
}
