package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울 {
    static int N, res;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("3234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            res = 0;
            N = Integer.parseInt(br.readLine());
            int weights[] = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++)
                weights[i] = Integer.parseInt(st.nextToken());
            setOrder(weights, 0, 0, 0, 0);
            sb.append("#" + tc + " " + res).append("\n");
        }
        System.out.println(sb);
        br.close();

    }

    private static void setOrder(int[] weights, int cnt, int flag, int left, int right) {
        if (cnt == N) {
            res++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) != 0) // 이미 순서가 정해진 추라면
                continue;

            int nowWeight = weights[i];

            // 왼쪽 저울에 올리기
            setOrder(weights, cnt + 1, flag | 1 << i, left + nowWeight, right);

            // 오른쪽 저울에 올리기
            int rightSum = right + nowWeight;
            if (rightSum <= left) setOrder(weights, cnt + 1, flag | 1 << i, left, rightSum);
        }

    }

}
