package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15665_N과M_11 {
    static StringBuilder sb;
    static int N, M;
    static int numbers[], selected[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        selected = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(numbers);
        selectNumbers(0);
        System.out.println(sb.toString());

    }

    private static void selectNumbers(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++)
                sb.append(selected[i]).append(" ");
            sb.append("\n");
            return;
        }
        int last = -1;
        for (int i = 0; i < N; i++) {
            if (last == numbers[i])
                continue;
            last=numbers[i];
            selected[count] = numbers[i];
            selectNumbers(count + 1);
        }
    }
}
