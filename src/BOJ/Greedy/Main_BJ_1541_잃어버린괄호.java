package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_BJ_1541_잃어버린괄호 {
    static int idx;
    static StringBuilder numSb = new StringBuilder();

    public static int getNum(String input) {
        numSb.setLength(0);
        int length = input.length();
        while (true) {
            numSb.append(input.charAt(idx));
            if (idx == length - 1 || input.charAt(idx + 1) == '-' || input.charAt(idx + 1) == '+') break;
            idx++;
        }
        return Integer.parseInt(numSb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        Stack<Integer> operand = new Stack<>();

        int length = input.length();
        idx = -1;
        while (++idx < length) {
            char cur = input.charAt(idx);
            if (cur == '-') {
                continue;
            } else if (cur == '+') {
                int pre = operand.pop();
                int next = getNum(input);
                operand.push(pre + next);
            } else {
                int curNum = getNum(input);
                operand.push(curNum);
            }
        }

        int sSize = operand.size();
        int popNum = 0;
        int result = 0;
        while (!operand.isEmpty()) {
            int num = ++popNum == sSize ? operand.pop() : operand.pop() * (-1);
            result += num;
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

}
