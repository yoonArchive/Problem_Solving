package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_9370_미확인도착지 {

    public static int n, m, t, s, g, h, a, b, d;
    public static List<List<Node>> adjList;
    public static boolean[] isDestination;

    public static class Node implements Comparable<Node> {
        int to;
        int distance;
        boolean isPassed;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        public Node(int distance, boolean isPassed) {
            this.distance = distance;
            this.isPassed = isPassed;
        }

        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            adjList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                adjList.get(a).add(new Node(b, d));
                adjList.get(b).add(new Node(a, d));
            }
            isDestination = new boolean[n + 1];
            for (int i = 0; i < t; i++) {
                isDestination[Integer.parseInt(br.readLine())] = true;
            }
            Set<Integer> destinations = new TreeSet<>();
            move(destinations);
            for (int destination : destinations) {
                sb.append(destination).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void move(Set<Integer> destinations) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[] nodeInfos = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodeInfos[i] = new Node(Integer.MAX_VALUE, false);
        }
        boolean[] isVisited = new boolean[n + 1];
        pq.offer(new Node(s, 0));
        nodeInfos[s].distance = 0;
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.to;
            isVisited[current] = true;
            if (isDestination[current] && nodeInfos[current].isPassed) {
                destinations.add(current);
            }
            for (Node nextNode : adjList.get(current)) {
                int next = nextNode.to;
                int nDistance = nextNode.distance + nodeInfos[current].distance;
                if (!isVisited[next] && nodeInfos[next].distance >= nDistance) {
                    if (nodeInfos[next].isPassed && nodeInfos[next].distance == nDistance) {
                        continue;
                    }
                    nodeInfos[next].distance = nDistance;
                    nodeInfos[next].isPassed = nodeInfos[current].isPassed || (current == g && next == h) || (current == h && next == g);
                    pq.offer(new Node(next, nodeInfos[next].distance));
                }
            }
        }
    }
}
