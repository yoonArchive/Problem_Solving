package BOJ.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_21921_블로그 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] visitors = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            visitors[i] = Integer.parseInt(st.nextToken());
        // X일 동안 가장 많이 들어온 방문자 수와 기간 수 구하기
        int max = 0, cnt = 0, cur = 0;
        for (int i = 0; i < X; i++)
            cur += visitors[i];
        max = cur;
        cnt = 1;
        for (int i = X; i < N; i++) {
            cur -= visitors[i - X];
            cur += visitors[i];
            if (cur > max) {
                max = cur;
                cnt = 1;
            } else if (cur == max)
                cnt++;
        }
        if (max == 0)
            sb.append("SAD");
        else
            sb.append(max).append("\n").append(cnt);
        System.out.println(sb.toString());
        br.close();

    }

}
