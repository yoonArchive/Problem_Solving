package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_12761_돌다리 {

    public static final int SIZE = 100000;

    public static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int[] count = new int[SIZE + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int result = 0;
        while (!queue.isEmpty()) {
            int currentLocation = queue.poll();
            if (currentLocation == goal) {
                result = count[currentLocation];
                break;
            }
            for (int i = 0; i < 8; i++) {
                int nextLocation = getNextLocation(i, currentLocation);
                if (!isIn(nextLocation) || count[nextLocation] != 0) {
                    continue;
                }
                queue.offer(nextLocation);
                count[nextLocation] = count[currentLocation] + 1;
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int getNextLocation(int i, int currentLocation) {
        switch (i) {
            case 0:
                return currentLocation + 1;
            case 1:
                return currentLocation - 1;
            case 2:
                return currentLocation + A;
            case 3:
                return currentLocation - A;
            case 4:
                return currentLocation + B;
            case 5:
                return currentLocation - B;
            case 6:
                return currentLocation * A;
            case 7:
                return currentLocation * B;
        }
        return -1;
    }

    private static boolean isIn(int location) {
        return location >= 0 && location <= SIZE;
    }
}
