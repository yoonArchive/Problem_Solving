package BOJ.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1002_터렛 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            sb.append(getLocationCount(x1, y1, r1, x2, y2, r2)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int getLocationCount(int x1, int y1, int r1, int x2, int y2, int r2) {
        int distance = (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        if (distance == 0 && r1 == r2) {
            return -1;
        } else if (distance < Math.pow(r1 - r2, 2) || distance > Math.pow(r1 + r2, 2)) {
            return 0;
        } else if (distance == Math.pow(r1 - r2, 2) || distance == Math.pow(r1 + r2, 2)) {
            return 1;
        } else {
            return 2;
        }
    }
}
