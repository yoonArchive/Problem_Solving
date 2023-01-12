package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_5373_큐빙 {

    public static final char WHITE = 'w';
    public static final char YELLOW = 'y';
    public static final char RED = 'r';
    public static final char ORANGE = 'o';
    public static final char GREEN = 'g';
    public static final char BLUE = 'b';
    public static final char UP_SIDE = 'U';
    public static final char DOWN_SIDE = 'D';
    public static final char FRONT_SIDE = 'F';
    public static final char BACK_SIDE = 'B';
    public static final char LEFT_SIDE = 'L';
    public static final char RIGHT_SIDE = 'R';
    public static final char CLOCK_WISE = '+';

    public static char[][] top, bottom, front, back, left, right, copiedFace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            setFaceColor();
            copiedFace = new char[3][3];
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                String command = st.nextToken();
                turnCube(command.charAt(0), command.charAt(1));
            }
            printTop(sb);
        }
        System.out.println(sb);
        br.close();
    }

    private static void setFaceColor() {
        top = fillColor(WHITE);
        bottom = fillColor(YELLOW);
        front = fillColor(RED);
        back = fillColor(ORANGE);
        left = fillColor(GREEN);
        right = fillColor(BLUE);
    }

    private static char[][] fillColor(char color) {
        char[][] face = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(face[i], color);
        }
        return face;
    }

    private static void turnCube(char turnedFace, char direction) {
        switch (turnedFace) {
            case UP_SIDE:
                copy(front);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(front, right, true, true, 0, 0);
                    changeFaceColorWithReverseOrder(right, back, true, true, 0, 2);
                    changeFaceColorWithReverseOrder(back, left, true, true, 2, 0);
                    changeFaceColor(left, copiedFace, true, true, 0, 0);
                    rotate(top, true);
                } else {
                    changeFaceColor(front, left, true, true, 0, 0);
                    changeFaceColorWithReverseOrder(left, back, true, true, 0, 2);
                    changeFaceColorWithReverseOrder(back, right, true, true, 2, 0);
                    changeFaceColor(right, copiedFace, true, true, 0, 0);
                    rotate(top, false);
                }
                break;
            case DOWN_SIDE:
                copy(front);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(front, left, true, true, 2, 2);
                    changeFaceColorWithReverseOrder(left, back, true, true, 2, 0);
                    changeFaceColorWithReverseOrder(back, right, true, true, 0, 2);
                    changeFaceColor(right, copiedFace, true, true, 2, 2);
                    rotate(bottom, true);
                } else {
                    changeFaceColor(front, right, true, true, 2, 2);
                    changeFaceColorWithReverseOrder(right, back, true, true, 2, 0);
                    changeFaceColorWithReverseOrder(back, left, true, true, 0, 2);
                    changeFaceColor(left, copiedFace, true, true, 2, 2);
                    rotate(bottom, false);
                }
                break;
            case FRONT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColorWithReverseOrder(top, left, true, false, 2, 2);
                    changeFaceColor(left, bottom, false, true, 2, 0);
                    changeFaceColorWithReverseOrder(bottom, right, true, false, 0, 0);
                    changeFaceColor(right, copiedFace, false, true, 0, 2);
                    rotate(front, true);
                } else {
                    changeFaceColor(top, right, true, false, 2, 0);
                    changeFaceColorWithReverseOrder(right, bottom, false, true, 0, 0);
                    changeFaceColor(bottom, left, true, false, 0, 2);
                    changeFaceColorWithReverseOrder(left, copiedFace, false, true, 2, 2);
                    rotate(front, false);
                }
                break;
            case BACK_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, right, true, false, 0, 2);
                    changeFaceColorWithReverseOrder(right, bottom, false, true, 2, 2);
                    changeFaceColor(bottom, left, true, false, 2, 0);
                    changeFaceColorWithReverseOrder(left, copiedFace, false, true, 0, 0);
                    rotate(back, true);
                } else {
                    changeFaceColorWithReverseOrder(top, left, true, false, 0, 0);
                    changeFaceColor(left, bottom, false, true, 0, 2);
                    changeFaceColorWithReverseOrder(bottom, right, true, false, 2, 2);
                    changeFaceColor(right, copiedFace, false, true, 2, 0);
                    rotate(back, false);
                }
                break;
            case LEFT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, back, false, false, 0, 0);
                    changeFaceColor(back, bottom, false, false, 0, 0);
                    changeFaceColor(bottom, front, false, false, 0, 0);
                    changeFaceColor(front, copiedFace, false, false, 0, 0);
                    rotate(left, true);
                } else {
                    changeFaceColor(top, front, false, false, 0, 0);
                    changeFaceColor(front, bottom, false, false, 0, 0);
                    changeFaceColor(bottom, back, false, false, 0, 0);
                    changeFaceColor(back, copiedFace, false, false, 0, 0);
                    rotate(left, false);
                }
                break;
            case RIGHT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, front, false, false, 2, 2);
                    changeFaceColor(front, bottom, false, false, 2, 2);
                    changeFaceColor(bottom, back, false, false, 2, 2);
                    changeFaceColor(back, copiedFace, false, false, 2, 2);
                    rotate(right, true);
                } else {
                    changeFaceColor(top, back, false, false, 2, 2);
                    changeFaceColor(back, bottom, false, false, 2, 2);
                    changeFaceColor(bottom, front, false, false, 2, 2);
                    changeFaceColor(front, copiedFace, false, false, 2, 2);
                    rotate(right, false);
                }
                break;
        }
    }

    private static void changeFaceColorWithReverseOrder(char[][] to, char[][] from, boolean toIsRow, boolean fromIsRow, int toIdx, int fromIdx) {
        if (toIsRow == fromIsRow) {
            for (int i = 0; i < 3; i++) {
                to[toIdx][i] = from[fromIdx][2 - i];
            }
        } else {
            if (toIsRow) {
                for (int i = 0; i < 3; i++) {
                    to[toIdx][i] = from[2 - i][fromIdx];
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    to[i][toIdx] = from[fromIdx][2 - i];
                }
            }
        }
    }

    private static void changeFaceColor(char[][] to, char[][] from, boolean toIsRow, boolean fromIsRow, int toIdx, int fromIdx) {
        if (toIsRow == fromIsRow) {
            if (toIsRow) {
                for (int i = 0; i < 3; i++) {
                    to[toIdx][i] = from[fromIdx][i];
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    to[i][toIdx] = from[i][fromIdx];
                }
            }
        } else {
            if (toIsRow) {
                for (int i = 0; i < 3; i++) {
                    to[toIdx][i] = from[i][fromIdx];
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    to[i][toIdx] = from[fromIdx][i];
                }
            }
        }
    }

    private static void rotate(char[][] face, boolean isClockWise) {
        char rotated[][] = new char[3][3];
        if (isClockWise) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotated[i][j] = face[2 - j][i];
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotated[i][j] = face[j][2 - i];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[i][j] = rotated[i][j];
            }
        }
    }

    private static void copy(char[][] face) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copiedFace[i][j] = face[i][j];
            }
        }
    }

    private static void printTop(StringBuilder sb) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(top[i][j]);
            }
            sb.append("\n");
        }
    }
}
