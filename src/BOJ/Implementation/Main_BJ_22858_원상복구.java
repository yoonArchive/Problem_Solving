package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_22858_원상복구 {
    static int N, K;
    static int S[], D[], P[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new int[N + 1];
        D = new int[N + 1];
        P = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++)
            S[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++)
            D[i] = Integer.parseInt(st.nextToken());
        restore(K);
        for (int i = 1; i <= N; i++)
            sb.append(P[i]).append(" ");
        System.out.println(sb.toString());
        br.close();
    }

    private static void restore(int K) {
        while (K-- > 0) {
            for (int i = 1; i <= N; i++)
                P[D[i]] = S[i];
            S = P.clone();
        }

    }

}
