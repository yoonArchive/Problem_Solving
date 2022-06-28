package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1254_팰린드롬만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S = br.readLine();
        sb.append(S);

        StringBuilder resultSb = new StringBuilder();

        int i = 1;
        while (true) {
            if (sb.toString().equals(new StringBuilder(sb).reverse().toString())) {
                resultSb.append(sb.length());
                break;
            }
            if (i != 1)
                sb.setLength(sb.length() - (i - 1));
            sb.append(new StringBuilder(sb.substring(0, i)).reverse());
            i++;
        }

        System.out.println(resultSb.toString());
        br.close();

    }

}
