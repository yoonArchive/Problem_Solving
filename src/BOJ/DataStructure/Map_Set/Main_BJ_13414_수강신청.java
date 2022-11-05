package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BJ_13414_수강신청 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> enrollMap = new HashMap<>();
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int order = 0;
        while (L-- > 0) {
            enrollMap.put(br.readLine(), order++);
        }
        String[] orders = new String[order];
        for (Map.Entry entry : enrollMap.entrySet()) {
            orders[(int) entry.getValue()] = (String) entry.getKey();
        }
        int count = 0;
        for (int i = 0, length = orders.length; i < length; i++) {
            if (orders[i] == null) {
                continue;
            }
            sb.append(orders[i]).append("\n");
            if (++count == K) {
                break;
            }
        }
        System.out.println(sb);
    }
}
