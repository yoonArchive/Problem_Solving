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
        int d1, d2, d3, d4;
        StringBuilder command;

        public Register(int number, StringBuilder command) {
            this.d1 = number / 1000;
            number -= (this.d1 * 1000);
            this.d2 = number / 100;
            number -= (this.d2 * 100);
            this.d3 = number / 10;
            number -= (this.d3 * 10);
            this.d4 = number;
            this.command = command;
        }

        public int makeN() {
            return this.d1 * 1000 + this.d2 * 100 + this.d3 * 10 + this.d4;
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
            int n = register.makeN();
            StringBuilder currentCommand = register.command;
            if (n == B) {
                return currentCommand;
            }
            number = D(n);
            offerQueue(number, currentCommand, "D");
            number = S(n);
            offerQueue(number, currentCommand, "S");
            number = L(register);
            offerQueue(number, currentCommand, "L");
            number = R(register);
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

    private static int L(Register r) {
        return r.d2 * 1000 + r.d3 * 100 + r.d4 * 10 + r.d1;
    }

    private static int R(Register r) {
        return r.d4 * 1000 + r.d1 * 100 + r.d2 * 10 + r.d3;
    }
}
