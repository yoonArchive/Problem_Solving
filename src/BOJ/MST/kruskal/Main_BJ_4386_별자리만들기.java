package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_4386_별자리만들기 {
    public static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public static void makeSet(int parents[], int n) {
        for (int i = 0; i < n; i++)
            parents[i] = i;
    }

    public static int findSet(int parents[], int n) {
        if (parents[n] == n) return n;
        return parents[n] = findSet(parents, parents[n]);
    }

    public static boolean union(int parents[], int a, int b) {
        int aRoot = findSet(parents, a);
        int bRoot = findSet(parents, b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double loc[][] = new double[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            loc[i][0] = Double.parseDouble(st.nextToken());
            loc[i][1] = Double.parseDouble(st.nextToken());
        }
        ArrayList<Edge> edgeList = new ArrayList<>(); // 간선리스트 생성
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double weight = Math.sqrt(Math.pow(loc[i][0] - loc[j][0], 2) + Math.pow(loc[i][1] - loc[j][1], 2));
                edgeList.add(new Edge(i, j, weight));
            }
        }

        int parents[] = new int[n];
        makeSet(parents, n);
        Collections.sort(edgeList);

        double total = 0.0;
        int count = 0;
        for (Edge e : edgeList) {
            if (union(parents, e.from, e.to)) {
                total += e.weight;
                if (++count == n - 1) break;
            }
        }

        System.out.printf("%.2f", total);
        br.close();

    }

}
