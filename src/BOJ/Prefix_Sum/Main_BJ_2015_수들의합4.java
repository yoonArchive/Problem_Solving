package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BJ_2015_수들의합4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        long cnt = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
            if (A[i] == K)
                cnt++;
            // A[i]-K 를 더하지 않으면 K를 만들 수 있음.
            cnt += map.getOrDefault(A[i] - K, 0L);
            map.put(A[i], map.getOrDefault(A[i], 0L) + 1);
        }
        bw.write(Long.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }

}
