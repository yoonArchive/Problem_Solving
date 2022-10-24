package BOJ.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_1068_트리 {

    public static List<List<Integer>> tree;
    public static boolean[] isVisited;
    public static int leafCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0, parent = 0;
        for (int currentNode = 0; currentNode < N; currentNode++) {
            parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = currentNode;
            } else {
                tree.get(parent).add(currentNode);
            }
        }
        int removeNode = Integer.parseInt(br.readLine());
        isVisited[removeNode] = true;
        if (removeNode != root) {
            moveToChildNode(root);
        }
        bw.write(Integer.toString(leafCount));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void moveToChildNode(int node) {
        if (tree.get(node).size() == 0) {
            leafCount++;
            return;
        }
        isVisited[node] = true;
        for (int nextNode : tree.get(node)) {
            if (!isVisited[nextNode]) {
                moveToChildNode(nextNode);
            } else {
                if (tree.get(node).size() == 1) {
                    leafCount++;
                }
            }
        }
    }
}
