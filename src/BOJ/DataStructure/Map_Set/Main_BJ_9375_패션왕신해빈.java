package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BJ_9375_패션왕신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> costume = new HashMap<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String type = st.nextToken();
                costume.put(type, costume.getOrDefault(type, 0) + 1);
            }
            int cases = 1;
            for (Map.Entry<String, Integer> entry : costume.entrySet()) {
                int count = entry.getValue();
                cases *= (count + 1);
            }
            sb.append(cases - 1).append("\n");
        }
        System.out.println(sb);
    }
}
