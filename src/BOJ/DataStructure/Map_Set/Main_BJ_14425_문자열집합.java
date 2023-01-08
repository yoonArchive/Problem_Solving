package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_14425_문자열집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (set.contains(br.readLine())) {
                count++;
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
}
