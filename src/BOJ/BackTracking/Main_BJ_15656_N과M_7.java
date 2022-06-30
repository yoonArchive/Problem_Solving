package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15656_N과M_7 {
    static int N, M;
    static int numbers[], selected[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        selected = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        permutation(0);
        System.out.println(sb.toString());
        br.close();
    }

    private static void permutation(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++)
                sb.append(selected[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            selected[count] = numbers[i];
            permutation(count + 1);
        }

    }
}
