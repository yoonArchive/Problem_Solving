package BOJ.BackTracking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_18428_감시피하기 {

    public static final char TEACHER = 'T';
    public static final char STUDENT = 'S';
    public static final char OBSTACLE = 'O';
    public static final char EMPTY = 'X';
    public static final String POSSIBLE = "YES";
    public static final String IMPOSSIBLE = "NO";
    public static final int INSTALL_COUNT = 3;
    public static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static BufferedWriter bw;
    public static int N;
    public static char[][] hallway;
    public static List<TeacherLocation> teacherLocationList;

    public static class TeacherLocation {
        int r;
        int c;

        public TeacherLocation(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        hallway = new char[N][N];
        teacherLocationList = new ArrayList<>();
        StringTokenizer st;
        char current;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                current = st.nextToken().charAt(0);
                hallway[i][j] = current;
                if (current == TEACHER) {
                    teacherLocationList.add(new TeacherLocation(i, j));
                }
            }
        }
        installObstacle(0, 0);
        bw.write(IMPOSSIBLE);
        br.close();
        bw.flush();
        bw.close();
    }

    private static void installObstacle(int index, int count) throws IOException {
        if (count == INSTALL_COUNT) {
            if (!canMonitorStudents()) {
                bw.write(POSSIBLE);
                bw.flush();
                System.exit(0);
            }
            return;
        }
        if (index == N * N) {
            return;
        }
        int row = index / N;
        int col = index % N;
        if (hallway[row][col] == EMPTY) {
            hallway[row][col] = OBSTACLE;
            installObstacle(index + 1, count + 1);
            hallway[row][col] = EMPTY;
        }
        installObstacle(index + 1, count);
    }

    private static boolean canMonitorStudents() {
        for (TeacherLocation location : teacherLocationList) {
            int locationR = location.r;
            int locationC = location.c;
            for (int i = 0; i < 4; i++) {
                if (canReachStudent(locationR, locationC, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canReachStudent(int teacherR, int teacherC, int direction) {
        for (int dist = 1; dist < N; dist++) {
            int r = teacherR + (dist * DELTAS[direction][0]);
            int c = teacherC + (dist * DELTAS[direction][1]);
            if (!isIn(r, c) || hallway[r][c] == OBSTACLE) {
                break;
            }
            if (hallway[r][c] == STUDENT) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
