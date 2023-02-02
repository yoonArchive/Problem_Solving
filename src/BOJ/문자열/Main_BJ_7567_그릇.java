package BOJ.문자열;

import java.io.*;

public class Main_BJ_7567_그릇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] bowls = br.readLine().toCharArray();
        int height = 10;
        for (int i = 1, length = bowls.length; i < length; i++) {
            height += bowls[i] == bowls[i - 1] ? 5 : 10;
        }
        bw.write(Integer.toString(height));
        br.close();
        bw.flush();
        bw.close();
    }
}
