package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1707_이분그래프 {

    public static final int BEFORE_GROUPING = 0;
    public static final int GROUP1 = 1;
    public static final int GROUP2 = 2;

    public static StringBuilder sb;
    public static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        queue = new LinkedList<>();
        StringTokenizer st = null;
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<List<Integer>> adjList = new ArrayList<>();
            int[] group = new int[V + 1];
            for (int i = 0; i <= V; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }
            validate(adjList, group, V);
            queue.clear();
        }
        System.out.println(sb);
    }

    private static void validate(List<List<Integer>> adjList, int[] group, int V) {
        for (int i = 1; i <= V; i++) {
            if (group[i] == BEFORE_GROUPING) {
                queue.offer(i);
                group[i] = GROUP1;
            }
            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                for (int adjVertex : adjList.get(currentVertex)) {
                    if (group[currentVertex] == group[adjVertex]) {
                        sb.append("NO").append("\n");
                        return;
                    }
                    if (group[adjVertex] == BEFORE_GROUPING) {
                        queue.offer(adjVertex);
                        group[adjVertex] = group[currentVertex] == GROUP1 ? GROUP2 : GROUP1;
                    }
                }
            }
        }
        sb.append("YES").append("\n");
    }
}
