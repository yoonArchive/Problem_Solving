package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2529_부등호 {

    public static int k;
    public static long min, max;
    public static StringBuilder minSb, maxSb;
    public static boolean[] isSmallerThanPrev, isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        isSmallerThanPrev = new boolean[k];
        isSelected = new boolean[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            if (st.nextToken().charAt(0) == '<') {
                isSmallerThanPrev[i] = true;
            }
        }
        min = Long.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            isSelected[i] = true;
            getSequence(1, i, new StringBuilder(Integer.toString(i)));
            isSelected[i] = false;
        }
        sb.append(maxSb).append("\n").append(minSb);
        System.out.println(sb);
        br.close();
    }

    private static void getSequence(int index, int prev, StringBuilder sequence) {
        if (index == k + 1) {
            long number = Long.parseLong(sequence.toString());
            if (number < min) {
                min = number;
                minSb = sequence;
            } else if (number > max) {
                max = number;
                maxSb = sequence;
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (isSelected[i] || (isSmallerThanPrev[index - 1] && i <= prev) || (!isSmallerThanPrev[index - 1] && i >= prev)) {
                continue;
            }
            isSelected[i] = true;
            getSequence(index + 1, i, new StringBuilder(sequence).append(i));
            isSelected[i] = false;
        }
    }
}
