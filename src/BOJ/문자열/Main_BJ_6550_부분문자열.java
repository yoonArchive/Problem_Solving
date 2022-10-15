package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_6550_부분문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        StringTokenizer st;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            st = new StringTokenizer(input, " ");
            char[] s = st.nextToken().toCharArray();
            char[] t = st.nextToken().toCharArray();
            if (isSubString(s, t)) {
                sb.append("Yes").append("\n");
            } else {
                sb.append("No").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static boolean isSubString(char[] s, char[] t) {
        int sIndex = 0;
        for (int tIndex = 0, tLen = t.length; tIndex < tLen; tIndex++) {
            if (t[tIndex] == s[sIndex]) {
                sIndex++;
            }
            if (sIndex == s.length) {
                return true;
            }
        }
        return false;
    }
}
