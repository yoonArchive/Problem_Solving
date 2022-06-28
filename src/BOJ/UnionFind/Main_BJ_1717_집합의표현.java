package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1717_집합의표현 {
    public static void makeSet(int[] parents, int n) {
        for (int i = 1; i <= n; i++)
            parents[i] = i;
    }

    public static int findSet(int parents[], int n) {
        if (parents[n] == n)
            return n;
        return parents[n] = findSet(parents, parents[n]);
    }

    public static void union(int parents[], int a, int b) {
        int aRoot = findSet(parents, a);
        int bRoot = findSet(parents, b);
        if (aRoot == bRoot)
            return;
        parents[bRoot] = aRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int parents[] = new int[n + 1];
        makeSet(parents, n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 0)
                union(parents, a, b);
            else if (findSet(parents, a) == findSet(parents, b))
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}
