package BOJ.문자열;

import java.io.*;

public class Main_BJ_1032_명령프롬프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] pattern = br.readLine().toCharArray();
        while (N-- > 1) {
            String file = br.readLine();
            for (int i = 0, length = file.length(); i < length; i++) {
                if (file.charAt(i) != pattern[i]) {
                    pattern[i] = '?';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : pattern) {
            sb.append(c);
        }
        System.out.println(sb);
        br.close();
    }
}
