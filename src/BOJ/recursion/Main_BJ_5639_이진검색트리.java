package BOJ.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_5639_이진검색트리 {

    public static StringBuilder sb;

    public static class Node {
        int number;
        Node left, right;

        public Node(int number, Node left, Node right) {
            this.number = number;
            this.left = left;
            this.right = right;
        }

        public void insert(int number) {
            if (number < this.number) {
                if (this.left == null) {
                    this.left = new Node(number, null, null);
                } else {
                    this.left.insert(number);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(number, null, null);
                } else {
                    this.right.insert(number);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Node root = new Node(Integer.parseInt(br.readLine()), null, null);
        String number;
        while ((number = br.readLine()) != null && !number.isEmpty()) {
            root.insert(Integer.parseInt(number));
        }
        postOrder(root);
        System.out.println(sb);
        br.close();
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.number).append("\n");
    }
}
