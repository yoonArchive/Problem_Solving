package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_1406_에디터 {

    // 스택 2개(커서의 왼쪽 문자열을 담는 스택과, 오른쪽 문자열을 담는 스택)를 활용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();

        Stack<Character> leftStack = new Stack<>(); // 커서의 왼쪽
        Stack<Character> rightStack = new Stack<>(); // 커서의 오른쪽

        int length = input.length;
        for (int i = 0; i < length; i++) {
            leftStack.push(input[i]); // 초기 입력 문자열을 커서 왼쪽 스택에 넣음
        }

        int num = Integer.parseInt(br.readLine());// 명령어 개수
        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            switch (command) {
                case "L": // 커서를 왼쪽으로 한 칸 옮김 -> 왼쪽 스택의 top을 꺼내 오른쪽 스택으로 push
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;

                case "D": // 커서를 오른쪽으로 한 칸 옮김 -> 오른쪽 스택의 top을 꺼내 왼쪽 스택으로 push
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;

                case "B": // 커서 왼쪽에 있는 문자 삭제
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
                case "P":
                    leftStack.push(st.nextToken().charAt(0));
                    break;
            }
        }

        // 시간초과 주의!! StringBuilder의 reverse 쓰면 시간초과..
        while (!leftStack.isEmpty())
            rightStack.push(leftStack.pop());
        while (!rightStack.isEmpty())
            sb.append(rightStack.pop());

        System.out.print(sb.toString());
    }

}
