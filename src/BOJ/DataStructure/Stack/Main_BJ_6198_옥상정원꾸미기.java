package BOJ.DataStructure.Stack;

import java.io.*;
import java.util.Stack;

public class Main_BJ_6198_옥상정원꾸미기 {

    public static class Building {
        int number;
        int height;

        public Building(int number, int height) {
            this.number = number;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        Stack<Building> stack = new Stack<>();
        long count = 0;
        stack.push(new Building(N - 1, heights[N - 1]));
        for (int i = N - 2; i >= 0; i--) {
            Building top = stack.peek();
            if (top.height < heights[i]) {
                while (!stack.isEmpty()) {
                    if (stack.peek().height >= heights[i]) {
                        break;
                    }
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    count += (N - 1 - i);
                } else {
                    count += (stack.peek().number - i - 1);
                }
            }
            stack.push(new Building(i, heights[i]));
        }
        bw.write(Long.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
}
