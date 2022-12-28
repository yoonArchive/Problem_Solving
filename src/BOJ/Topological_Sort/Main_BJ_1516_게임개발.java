package BOJ.Topological_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1516_게임개발 {

    public static int N;
    public static int[] preBuildCount, time;
    public static List<List<Integer>> preBuildInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        preBuildInfo = new ArrayList<>();
        preBuildCount = new int[N + 1];
        time = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            preBuildInfo.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            String preBuildNumber;
            while (!(preBuildNumber = st.nextToken()).equals("-1")) {
                preBuildInfo.get(Integer.parseInt(preBuildNumber)).add(i);
                preBuildCount[i]++;
            }
        }
        int[] cumulativeTime = new int[N + 1];
        build(cumulativeTime);
        for (int i = 1; i <= N; i++) {
            sb.append(cumulativeTime[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int[] build(int[] cumulativeTime) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            cumulativeTime[i] = time[i];
            if (preBuildCount[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int currentBuilding = queue.poll();
            for (int nextBuilding : preBuildInfo.get(currentBuilding)) {
                if (--preBuildCount[nextBuilding] == 0) {
                    queue.offer(nextBuilding);
                }
                cumulativeTime[nextBuilding] = Math.max(cumulativeTime[nextBuilding], cumulativeTime[currentBuilding] + time[nextBuilding]);
            }
        }
        return cumulativeTime;
    }
}
