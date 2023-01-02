package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_7490_0만들기 {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char SPACE = ' ';

    public static StringBuilder sb;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            makeZero(1, 1, SPACE, new StringBuilder("1"));
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void makeZero(int n, int result, char preOperator, StringBuilder currentSb) {
        if (n == N) {
            if (result == 0) {
                sb.append(currentSb).append("\n");
            }
            return;
        }
        int nextNumber = n + 1;
        makeZero(nextNumber, preOperator == SPACE ? result * 10 + nextNumber : (preOperator == PLUS ? result - n + (n * 10 + nextNumber) : result + n - (n * 10 + nextNumber)), SPACE, new StringBuilder(currentSb).append(SPACE).append(nextNumber));
        makeZero(nextNumber, result + nextNumber, PLUS, new StringBuilder(currentSb).append(PLUS).append(nextNumber));
        makeZero(nextNumber, result - nextNumber, MINUS, new StringBuilder(currentSb).append(MINUS).append(nextNumber));
    }
}
