package BOJ.DataStructure.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BJ_1927_최소힙 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int num = 0;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            if (num > 0)
                pq.offer(num);
            else if (num == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                    continue;
                }
                num = pq.poll();
                sb.append(num).append("\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

}
