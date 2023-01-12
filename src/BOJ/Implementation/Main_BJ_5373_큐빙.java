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
                    changeFaceColor(front, right, true, true, 0, 0, false);
                    changeFaceColor(right, back, true, true, 0, 2, true);
                    changeFaceColor(back, left, true, true, 2, 0, true);
                    changeFaceColor(left, copiedFace, true, true, 0, 0, false);
                    rotate(top, true);
                } else {
                    changeFaceColor(front, left, true, true, 0, 0, false);
                    changeFaceColor(left, back, true, true, 0, 2, true);
                    changeFaceColor(back, right, true, true, 2, 0, true);
                    changeFaceColor(right, copiedFace, true, true, 0, 0, false);
                    rotate(top, false);
                }
                break;
            case DOWN_SIDE:
                copy(front);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(front, left, true, true, 2, 2, false);
                    changeFaceColor(left, back, true, true, 2, 0, true);
                    changeFaceColor(back, right, true, true, 0, 2, true);
                    changeFaceColor(right, copiedFace, true, true, 2, 2, false);
                    rotate(bottom, true);
                } else {
                    changeFaceColor(front, right, true, true, 2, 2, false);
                    changeFaceColor(right, back, true, true, 2, 0, true);
                    changeFaceColor(back, left, true, true, 0, 2, true);
                    changeFaceColor(left, copiedFace, true, true, 2, 2, false);
                    rotate(bottom, false);
                }
                break;
            case FRONT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, left, true, false, 2, 2, true);
                    changeFaceColor(left, bottom, false, true, 2, 0, false);
                    changeFaceColor(bottom, right, true, false, 0, 0, true);
                    changeFaceColor(right, copiedFace, false, true, 0, 2, false);
                    rotate(front, true);
                } else {
                    changeFaceColor(top, right, true, false, 2, 0, false);
                    changeFaceColor(right, bottom, false, true, 0, 0, true);
                    changeFaceColor(bottom, left, true, false, 0, 2, false);
                    changeFaceColor(left, copiedFace, false, true, 2, 2, true);
                    rotate(front, false);
                }
                break;
            case BACK_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, right, true, false, 0, 2, false);
                    changeFaceColor(right, bottom, false, true, 2, 2, true);
                    changeFaceColor(bottom, left, true, false, 2, 0, false);
                    changeFaceColor(left, copiedFace, false, true, 0, 0, true);
                    rotate(back, true);
                } else {
                    changeFaceColor(top, left, true, false, 0, 0, true);
                    changeFaceColor(left, bottom, false, true, 0, 2, false);
                    changeFaceColor(bottom, right, true, false, 2, 2, true);
                    changeFaceColor(right, copiedFace, false, true, 2, 0, false);
                    rotate(back, false);
                }
                break;
            case LEFT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, back, false, false, 0, 0, false);
                    changeFaceColor(back, bottom, false, false, 0, 0, false);
                    changeFaceColor(bottom, front, false, false, 0, 0, false);
                    changeFaceColor(front, copiedFace, false, false, 0, 0, false);
                    rotate(left, true);
                } else {
                    changeFaceColor(top, front, false, false, 0, 0, false);
                    changeFaceColor(front, bottom, false, false, 0, 0, false);
                    changeFaceColor(bottom, back, false, false, 0, 0, false);
                    changeFaceColor(back, copiedFace, false, false, 0, 0, false);
                    rotate(left, false);
                }
                break;
            case RIGHT_SIDE:
                copy(top);
                if (direction == CLOCK_WISE) {
                    changeFaceColor(top, front, false, false, 2, 2, false);
                    changeFaceColor(front, bottom, false, false, 2, 2, false);
                    changeFaceColor(bottom, back, false, false, 2, 2, false);
                    changeFaceColor(back, copiedFace, false, false, 2, 2, false);
                    rotate(right, true);
                } else {
                    changeFaceColor(top, back, false, false, 2, 2, false);
                    changeFaceColor(back, bottom, false, false, 2, 2, false);
                    changeFaceColor(bottom, front, false, false, 2, 2, false);
                    changeFaceColor(front, copiedFace, false, false, 2, 2, false);
                    rotate(right, false);
                }
                break;
        }
    }

    private static void changeFaceColor(char[][] to, char[][] from, boolean toIsRow, boolean fromIsRow, int toIdx, int fromIdx, boolean isReverseOrder) {
        if (toIsRow == fromIsRow) {
            if (isReverseOrder) {
                for (int i = 0; i < 3; i++) {
                    to[toIdx][i] = from[fromIdx][2 - i];
                }
            } else {
                if (toIsRow) {
                    for (int i = 0; i < 3; i++) {
                        to[toIdx][i] = from[fromIdx][i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        to[i][toIdx] = from[i][fromIdx];
                    }
                }
            }
        } else {
            if (toIsRow) {
                if (isReverseOrder) {
                    for (int i = 0; i < 3; i++) {
                        to[toIdx][i] = from[2 - i][fromIdx];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        to[toIdx][i] = from[i][fromIdx];
                    }
                }
            } else {
                if (isReverseOrder) {
                    for (int i = 0; i < 3; i++) {
                        to[i][toIdx] = from[fromIdx][2 - i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        to[i][toIdx] = from[fromIdx][i];
                    }
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
