package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_14501_퇴사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int schedule[][] = new int[N + 1][2];
        StringTokenizer st = null;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        int cost[] = new int[N + 2];
        for (int i = N; i >= 1; i--) {
            int tmp = i + schedule[i][0];
            if (tmp > N + 1) // 퇴사 전 상담을 끝낼 수 없는 경우
                cost[i] = cost[i + 1];
            else // 선택하지 않은 경우 VS 선택하여 그 기간동안 다른 상담 못하는 경우
                cost[i] = Math.max(cost[i + 1], cost[tmp] + schedule[i][1]);
        }
        //System.out.println(Arrays.toString(cost));
        bw.write(Integer.toString(cost[1]));
        br.close();
        bw.flush();
        bw.close();

    }

}
