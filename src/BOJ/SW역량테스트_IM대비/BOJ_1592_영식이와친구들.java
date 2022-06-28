package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1592_영식이와친구들 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int getBall[] = new int[N + 1];

        int count = 0;
        int from = 0, to = 1;
        while (true) {
            if (++getBall[to] == M) break;
            from = to;
            to = getBall[from] % 2 == 1 ? from + L : from - L;
            if (to > N) to %= N;
            else if (to <= 0) to += N;
            count++;
        }

        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

}
