package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main_BJ_11656_접미사배열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String word = br.readLine();
        Set<String> suffixs = new TreeSet<>();
        for (int i = 0, length = word.length(); i < length; i++) {
            suffixs.add(word.substring(i, length));
        }
        for (String suffix : suffixs) {
            sb.append(suffix).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
