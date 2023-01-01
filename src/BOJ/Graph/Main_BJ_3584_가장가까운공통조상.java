package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_3584_가장가까운공통조상 {

    public static int[] parents;
    public static List<Set<Integer>> childs;
    public static boolean[] isVisited;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            parents = new int[N + 1];
            isVisited = new boolean[N + 1];
            childs = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                childs.add(new HashSet());
            }
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[child] = parent;
                childs.get(parent).add(child);
            }
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            sb.append(getCommonAncestor(node1, node2)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int getCommonAncestor(int node1, int node2) {
        isVisited[node1] = true;
        boolean isChild = searchChildNode(node1, node2);
        if (!isChild) {
            return parents[node1] == 0 ? node1 : getCommonAncestor(parents[node1], node2);
        } else {
            return node1;
        }
    }

    private static boolean searchChildNode(int parent, int findNode) {
        if (childs.get(parent).contains(findNode)) {
            return true;
        }
        boolean findFlag = false;
        isVisited[parent] = true;
        for (int child : childs.get(parent)) {
            if (isVisited[child]) {
                continue;
            }
            findFlag = searchChildNode(child, findNode);
            if (findFlag) {
                break;
            }
        }
        return findFlag;
    }
}
