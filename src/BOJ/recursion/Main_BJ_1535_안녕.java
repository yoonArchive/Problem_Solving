package BOJ.recursion;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1535_안녕 {

    public static int[] loss, joy;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        loss = new int[N];
        joy = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            loss[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            joy[i] = Integer.parseInt(st.nextToken());
        }
        bw.write(Integer.toString(greet(0, 0, 0)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int greet(int personId, int currentHp, int sum) {
        if (currentHp >= 100) {
            return 0;
        }
        if (personId == N) {
            return sum;
        }
        return Math.max(greet(personId + 1, currentHp + loss[personId], sum + joy[personId]), greet(personId + 1, currentHp, sum));
    }
}
