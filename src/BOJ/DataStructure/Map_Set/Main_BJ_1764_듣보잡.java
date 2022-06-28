package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BJ_1764_듣보잡 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<String>();
        ArrayList<String> resultList = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (set.contains(name)) {
                resultList.add(name);
            }

        }
        Collections.sort(resultList);
        sb.append(resultList.size()).append("\n");
        for (int i = 0, length = resultList.size(); i < length; i++) {
            sb.append(resultList.get(i)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}