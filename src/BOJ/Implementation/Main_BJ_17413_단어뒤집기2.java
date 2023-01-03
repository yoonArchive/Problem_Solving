package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_BJ_17413_단어뒤집기2 {

    public static final char OPEN_TAG = '<';
    public static final char CLOSE_TAG = '>';
    public static final char EMPTY = ' ';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        Deque deque = new ArrayDeque();
        char current;
        boolean isInTag = false;
        for (int i = 0; i < line.length(); i++) {
            current = line.charAt(i);
            switch (current) {
                case OPEN_TAG:
                    isInTag = true;
                    printReverse(deque, sb);
                    deque.add(current);
                    break;
                case CLOSE_TAG:
                    deque.add(current);
                    print(deque, sb);
                    isInTag = false;
                    break;
                case EMPTY:
                    if (!isInTag) {
                        printReverse(deque, sb);
                        sb.append(EMPTY);
                    } else {
                        deque.add(EMPTY);
                    }
                    break;
                default:
                    deque.add(current);
            }
        }
        if (deque.size() != 0) {
            printReverse(deque, sb);
        }
        System.out.println(sb);
        br.close();
    }

    private static void print(Deque deque, StringBuilder sb) {
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
    }

    private static void printReverse(Deque deque, StringBuilder sb) {
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
    }
}
