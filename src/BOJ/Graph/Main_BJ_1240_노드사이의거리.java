package BOJ.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_1240_노드사이의거리 {

    private static List<Node>[] connects;
    private static int result;

    public static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        connects = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            connects[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            connects[vertex1].add(new Node(vertex2, distance));
            connects[vertex2].add(new Node(vertex1, distance));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -1, 0);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void dfs(int start, int destination, int pre, int distance) {
        if (start == destination) {
            result = distance;
        }
        for (Node nextNode : connects[destination]) {
            if (nextNode.vertex != pre) {
                dfs(start, nextNode.vertex, destination, distance + nextNode.distance);
            }
        }
    }
}