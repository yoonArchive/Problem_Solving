package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BJ_5430_AC {

    public static final char DELETE = 'D';
    public static final char REVERSE = 'R';
    public static final char OPENING_BRACKET = '[';
    public static final char CLOSING_BRACKET = ']';
    public static final String BRACKETS = "[]";
    public static final String REST = ",";
    public static final String ERROR = "error";


    public static int n;
    public static String[] numbers;
    public static Deque<Integer> deque;
    public static boolean isRight;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String functions = br.readLine();
            n = Integer.parseInt(br.readLine());
            putNumberIntoDeque(new StringTokenizer(br.readLine(), "[],"));
            char function;
            isRight = true;
            boolean existError = false;
            outer:
            for (int i = 0; i < functions.length(); i++) {
                function = functions.charAt(i);
                if (function == DELETE) {
                    if (deque.isEmpty()) {
                        sb.append(ERROR).append("\n");
                        existError = true;
                        break outer;
                    }
                    deleteFirstNumber();
                } else if (function == REVERSE) {
                    reverseArray();
                }
            }
            if (!existError) {
                if (deque.isEmpty()) {
                    sb.append(BRACKETS).append("\n");
                } else {
                    print();
                }
            }
        }
        System.out.println(sb);
        br.close();
    }

    private static void putNumberIntoDeque(StringTokenizer st) {
        deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            deque.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void deleteFirstNumber() {
        if (isRight) {
            deque.pollFirst();
        } else {
            deque.pollLast();
        }
    }

    private static void reverseArray() {
        isRight = !isRight;
    }

    private static void print() {
        sb.append(OPENING_BRACKET);
        if (isRight) {
            sb.append(deque.pollFirst());
            while (!deque.isEmpty()) {
                sb.append(REST).append(deque.pollFirst());
            }
        } else {
            sb.append(deque.pollLast());
            while (!deque.isEmpty()) {
                sb.append(REST).append(deque.pollLast());
            }
        }
        sb.append(CLOSING_BRACKET).append("\n");
    }
}
