package BOJ.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10211_Maximum_Subarray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] maximumSubarrays = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            maximumSubarrays[0] = Integer.parseInt(st.nextToken());
            int maximumSubarray = maximumSubarrays[0];
            for (int i = 1; i < N; i++) {
                int current = Integer.parseInt(st.nextToken());
                maximumSubarrays[i] = current + maximumSubarrays[i - 1] > current ? current + maximumSubarrays[i - 1] : current;
                maximumSubarray = Math.max(maximumSubarray, maximumSubarrays[i]);
            }
            sb.append(maximumSubarray).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
