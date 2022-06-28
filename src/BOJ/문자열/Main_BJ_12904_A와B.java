package BOJ.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_12904_A와B {
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = br.readLine();
        String T = br.readLine();
        Queue<String> stringQ = new LinkedList<>();
        stringQ.offer(T);
        if (isChangable(stringQ)) bw.write("1");
        else bw.write("0");
        br.close();
        bw.flush();
        bw.close();

    }

    public static boolean isChangable(Queue<String> stringQ) {
        boolean isSame = false;
        String str = null;
        int lengthOfS = S.length();

        while (!stringQ.isEmpty()) {
            str = stringQ.poll();
            if (str.length() == lengthOfS - 1) break;
            if (str.equals(S)) {
                isSame = true;
                break;
            }
            if (str.charAt(str.length() - 1) == 'A')
                stringQ.offer(operation1(str));
            else
                stringQ.offer(operation2(str));
        }

        if (isSame) return true;
        return false;
    }

    public static String operation1(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static String operation2(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setLength(sb.length() - 1);
        return sb.reverse().toString();
    }

}
