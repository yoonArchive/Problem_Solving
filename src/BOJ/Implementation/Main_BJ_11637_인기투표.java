package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_11637_인기투표 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int max = 0, elected = 0, sum = 0;
            boolean isExist = false;
            for (int i = 1; i <= n; i++) {
                int votes = Integer.parseInt(br.readLine());
                sum += votes;
                if (votes > max) {
                    max = votes;
                    elected = i;
                    isExist = true;
                } else if (votes == max) {
                    isExist = false;
                }
            }
            if (!isExist) {
                sb.append("no winner").append("\n");
            } else {
                sb.append(max > sum / 2 ? "majority winner " : "minority winner ").append(elected).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
