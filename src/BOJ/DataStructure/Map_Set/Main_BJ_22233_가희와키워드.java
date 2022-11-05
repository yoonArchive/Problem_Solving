package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_22233_가희와키워드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> memo = new HashSet<>();
        while (N-- > 0) {
            memo.add(br.readLine());
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()){
                String keyword = st.nextToken();
                if(memo.contains(keyword)){
                    memo.remove(keyword);
                }
            }
            sb.append(memo.size()).append("\n");
        }
        System.out.println(sb);
    }
}
