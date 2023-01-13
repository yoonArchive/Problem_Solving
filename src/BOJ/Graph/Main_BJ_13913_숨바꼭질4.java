package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13913_숨바꼭질4 {

    public static final int SIZE = 100001;

    public static int[] beforeLocation;

    public static class MoveInfo {
        int location;
        int count;

        public MoveInfo(int location, int count) {
            this.location = location;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());
        beforeLocation = new int[SIZE];
        sb.append(findSister(subin, sister)).append("\n");
        StringBuilder pathSb = new StringBuilder();
        getPath(subin, sister, pathSb);
        sb.append(pathSb);
        System.out.println(sb);
        br.close();
    }

    private static int findSister(int subin, int sister) {
        Queue<MoveInfo> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[SIZE];
        queue.offer(new MoveInfo(subin, 0));
        isVisited[subin] = true;
        while (!queue.isEmpty()) {
            MoveInfo moveInfo = queue.poll();
            int location = moveInfo.location;
            int count = moveInfo.count;
            if (location == sister) {
                return count;
            }
            for (int i = 0; i < 3; i++) {
                int nextLocation = move(i, location);
                if (!isIn(nextLocation) || isVisited[nextLocation]) {
                    continue;
                }
                beforeLocation[nextLocation] = location;
                queue.offer(new MoveInfo(nextLocation, count + 1));
                isVisited[nextLocation] = true;
            }
        }
        return -1;
    }

    private static int move(int i, int location) {
        return i == 0 ? location + 1 : i == 1 ? location - 1 : 2 * location;
    }

    private static void getPath(int subin, int sister, StringBuilder sb) {
        int current = sister;
        while (true) {
            sb.insert(0, current + " ");
            if (current == subin) {
                break;
            }
            current = beforeLocation[current];
        }
    }

    private static boolean isIn(int location) {
        return location >= 0 && location < SIZE;
    }
}
