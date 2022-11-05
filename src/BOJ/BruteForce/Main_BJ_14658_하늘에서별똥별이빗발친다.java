package BOJ.BruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_14658_하늘에서별똥별이빗발친다 {

    public static int N, M, L, K;
    public static List<ShootingStar> starList;

    public static class ShootingStar {
        int x;
        int y;

        public ShootingStar(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        starList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            starList.add(new ShootingStar(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }
        int bounceCount = 0;
        for (ShootingStar star1 : starList) {
            for (ShootingStar star2 : starList) {
                bounceCount = Math.max(bounceCount, getBounceCount(star1.x, star2.y));
            }
        }
        bw.write(Integer.toString(K - bounceCount));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int getBounceCount(int x, int y) {
        int bounceCount = 0;
        for (ShootingStar star : starList) {
            if (star.x >= x && star.x <= x + L && star.y >= y && star.y <= y + L) {
                bounceCount++;
            }
        }
        return bounceCount;
    }
}
