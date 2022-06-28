package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2661_좋은수열 {

    static int N;
    static StringBuilder sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sequence = new StringBuilder();
        sequence.append(1);
        getGoodSequence(2);
    }

    private static void getGoodSequence(int length) {
        if (length - 1 == N) {
            System.out.println(sequence.toString());
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            sequence.append(i);
            if (isGood(length))
                getGoodSequence(length + 1);
            else
                sequence.setLength(sequence.length() - 1);
        }
        sequence.setLength(sequence.length() - 1);
    }

    private static boolean isGood(int length) {
        int startIdx = length - 2;
        int strLen = 1;
        while (startIdx >= 0) {
            if (sequence.substring(startIdx, startIdx + strLen).toString()
                    .equals(sequence.substring(startIdx + strLen).toString()))
                return false;
            startIdx -= 2;
            strLen++;
        }
        return true;
    }
}
