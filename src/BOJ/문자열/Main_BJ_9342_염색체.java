package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_9342_염색체 {

    /**
     * ^ : 정규표현식 시작
     * $ : 정규표현식 끝
     * [A-F] : A,B,C,D,E,F
     * ? : 0번 또는 1번
     * + : 그 전 문자가 1개 이상
     */
    public static final String REGEX = "^[A-F]?A+F+C+[A-F]?$";
    public static final String INFECTED = "Infected!";
    public static final String GOOD = "Good";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            sb.append(br.readLine().matches(REGEX) ? INFECTED : GOOD).append("\n");
        }
        System.out.println(sb.toString());
    }

}
