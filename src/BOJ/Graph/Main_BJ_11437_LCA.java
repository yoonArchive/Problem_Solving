package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_11437_LCA {

    public static final int ROOT = 1;

    public static int N;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> connectMap = new HashMap<>();
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            connectMap.put(i, new HashSet<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            connectMap.get(vertex1).add(vertex2);
            connectMap.get(vertex2).add(vertex1);
        }
        parents = new int[N + 1];
        Queue<Integer> parentQueue = new LinkedList<>();
        parentQueue.offer(ROOT);
        while (!parentQueue.isEmpty()) {
            int parent = parentQueue.poll();
            for (int child : connectMap.get(parent)) {
                if (parents[child] != 0) {
                    continue;
                }
                parents[child] = parent;
                parentQueue.offer(child);
            }
        }
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            sb.append(getCommonAncestor(vertex1, vertex2)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int getCommonAncestor(int vertex1, int vertex2) {
        boolean[] isParent = new boolean[N + 1];
        while (vertex1 != ROOT) {
            isParent[vertex1] = true;
            vertex1 = parents[vertex1];
        }
        while (vertex2 != ROOT) {
            if (isParent[vertex2]) {
                return vertex2;
            }
            vertex2 = parents[vertex2];
        }
        return ROOT;
    }
}
