package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1038_감소하는수_fail {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        getDecreaseNumber(1, 1);
    }

    private static void getDecreaseNumber(int count, long start) {
        if (count - 1 == N) {
            System.out.println(start - 1);
            System.exit(0);
        }
        System.out.println(start - 1);
        for (long i = start; i <= 9876543210L; i++) {
            if (isDecreaseNumber(i))
                getDecreaseNumber(count + 1, i + 1);
        }
    }

    private static boolean isDecreaseNumber(long number) {
        if (number < 10)
            return true;

        int length = Long.toString(number).length();
        long base = (long) Math.pow(10, length - 1);
        long pre = number / base;
        number -= (base * pre);
        while (true) {
            base = base / 10;
            long current = number / base;
            if (pre <= current)
                return false;
            pre = current;
            number = number - (base * pre);
            if (base == 1)
                break;
        }
        return true;
    }

}
