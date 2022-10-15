package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1325_효율적인해킹 {

    public static int maxCount = 0;
    public static int N;
    public static List<List<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        int a = 0, b = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            list.get(b).add(a);
        }
        int[] hackedCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (list.get(i).size() == 0) {
                continue;
            }
            int count = hack(i);
            hackedCount[i] = count;
            maxCount = Math.max(count, maxCount);
        }
        for (int i = 1; i <= N; i++) {
            if (hackedCount[i] == maxCount) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private static int hack(int hackedNo) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isHacked = new boolean[N + 1];
        queue.offer(hackedNo);
        isHacked[hackedNo] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int computerNo = queue.poll();
            for (int computer : list.get(computerNo)) {
                if (!isHacked[computer]) {
                    queue.offer(computer);
                    isHacked[computer] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
