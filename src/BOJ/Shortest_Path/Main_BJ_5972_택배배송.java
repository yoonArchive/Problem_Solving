package BOJ.Shortest_Path;

import java.io.*;
import java.util.*;

public class Main_BJ_5972_택배배송 {

    public static final int MAX_DIST = Integer.MAX_VALUE;

    public static int N, M;
    public static List<List<MoveInfo>> moveInfoList;

    public static class MoveInfo implements Comparable<MoveInfo> {
        int to;
        int cost;

        public MoveInfo(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(MoveInfo o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        moveInfoList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            moveInfoList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cows = Integer.parseInt(st.nextToken());
            moveInfoList.get(a).add(new MoveInfo(b, cows));
            moveInfoList.get(b).add(new MoveInfo(a, cows));
        }
        bw.write(Integer.toString(move()));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move() {
        PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(dist, MAX_DIST);
        pq.offer(new MoveInfo(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            MoveInfo moveInfo = pq.poll();
            int to = moveInfo.to;
            int cost = moveInfo.cost;
            if (isVisited[to]) {
                continue;
            }
            isVisited[to] = true;
            for (MoveInfo nextMove : moveInfoList.get(to)) {
                int nextTo = nextMove.to;
                int nextCost = nextMove.cost;
                if (dist[nextTo] > cost + nextCost) {
                    dist[nextTo] = cost + nextCost;
                    pq.offer(new MoveInfo(nextTo, dist[nextTo]));
                }
            }
        }
        return dist[N];
    }
}
