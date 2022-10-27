package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main_BJ_11478_서로다른부분문자열의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine();
        Set<String> set = new HashSet<>();
        for (int i = 0, length = S.length(); i <= length - 1; i++) {
            for (int j = i + 1; j <= length; j++) {
                set.add(S.substring(i, j));
            }
        }
        bw.write(Integer.toString(set.size()));
        br.close();
        bw.flush();
        bw.close();
    }
}
