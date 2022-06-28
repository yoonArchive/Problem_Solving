package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_1874_스택수열 {
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int numbers[] = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        push(1);

        int index = 0;
        int addNum = 2;
        while (index < n) {
            if (stack.isEmpty() || stack.peek() < numbers[index]) {
                while (addNum <= numbers[index]) {
                    push(addNum++);
                }
            } else if (stack.peek() == numbers[index]) {
                pop();
                index++;
            } else {
                sb.setLength(0);
                sb.append("NO");
                break;
            }
        }
        System.out.println(sb.toString());
    }

    public static void push(int number) {
        stack.add(number);
        sb.append("+").append("\n");
    }

    public static void pop() {
        stack.pop();
        sb.append("-").append("\n");
    }

}
