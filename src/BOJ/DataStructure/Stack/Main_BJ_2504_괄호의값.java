package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_BJ_2504_괄호의값 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] input = br.readLine().toCharArray();
        Stack<String> stack = new Stack<>();
        boolean isCorrect = true;
        String top;
        for (int i = 0; i < input.length; i++) {
            String cur = Character.toString(input[i]);
            switch (cur) {
                case "(":
                    stack.push(cur);
                    break;

                case "[":
                    stack.push(cur);
                    break;

                case ")":
                    if (stack.isEmpty()) {
                        isCorrect = false;
                        break;
                    }
                    top = stack.peek();
                    if (top.equals("[") || top.equals("]") || top.equals(")")) {
                        isCorrect = false;
                        break; // 잘못된 입력
                    } else if (top.equals("(")) {
                        stack.pop();
                        stack.push("2");
                    } else { // 숫자
                        long sum = 0;
                        while (true) {
                            if (stack.size() == 0) {
                                isCorrect = false;
                                break;
                            }
                            if (stack.peek().equals("[") || stack.peek().equals("]") || stack.peek().equals(")")) {
                                isCorrect = false;
                                break; // 잘못된 입력
                            }
                            if (stack.peek().equals("(")) {
                                stack.pop();
                                break;
                            }
                            sum += Long.parseLong(stack.pop());
                        }
                        if (!isCorrect)
                            break;
                        else
                            stack.push(Long.toString(sum * 2));
                    }
                    break;

                case "]":
                    if (stack.isEmpty()) {
                        isCorrect = false;
                        break;
                    }
                    top = stack.peek();
                    if (top.equals("(") || top.equals(")") || top.equals("]")) {
                        isCorrect = false;
                        break; // 잘못된 입력
                    } else if (top.equals("[")) {
                        stack.pop();
                        stack.push("3");
                    } else { // 숫자
                        long sum = 0;
                        while (true) {
                            if (stack.size() == 0) {
                                isCorrect = false;
                                break;
                            }
                            if (stack.peek().equals("(") || stack.peek().equals(")") || stack.peek().equals("]")) {
                                isCorrect = false;
                                break; // 잘못된 입력
                            }
                            if (stack.peek().equals("[")) {
                                stack.pop();
                                break;
                            }
                            sum += Long.parseLong(stack.pop());
                        }
                        if (!isCorrect)
                            break;
                        else
                            stack.push(Long.toString(sum * 3));
                    }
                    break;
            }
        }

        long result = 0;
        if (isCorrect) {
            if (stack.peek().equals("(") || stack.peek().equals("["))
                result = 0;
            else {
                while (!stack.isEmpty()) {
                    if (stack.peek().equals("(") || stack.peek().equals("[")) {
                        result = 0;
                        break;
                    }
                    result += Long.parseLong(stack.pop());
                }
            }
        } else {
            result = 0;
        }

        bw.write(Long.toString(result));
        br.close();
        bw.flush();
        bw.close();

    }
}
