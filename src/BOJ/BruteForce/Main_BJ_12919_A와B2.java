package BOJ.BruteForce;

import java.io.*;

public class Main_BJ_12919_A와B2 {

    public static String S;
    public static boolean canChange;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = br.readLine();
        String T = br.readLine();
        makeTtoS(T);
        if (canChange) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void makeTtoS(String str) {
        if (str.equals(S)) {
            canChange = true;
            return;
        }
        if (str.length() == S.length()) {
            return;
        }
        if (str.charAt(str.length() - 1) == 'A') {
            makeTtoS(str.substring(0, str.length() - 1));
        }
        if (str.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(str.substring(1));
            sb.reverse();
            makeTtoS(sb.toString());
        }
    }
}
