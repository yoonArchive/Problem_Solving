package BOJ.DataStructure.Stack;

import java.io.*;
import java.util.Stack;

public class Main_BJ_1662_압축 {

    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSING_PARENTHESIS = ')';

    public static class Element {
        char character;
        int length;
        boolean isInput;

        public Element(char character) {
            this.character = character;
            this.isInput = true;
        }

        public Element(int length) {
            this.length = length;
            this.isInput = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine();
        Stack<Element> stack = new Stack<>();
        for (int i = 0, len = S.length(); i < len; i++) {
            char current = S.charAt(i);
            if (current == CLOSING_PARENTHESIS) {
                int currentLength = 0;
                while (true) {
                    Element popped = stack.pop();
                    if (popped.character == OPEN_PARENTHESIS) {
                        stack.push(new Element(currentLength * (stack.pop().character - '0')));
                        break;
                    } else if (popped.isInput) {
                        currentLength++;
                    } else {
                        currentLength += popped.length;
                    }
                }
            } else {
                stack.push(new Element(current));
            }
        }
        int length = 0;
        while (!stack.isEmpty()) {
            Element popped = stack.pop();
            length += popped.isInput ? 1 : popped.length;
        }
        bw.write(Integer.toString(length));
        br.close();
        bw.flush();
        bw.close();
    }
}
