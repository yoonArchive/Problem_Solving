package BOJ.MST.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_2887_행성터널 {

    public static class Planet {
        int num, x, y, z;

        public Planet(int num, int x, int y, int z) {
            super();
            this.num = num;
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    public static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            super();
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

    }

    public static void makeSet(int[] parents, int N) {
        for (int i = 0; i < N; i++)
            parents[i] = i;
    }

    public static int findSet(int[] parents, int n) {
        if (parents[n] == n) return n;
        return parents[n] = findSet(parents, parents[n]);
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Planet kingdom[] = new Planet[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            kingdom[i] = new Planet(i, x, y, z);
        }

        ArrayList<Edge> edgeList = new ArrayList<>();
        Arrays.sort(kingdom, (k1, k2) -> k1.x - k2.x);
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(kingdom[i].x - kingdom[i + 1].x);
            edgeList.add(new Edge(kingdom[i].num, kingdom[i + 1].num, cost));
        }

        Arrays.sort(kingdom, (k1, k2) -> k1.y - k2.y);
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(kingdom[i].y - kingdom[i + 1].y);
            edgeList.add(new Edge(kingdom[i].num, kingdom[i + 1].num, cost));
        }

        Arrays.sort(kingdom, (k1, k2) -> k1.z - k2.z);
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(kingdom[i].z - kingdom[i + 1].z);
            edgeList.add(new Edge(kingdom[i].num, kingdom[i + 1].num, cost));
        }

        int parents[] = new int[N];
        makeSet(parents, N);
        Collections.sort(edgeList);

        long result = 0;
        int count = 0;
        for (Edge edge : edgeList) {
            if (union(parents, edge.from, edge.to)) {
                result += edge.cost;
                if (++count == N - 1) break;
            }
        }
        bw.write(Long.toString(result));
        br.close();
        bw.flush();
        bw.close();

    }

}
