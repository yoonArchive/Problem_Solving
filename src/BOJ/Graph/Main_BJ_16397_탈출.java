package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16397_탈출 {

    public static final int MAX_NUMBER = 99999;

    public static int T, G;

    public static class PressInfo {
        int number;
        int count;

        public PressInfo(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        int result = escape(N);
        bw.write(result == -1 ? "ANG" : Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int escape(int start) {
        Queue<PressInfo> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[MAX_NUMBER + 1];
        queue.offer(new PressInfo(start, 0));
        isVisited[start] = true;
        while (!queue.isEmpty()) {
            PressInfo pressInfo = queue.poll();
            int number = pressInfo.number;
            int count = pressInfo.count;
            if (number == G) {
                return count;
            }
            int nextNumber = pressButtonA(number);
            count++;
            offer(queue, isVisited, count, nextNumber);
            nextNumber = pressButtonB(number);
            offer(queue, isVisited, count, nextNumber);
        }
        return -1;
    }

    private static void offer(Queue<PressInfo> queue, boolean[] isVisited, int count, int nextNumber) {
        if (isValidate(nextNumber, count) && !isVisited[nextNumber]) {
            queue.offer(new PressInfo(nextNumber, count));
            isVisited[nextNumber] = true;
        }
    }

    private static int pressButtonA(int number) {
        return number + 1;
    }

    private static int pressButtonB(int number) {
        if (number != 0) {
            number *= 2;
            if (number > MAX_NUMBER) {
                return -1;
            }
            number -= Math.pow(10, Integer.toString(number).length() - 1);
        }
        return number;
    }

    private static boolean isValidate(int number, int count) {
        return number >= 0 && number <= MAX_NUMBER && count <= T;
    }
}
