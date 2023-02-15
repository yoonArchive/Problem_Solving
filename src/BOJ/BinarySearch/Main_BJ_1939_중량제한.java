package BOJ.BinarySearch;

import java.io.*;
import java.util.*;

public class Main_BJ_1939_중량제한 {

    public static final int INF = 1000000000;

    public static int N, M, island1, island2;
    public static List<List<Bridge>> bridges;

    public static class Bridge {
        int connectedIsland;
        int weightLimit;

        public Bridge(int connectedIsland, int weightLimit) {
            this.connectedIsland = connectedIsland;
            this.weightLimit = weightLimit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bridges = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            bridges.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            bridges.get(A).add(new Bridge(B, C));
            bridges.get(B).add(new Bridge(A, C));
        }
        st = new StringTokenizer(br.readLine());
        island1 = Integer.parseInt(st.nextToken());
        island2 = Integer.parseInt(st.nextToken());
        int left = 0, right = INF, maxWeight = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canMove(mid)) {
                left = mid + 1;
                maxWeight = mid;
            } else {
                right = mid - 1;
            }
        }
        bw.write(Integer.toString(maxWeight));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean canMove(int weight) {
        Queue<Bridge> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];
        queue.offer(new Bridge(island1, 0));
        isVisited[island1] = true;
        while (!queue.isEmpty()) {
            Bridge bridge = queue.poll();
            int connectedIsland = bridge.connectedIsland;
            int weightLimit = bridge.weightLimit;
            if (connectedIsland == island2) {
                return true;
            }
            for (Bridge nextBridge : bridges.get(connectedIsland)) {
                int nconnectedIsland = nextBridge.connectedIsland;
                int nWeightLimit = nextBridge.weightLimit;
                if (isVisited[nconnectedIsland] || weight > nWeightLimit) {
                    continue;
                }
                queue.offer(new Bridge(nconnectedIsland, nWeightLimit));
                isVisited[nconnectedIsland] = true;
            }
        }
        return false;
    }
}
