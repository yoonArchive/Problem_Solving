package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 문자열
        String input = br.readLine();
        int length = input.length();

        // 폭발 문자열
        String bomb = br.readLine();
        String reverse = new StringBuilder(bomb).reverse().toString();
        int bombLength = bomb.length();

        Stack<Character> popStack = new Stack<>();
        Stack<Character> pushStack = new Stack<>();
        for (int i = 0; i < length; i++) // 문자열 앞에서부터 스택에 push
            popStack.push(input.charAt(i));

        StringBuilder baseSb = new StringBuilder(); // 폭발 문자열인지 검사하기 위한 StringBuilder
        for (int i = 0; i < bombLength; i++)
            baseSb.append('*');

        while (!popStack.isEmpty()) {
            char c = popStack.pop(); // popStack에서 pop한 문자를
            pushStack.push(c); // pushStack에 push
            baseSb.deleteCharAt(0);
            baseSb.append(c);
            if (baseSb.toString().equals(reverse)) { // 폭발 문자열 발견
                for (int i = 0; i < bombLength; i++)
                    pushStack.pop(); // 폭발 문자열 길이만큼 pushStack pop
                int stSize = pushStack.size();
                if (stSize >= bombLength) { // pushStack size가 폭발 문자열 길이보다 크다면
                    for (int i = 0; i < bombLength - 1; i++) {
                        popStack.push(pushStack.pop()); // 폭발 문자열 길이 -1 만큼 pop하여 popStack에 넣고 위의 과정 다시 진행
                    }
                } else { // pushStack size가 폭발 문자열 길이보다 작다면
                    while (!pushStack.isEmpty())
                        popStack.push(pushStack.pop()); // pushStack에 있는 값 모두 popStack에 push하고 위의 과정 다시 진행
                }
            }
        }

        while (!pushStack.isEmpty())
            sb.append(pushStack.pop());

        if (sb.length() == 0)
            sb.append("FRULA");

        System.out.println(sb.toString());
        br.close();
    }
}
