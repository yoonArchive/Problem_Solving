package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_1967_트리의지름 {

    public static final int ROOT = 1;

    public static List<List<Edge>> edgeList;
    public static Queue<int[]> queue;
    public static boolean[] isVisited;

    public static class Edge {
        int number;
        int weight;

        public Edge(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edgeList.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList.get(parent).add(new Edge(child, weight));
            edgeList.get(child).add(new Edge(parent, weight));
        }
        // root에서 가장 멀리 있는 노드의 "번호" 구하기
        queue = new LinkedList<>();
        isVisited = new boolean[n + 1];
        int maxDistNode = getDistance(ROOT)[0];
        // root에서 가장 멀리 있는 노드로부터 가장 멀리 있는 노드까지의 "거리" -> 트리의 지름
        int diameter = getDistance(maxDistNode)[1];
        bw.write(Integer.toString(diameter));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int[] getDistance(int start) {
        queue.clear();
        Arrays.fill(isVisited, false);
        queue.offer(new int[]{start, 0});
        isVisited[start] = true;
        int[] result = new int[2];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int parent = current[0];
            int distSum = current[1];
            if (distSum > result[1]) {
                result[0] = parent;
                result[1] = distSum;
            }
            for (Edge edge : edgeList.get(parent)) {
                int number = edge.number;
                if (isVisited[number]) {
                    continue;
                }
                isVisited[number] = true;
                queue.offer(new int[]{number, distSum + edge.weight});
            }
        }
        return result;
    }
}
