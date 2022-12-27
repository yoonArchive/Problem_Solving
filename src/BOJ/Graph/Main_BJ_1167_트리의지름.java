package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_1167_트리의지름 {

    public static List<List<Edge>> edgeList;
    public static Queue<int[]> queue;
    public static boolean[] isVisited;

    public static class Edge {
        int number;
        int distance;

        public Edge(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int V = Integer.parseInt(br.readLine());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            edgeList.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int currentVertex = Integer.parseInt(st.nextToken());
            while (true) {
                int connectedVertex = Integer.parseInt(st.nextToken());
                if (connectedVertex == -1) {
                    break;
                }
                int distance = Integer.parseInt(st.nextToken());
                edgeList.get(currentVertex).add(new Edge(connectedVertex, distance));
                edgeList.get(connectedVertex).add(new Edge(currentVertex, distance));
            }
        }
        // 1번 정점으로부터 가장 멀리 있는 정점의 번호 구하기
        queue = new LinkedList<>();
        isVisited = new boolean[V + 1];
        int vertexOfMaxDist = getDistance(1)[0];
        // vertexOfMaxDist로 부터 가장 멀리 있는 정점까지의 거리 -> 트리의 지름
        bw.write(Integer.toString(getDistance(vertexOfMaxDist)[1]));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int[] getDistance(int start) {
        int[] result = new int[2];
        queue.clear();
        Arrays.fill(isVisited, false);
        queue.offer(new int[]{start, 0});
        isVisited[start] = true;
        while (!queue.isEmpty()) {
            int[] connectInfo = queue.poll();
            int number = connectInfo[0];
            int distance = connectInfo[1];
            if (distance > result[1]) {
                result[0] = number;
                result[1] = distance;
            }
            for (Edge edge : edgeList.get(number)) {
                int nextNumber = edge.number;
                if (isVisited[nextNumber]) {
                    continue;
                }
                isVisited[nextNumber] = true;
                queue.offer(new int[]{nextNumber, distance + edge.distance});
            }
        }
        return result;
    }
}
