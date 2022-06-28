package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_21318_피아노체조 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] difficulty = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int pre = Integer.parseInt(st.nextToken()), cur = 0;
        for (int i = 2; i <= N; i++) {
            cur = Integer.parseInt(st.nextToken());
            if (cur < pre)
                difficulty[i - 1]++;
            difficulty[i] = difficulty[i - 1];
            pre = cur;
        }
        int Q = Integer.parseInt(br.readLine());
        int x = 0, y = 0;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (difficulty[y] != difficulty[y - 1])
                sb.append(difficulty[y] - difficulty[x - 1] - 1).append("\n");
            else
                sb.append(difficulty[y] - difficulty[x - 1]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}
