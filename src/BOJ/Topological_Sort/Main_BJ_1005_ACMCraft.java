package BOJ.Topological_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1005_ACMCraft {

    public static int N, K, W;
    public static int[] constructionTimes, preBuildCounts;
    public static List<List<Integer>> orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            constructionTimes = new int[N + 1];
            preBuildCounts = new int[N + 1];
            orders = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                orders.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                constructionTimes[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                preBuildCounts[Y]++;
                orders.get(X).add(Y);
            }
            W = Integer.parseInt(br.readLine());
            sb.append(build()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int build() {
        Queue<Integer> queue = new LinkedList<>();
        int[] cumulativeTime = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cumulativeTime[i] = constructionTimes[i];
            if (preBuildCounts[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int buildingNumber = queue.poll();
            for (int number : orders.get(buildingNumber)) {
                cumulativeTime[number] = Math.max(cumulativeTime[number], cumulativeTime[buildingNumber] + constructionTimes[number]);
                if (--preBuildCounts[number] == 0) {
                    queue.offer(number);
                }
            }
        }
        return cumulativeTime[W];
    }
}
