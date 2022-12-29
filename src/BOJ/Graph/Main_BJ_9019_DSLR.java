package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9019_DSLR {

    public static int A, B;
    public static Queue<Register> queue;
    public static boolean[] isVisited;

    public static class Register {
        int n;
        StringBuilder command;

        public Register(int n, StringBuilder command) {
            this.n = n;
            this.command = command;
        }
    }

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
        queue.offer(new Register(A, new StringBuilder()));
        isVisited[A] = true;
        int number;
        while (!queue.isEmpty()) {
            Register register = queue.poll();
            int n = register.n;
            StringBuilder currentCommand = register.command;
            if (n == B) {
                return currentCommand;
            }
            number = D(n);
            offerQueue(number, currentCommand, "D");
            number = S(n);
            offerQueue(number, currentCommand, "S");
            number = L(n);
            offerQueue(number, currentCommand, "L");
            number = R(n);
            offerQueue(number, currentCommand, "R");
        }
        return null;
    }

    private static void offerQueue(int number, StringBuilder sb, String command) {
        if (!isVisited[number]) {
            queue.offer(new Register(number, new StringBuilder(sb).append(command)));
            isVisited[number] = true;
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
}
