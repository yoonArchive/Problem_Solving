package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_16928_뱀과사다리게임 {

    public static final int SIZE = 101;
    public static final int GOAL = 100;

    public static int ladders, snakes;
    public static int[] obstacles;

    public static class MoveInfo implements Comparable<MoveInfo> {
        int number;
        int count;

        public MoveInfo(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int compareTo(MoveInfo o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ladders = Integer.parseInt(st.nextToken());
        snakes = Integer.parseInt(st.nextToken());
        obstacles = new int[SIZE];
        for (int i = 0, size = ladders + snakes; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            obstacles[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        bw.write(Integer.toString(rollDice(1)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int rollDice(int start) {
        PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
        boolean isVisited[] = new boolean[SIZE];
        pq.offer(new MoveInfo(start, 0));
        isVisited[start] = true;
        int nextNumber;
        while (!pq.isEmpty()) {
            MoveInfo moveInfo = pq.poll();
            int number = moveInfo.number;
            int count = moveInfo.count;
            System.out.println(number + " " + count);
            if (number == GOAL) {
                return count;
            }
            if (obstacles[number] != 0) {
                nextNumber = obstacles[number];
                pq.offer(new MoveInfo(nextNumber, count));
            } else {
                for (int diceNumber = 1; diceNumber <= 6; diceNumber++) {
                    nextNumber = number + diceNumber;
                    if (nextNumber > GOAL || isVisited[nextNumber]) {
                        continue;
                    }
                    pq.offer(new MoveInfo(nextNumber, count + 1));
                    isVisited[nextNumber] = true;
                }
            }
        }
        return -1;
    }
}
