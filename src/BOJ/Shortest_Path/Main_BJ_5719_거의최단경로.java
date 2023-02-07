package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_5719_거의최단경로 {

    public static int N, M, S, D;
    public static int[] distances;
    public static List<List<Node>> adjList, reverseAdjList;

    public static class Node implements Comparable<Node> {
        int to;
        int distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        public int compareTo(Node n) {
            return this.distance - n.distance;
        }

        public boolean equals(Object o) {
            Node n = (Node) o;
            return n.to == to && n.distance == distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            adjList = new LinkedList<>();
            reverseAdjList = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                adjList.add(new LinkedList<>());
                reverseAdjList.add(new LinkedList<>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());
                adjList.get(from).add(new Node(to, distance));
                reverseAdjList.get(to).add(new Node(from, distance));
            }
            distances = new int[N];
            getShortestPath();
            removeShortestPath();
            sb.append(getShortestPath()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int getShortestPath() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N];
        Arrays.fill(distances, Integer.MAX_VALUE);
        pq.offer(new Node(S, 0));
        distances[S] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int to = node.to;
            int distance = node.distance;
            if (isVisited[to]) {
                continue;
            }
            isVisited[to] = true;
            for (Node adjNode : adjList.get(to)) {
                int next = adjNode.to;
                int nDistance = distance + adjNode.distance;
                if (nDistance < distances[next]) {
                    distances[next] = nDistance;
                    pq.offer(new Node(next, nDistance));
                }
            }
        }
        return distances[D] == Integer.MAX_VALUE ? -1 : distances[D];
    }

    private static void removeShortestPath() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[N];
        queue.offer(D);
        isVisited[D] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node node : reverseAdjList.get(current)) {
                int prev = node.to;
                int distance = node.distance;
                if (distances[prev] + distance == distances[current]) {
                    if (!isVisited[prev]) {
                        queue.offer(prev);
                        isVisited[prev] = true;
                    }
                    adjList.get(prev).remove(new Node(current, distance));
                }
            }
        }
    }
}
