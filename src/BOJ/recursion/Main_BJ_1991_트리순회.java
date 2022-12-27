package BOJ.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1991_트리순회 {

    public static final char EMPTY = '.';
    public static final String NEW_LINE = "\n";

    public static StringBuilder sb;

    public static class Node {
        char character;
        Node left, right;

        public Node(char character) {
            this.character = character;
        }

        public Node findNode(char character) {
            Node node = null;
            boolean findFlag = false;
            if (this.character == character) {
                node = this;
            } else {
                if (this.left != null) {
                    node = this.left.findNode(character);
                    if (node != null) {
                        findFlag = true;
                    }
                }
                if (!findFlag && this.right != null) {
                    node = this.right.findNode(character);
                }
            }
            return node;
        }

        public void insertLeftNode(char left) {
            this.left = new Node(left);
        }

        public void insertRightNode(char right) {
            this.right = new Node(right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Node root = new Node('A');
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char character = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            if (left != EMPTY) {
                root.findNode(character).insertLeftNode(left);
            }
            char right = st.nextToken().charAt(0);
            if (right != EMPTY) {
                root.findNode(character).insertRightNode(right);
            }
        }
        preOrder(root);
        sb.append(NEW_LINE);
        inOrder(root);
        sb.append(NEW_LINE);
        postOrder(root);
        System.out.println(sb);
        br.close();
    }

    private static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.character);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        sb.append(node.character);
        inOrder(node.right);
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.character);
    }
}
