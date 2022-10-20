package BOJ.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_9934_완전이진트리 {

    public static int K;
    public static int[] order;
    public static List<List<Integer>> buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, K) - 1;
        order = new int[size];
        buildings = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            buildings.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int index = 0;
        while (st.hasMoreTokens()) {
            order[index++] = Integer.parseInt(st.nextToken());
        }
        int rootIndex = order.length / 2;
        int gap = rootIndex / 2 + 1;
        buildings.get(0).add(order[rootIndex]);
        makeTree(rootIndex, gap, 1);
        int treeLevel = -1;
        while (++treeLevel < K) {
            for (int building : buildings.get(treeLevel)) {
                sb.append(building).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void makeTree(int rootIndex, int gap, int level) {
        if (level >= K) {
            return;
        }
        buildings.get(level).add(order[rootIndex - gap]);
        buildings.get(level).add(order[rootIndex + gap]);
        makeTree(rootIndex - gap, gap / 2, level + 1);
        makeTree(rootIndex + gap, gap / 2, level + 1);
    }
}
