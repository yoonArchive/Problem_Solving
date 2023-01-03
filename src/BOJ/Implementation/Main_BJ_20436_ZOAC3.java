package BOJ.Implementation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_20436_ZOAC3 {

    public static char[][] keyboard;
    public static boolean isLeft;

    public static class MoveInfo {
        char alphabet;
        int r;
        int c;

        public MoveInfo(char alphabet, int r, int c) {
            this.alphabet = alphabet;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        keyboard = new char[3][10];
        keyboard[0] = new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        keyboard[1] = new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        keyboard[2] = new char[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        MoveInfo leftStart = new MoveInfo(st.nextToken().charAt(0), 0, 0);
        MoveInfo rightStart = new MoveInfo(st.nextToken().charAt(0), 0, 0);
        getLocation(leftStart);
        getLocation(rightStart);
        String word = br.readLine();
        MoveInfo current = new MoveInfo('_', 0, 0);
        int cost = 0;
        for (int i = 0; i < word.length(); i++) {
            current.alphabet = word.charAt(i);
            getLocation(current);
            if (isLeft) {
                cost += getCost(leftStart, current);
                leftStart.r = current.r;
                leftStart.c = current.c;
            } else {
                cost += getCost(rightStart, current);
                rightStart.r = current.r;
                rightStart.c = current.c;
            }
        }
        bw.write(Integer.toString(cost + word.length()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void getLocation(MoveInfo moveInfo) {
        outer:
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                if (keyboard[i][j] == moveInfo.alphabet) {
                    moveInfo.r = i;
                    moveInfo.c = j;
                    isLeft = i <= 1 ? (j <= 4 ? true : false) : (j <= 3 ? true : false);
                    break outer;
                }
            }
        }
    }

    private static int getCost(MoveInfo start, MoveInfo current) {
        return Math.abs(start.r - current.r) + Math.abs(start.c - current.c);
    }
}
