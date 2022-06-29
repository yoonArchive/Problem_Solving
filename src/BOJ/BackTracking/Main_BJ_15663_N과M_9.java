package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15663_N과M_9 {
    static int[] numbers, selected;
    static boolean[] isSelected;
    static int N, M;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        isSelected = new boolean[N];
        selected = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(numbers);
        permutation(0);
        System.out.println(sb);
    }

    public static void permutation(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i] + " ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            if (prev == numbers[i]) continue;
            prev = numbers[i];
            selected[count] = numbers[i];
            isSelected[i] = true;
            permutation(count + 1);
            isSelected[i] = false;
        }
    }

}