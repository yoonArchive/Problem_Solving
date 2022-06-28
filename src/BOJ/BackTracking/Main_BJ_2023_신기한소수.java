package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2023_신기한소수 {
    static int N, size, end;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        size = (int) Math.pow(10, N);

        int startNum[] = {2, 3, 5, 7};
        // 신기한 소수 구하기
        for (int start : startNum) {
            getWeirdPrimeNumbers(start, 2);
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void getWeirdPrimeNumbers(int number, int length) {
        if (length == N + 1) {
            sb.append(number).append("\n");
            return;
        }
        for (int i = 0; i <= 9; i++) {
            int tmp = number * 10 + i;
            if (isPrimeNumber(tmp))
                getWeirdPrimeNumbers(tmp, length + 1);
        }
    }

    private static boolean isPrimeNumber(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
