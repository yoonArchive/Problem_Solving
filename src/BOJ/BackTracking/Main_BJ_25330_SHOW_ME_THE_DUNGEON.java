package BOJ.BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_25330_SHOW_ME_THE_DUNGEON {

    public static int N, maxFreed;
    public static int[] monstersPower;
    public static int[] population;
    public static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        monstersPower = new int[N + 1];
        population = new int[N + 1];
        isVisited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            monstersPower[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        visitTown(K, 0, 0, 0);
        bw.write(Integer.toString(maxFreed));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void visitTown(int remain, int sum, int freed, int visitCount) {
        maxFreed = Math.max(maxFreed, freed);
        if (visitCount == N) {
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (isVisited[i] || sum + monstersPower[i] > remain) {
                continue;
            }
            isVisited[i] = true;
            visitTown(remain - sum - monstersPower[i], sum + monstersPower[i], freed + population[i], visitCount + 1);
            isVisited[i] = false;
        }
    }
}
