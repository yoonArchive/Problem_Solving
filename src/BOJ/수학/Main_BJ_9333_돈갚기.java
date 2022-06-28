package BOJ.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_9333_돈갚기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            double R = Double.parseDouble(st.nextToken()); // 이자
            double B = Double.parseDouble(st.nextToken()); // 갚아야 할 돈
            double M = Double.parseDouble(st.nextToken()); // 과외비

            int month = 0;
            while (true) {
                month++;
                if (month > 1200) {
                    sb.append("impossible").append("\n");
                    break;
                }

                // 센트로 변환
                double cent = B * 100;

                // 이자붙은 남은 돈
                cent = Math.round(cent * (1 + R / 100));

                // 달러로 변환
                B = cent / 100;

                // 과외비로 갚고 남은 돈
                B -= M;
                if (B <= 0) {
                    sb.append(month).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }
}
