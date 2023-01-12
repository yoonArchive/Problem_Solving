package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1248_Guess {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char ZERO = '0';

    public static int N;
    public static int[] sequence, sum;
    public static char[] characters;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        sequence = new int[N];
        sum = new int[N + 1];
        characters = br.readLine().toCharArray();
        makeSequence(1);
    }

    private static void makeSequence(int count) {
        if (count == N + 1) {
            print();
            System.exit(0);
        }
        for (int number = -10; number <= 10; number++) {
            sum[count] = sum[count - 1] + number;
            int iter = count;
            int minusIdx = 0, charIdx = count - 1, i = N - 1;
            boolean flag = true;
            while (iter-- > 0) {
                int currentSum = sum[count] - sum[minusIdx++];
                char currentChar = currentSum < 0 ? MINUS : (currentSum > 0 ? PLUS : ZERO);
                if (currentChar != characters[charIdx]) {
                    flag = false;
                    break;
                }
                charIdx += (i--);
            }
            if (!flag) {
                continue;
            }
            sequence[count - 1] = number;
            makeSequence(count + 1);
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            sb.append(sequence[i]).append(" ");
        }
        System.out.println(sb);
    }
}
