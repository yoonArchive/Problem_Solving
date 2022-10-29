package BOJ.BruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_2503_숫자야구 {

    public static int[][] bullsAndCows;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        bullsAndCows = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                bullsAndCows[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        for (int number = 100; number <= 999; number++) {
            if (!canValidate(number)) {
                continue;
            }
            if (validate(number)) {
                count++;
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean canValidate(int number) {
        String string = Integer.toString(number);
        for (int i = 0; i < 3; i++) {
            char currentChar = string.charAt(i);
            if (currentChar == '0') {
                return false;
            }
            for (int j = i + 1; j < 3; j++) {
                if (string.charAt(j) == currentChar) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validate(int number) {
        for (int i = 0; i < N; i++) {
            int strikeCount = getStrikeCount(number, bullsAndCows[i][0]);
            if (bullsAndCows[i][1] != getStrikeCount(number, bullsAndCows[i][0])) {
                return false;
            }
            if (bullsAndCows[i][2] != getBallCount(strikeCount, number, bullsAndCows[i][0])) {
                return false;
            }
        }
        return true;
    }

    private static int getStrikeCount(int number, int question) {
        int strikeCount = 0;
        for (int i = 0; i < 3; i++) {
            if (Integer.toString(number).charAt(i) - '0' == Integer.toString(question).charAt(i) - '0') {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    private static int getBallCount(int strikeCount, int number, int question) {
        int ballCount = 0;
        for (int i = 0; i < 3; i++) {
            int compareNumber = Integer.toString(question).charAt(i) - '0';
            for (int j = 0; j < 3; j++) {
                if (Integer.toString(number).charAt(j) - '0' == compareNumber) {
                    ballCount++;
                }
            }
        }
        return ballCount - strikeCount;
    }
}
