package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9019_DSLR {

    public static int A, B;
    public static Queue<Integer> queue;
    public static boolean[] isVisited;
    public static int[] parent;
    public static char[] commands;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append(makeAtoB()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static StringBuilder makeAtoB() {
        queue = new LinkedList<>();
        isVisited = new boolean[10000];
        parent = new int[10000];
        commands = new char[10000];
        queue.offer(A);
        isVisited[A] = true;
        int number;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (n == B) {
                return print();
            }
            number = D(n);
            offerQueue(number, n, 'D');
            number = S(n);
            offerQueue(number, n, 'S');
            number = L(n);
            offerQueue(number, n, 'L');
            number = R(n);
            offerQueue(number, n, 'R');
        }
        return null;
    }

    private static void offerQueue(int number, int parentNumber, char command) {
        if (!isVisited[number]) {
            queue.offer(number);
            isVisited[number] = true;
            parent[number] = parentNumber;
            commands[number] = command;
        }
    }

    private static int D(int n) {
        return n * 2 % 10000;
    }

    private static int S(int n) {
        return n == 0 ? 9999 : n - 1;
    }

    private static int L(int n) {
        return n % 1000 * 10 + n / 1000;
    }

    private static int R(int n) {
        return n / 10 + n % 10 * 1000;
    }

    private static StringBuilder print() {
        StringBuilder result = new StringBuilder();
        int tmp = B;
        while (tmp != A) {
            result.append(commands[tmp]);
            tmp = parent[tmp];
        }
        return result.reverse();
    }
}
