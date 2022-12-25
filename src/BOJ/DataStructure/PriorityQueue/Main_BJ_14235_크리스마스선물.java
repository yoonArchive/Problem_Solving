package BOJ.DataStructure.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14235_크리스마스선물 {

    public static final String NEW_LINE = "\n";
    public static final int EMPTY = -1;
    public static final int CHILD = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue gifts = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int current = Integer.parseInt(st.nextToken());
            if (current == CHILD) {
                sb.append(gifts.isEmpty() ? EMPTY : gifts.poll()).append(NEW_LINE);
            } else {
                while (st.hasMoreTokens()) {
                    gifts.offer(Integer.parseInt(st.nextToken()));
                }
            }
        }
        System.out.println(sb);
        br.close();
    }
}
