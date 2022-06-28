package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_6497_전력난 {

    public static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

    }

    public static void makeSet(int[] parents, int n) {
        for (int i = 0; i < n; i++)
            parents[i] = i;
    }

    public static int findSet(int[] parents, int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents, parents[a]);
    }

    public static boolean union(int[] parents, int a, int b) {
        int aRoot = findSet(parents, a);
        int bRoot = findSet(parents, b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            ArrayList<Edge> edgeList = new ArrayList<>();
            int totalCost = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                totalCost += weight;
                edgeList.add(new Edge(from, to, weight));
            }

            int parents[] = new int[n];
            makeSet(parents, n);
            Collections.sort(edgeList);
            int selectedCost = 0;
            int count = 0;
            for (Edge edge : edgeList) {
                if (union(parents, edge.from, edge.to)) {
                    selectedCost += edge.weight;
                    if (++count == n - 1) break;
                }
            }
            sb.append(totalCost - selectedCost).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}
