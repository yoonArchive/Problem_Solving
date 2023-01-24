package BOJ.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_4803_트리 {

    public static final int ROOT = -1;
    public static final int CYCLE = -2;

    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = 0;
        while (true) {
            ++testcase;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            parents = new int[n + 1];
            Arrays.fill(parents, ROOT);
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int vertex1 = Integer.parseInt(st.nextToken());
                int vertex2 = Integer.parseInt(st.nextToken());
                union(vertex1, vertex2);
            }
            int trees = 0;
            for (int i = 1; i <= n; i++) {
                if (parents[i] == ROOT) {
                    trees++;
                }
            }
            sb.append("Case ").append(testcase).append(": ");
            if (trees == 0) {
                sb.append("No trees.");
            } else if (trees == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(trees).append(" trees.");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int findSet(int vertex) {
        if (parents[vertex] < 0) {
            return vertex;
        }
        return parents[vertex] = findSet(parents[vertex]);
    }

    private static void union(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b) { // 사이클 생김 -> 트리 x
            parents[a] = CYCLE;
        } else {
            if (parents[a] == CYCLE) {
                parents[b] = a;
            } else if (parents[b] == CYCLE) {
                parents[a] = b;
            } else if (parents[a] == ROOT) {
                parents[b] = a;
            } else {
                parents[a] = b;
            }
        }
    }
}
