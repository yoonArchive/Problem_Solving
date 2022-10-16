package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_5014_스타트링크 {

    public static final String UNABLE_TO_GO = "use the stairs";

    public static int floors, startFloor, goal, upButton, downButton;

    public static class MoveInfo implements Comparable<MoveInfo> {
        int floor;
        int count;

        public MoveInfo(int floor, int count) {
            this.floor = floor;
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
        floors = Integer.parseInt(st.nextToken());
        startFloor = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());
        upButton = Integer.parseInt(st.nextToken());
        downButton = Integer.parseInt(st.nextToken());
        int minCount = goStartLink();
        if (minCount == -1) {
            bw.write(UNABLE_TO_GO);
        } else {
            bw.write(Integer.toString(minCount));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static int goStartLink() {
        PriorityQueue<MoveInfo> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[floors + 1];
        pq.offer(new MoveInfo(startFloor, 0));
        isVisited[startFloor] = true;
        while (!pq.isEmpty()) {
            MoveInfo moveInfo = pq.poll();
            int floor = moveInfo.floor;
            int count = moveInfo.count;
            if (floor == goal) {
                return count;
            }
            int nextFloor = floor + upButton;
            if (nextFloor <= floors && !isVisited[nextFloor]) {
                pq.offer(new MoveInfo(nextFloor, count + 1));
                isVisited[nextFloor] = true;
            }
            nextFloor = floor - downButton;
            if (nextFloor >= 1 && !isVisited[nextFloor]) {
                pq.offer(new MoveInfo(nextFloor, count + 1));
                isVisited[nextFloor] = true;
            }
        }
        return -1;
    }
}
