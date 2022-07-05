package BOJ.BackTracking;

import java.io.*;
import java.util.Arrays;

public class Main_BJ_2992_크면서작은수 {
    static int X, first, length;
    static int numbers[], selected[];
    static boolean isSelected[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());
        length = Integer.toString(X).length();
        numbers = new int[length];
        selected = new int[length];
        isSelected = new boolean[length];
        int temp = X;
        for (int i = 0; i < length; i++) {
            numbers[i] = (int) (temp / Math.pow(10, length - i - 1));
            temp -= (Math.pow(10, length - i - 1) * numbers[i]);
        }
        first = (int) (X / Math.pow(10, length - 0 - 1));
        Arrays.sort(numbers);
        findMinNumber(0);
        System.out.println(0);

    }

    private static void findMinNumber(int count) {
        if (count != 0 && (selected[0] == 0 || selected[0] < first)) return;
        if (count == length) {
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum += selected[i] * Math.pow(10, length - i - 1);
            }
            if (sum > X) {
                System.out.println(sum);
                System.exit(0);
            }
            return;
        }
        for (int i = 0; i < length; i++) {
            if (isSelected[i]) continue;
            selected[count] = numbers[i];
            isSelected[i] = true;
            findMinNumber(count + 1);
            isSelected[i] = false;
        }

    }
}
