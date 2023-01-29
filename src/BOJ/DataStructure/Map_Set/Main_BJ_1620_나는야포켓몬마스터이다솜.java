package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BJ_1620_나는야포켓몬마스터이다솜 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, String> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            map1.put(i, name);
            map2.put(name, i);
        }
        for (int i = 0; i < M; i++) {
            String question = br.readLine();
            if (question.charAt(0) > '9') {
                sb.append(map2.get(question)).append("\n");
            } else {
                sb.append(map1.get(Integer.parseInt(question))).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}
