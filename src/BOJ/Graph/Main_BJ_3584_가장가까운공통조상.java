package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_3584_가장가까운공통조상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parents = new int[N + 1];
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[child] = parent;
            }
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            boolean[] isParent = new boolean[N + 1];
            while (node1 != 0) {
                isParent[node1] = true;
                node1 = parents[node1];
            }
            while (true) {
                if (isParent[node2]) {
                    sb.append(node2).append("\n");
                    break;
                }
                node2 = parents[node2];
            }
        }
        System.out.println(sb);
        br.close();
    }
}
