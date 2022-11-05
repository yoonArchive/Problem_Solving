package BOJ.BruteForce;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1941_소문난칠공주 {

    public static final char DASOM_TEAM = 'S';
    public static final int SIZE = 5;
    public static final int MAX_COUNT = 7;

    public static char[][] classroom;
    public static boolean[][] isSelected;
    public static int count;
    public static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static class Student {
        int r;
        int c;

        public Student(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        classroom = new char[SIZE][SIZE];
        isSelected = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            classroom[i] = br.readLine().toCharArray();
        }
        formSevenPrincess(0, 0, 0);
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void formSevenPrincess(int index, int dasomTeamCount, int doYeonTeamCount) {
        if (doYeonTeamCount == 4) {
            return;
        }
        if (dasomTeamCount + doYeonTeamCount == MAX_COUNT) {
            if (canMakeSevenPrincess()) {
                count++;
            }
            return;
        }
        for (int studentNumber = index; studentNumber < SIZE * SIZE; studentNumber++) {
            int rowIndex = studentNumber / SIZE;
            int columnIndex = studentNumber % SIZE;
            if (isSelected[rowIndex][columnIndex]) {
                continue;
            }
            isSelected[rowIndex][columnIndex] = true;
            char currentStudent = classroom[rowIndex][columnIndex];
            if (currentStudent == DASOM_TEAM) {
                formSevenPrincess(studentNumber + 1, dasomTeamCount + 1, doYeonTeamCount);
            } else {
                formSevenPrincess(studentNumber + 1, dasomTeamCount, doYeonTeamCount + 1);
            }
            isSelected[rowIndex][columnIndex] = false;
        }
    }

    private static boolean canMakeSevenPrincess() {
        boolean isConnected = false;
        outer:
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isSelected[i][j]) {
                    isConnected = getIsConnected(i, j);
                    break outer;
                }
            }
        }
        return isConnected;
    }

    private static boolean getIsConnected(int sr, int sc) {
        Queue<Student> queue = new LinkedList<>();
        boolean[][] isOffered = new boolean[SIZE][SIZE];
        queue.offer(new Student(sr, sc));
        isOffered[sr][sc] = true;
        int offeredCount = 1;
        while (!queue.isEmpty()) {
            Student student = queue.poll();
            int r = student.r;
            int c = student.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (isIn(nr, nc) && !isOffered[nr][nc] && isSelected[nr][nc]) {
                    queue.offer(new Student(nr, nc));
                    isOffered[nr][nc] = true;
                    offeredCount++;
                }
            }
        }
        return offeredCount == MAX_COUNT ? true : false;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < SIZE && c < SIZE;
    }
}
