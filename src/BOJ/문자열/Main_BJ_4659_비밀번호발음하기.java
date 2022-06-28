package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_4659_비밀번호발음하기 {
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String password = "";
        while (true) {
            password = br.readLine();
            if (password.equals("end"))
                break;
            sb.append("<").append(password).append(checkPw(password) ? "> is acceptable." : "> is not acceptable.")
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean checkPw(String password) {
        int length = password.length();
        char c;
        boolean isVowel = false;
        int vCnt = 0, contiguousVcnt = 0, contiguousCcnt = 0;

        for (int i = 0; i < length; i++) {
            c = password.charAt(i);
            isVowel = false;
            // case 1
            for (char v : vowels)
                if (c == v) {
                    isVowel = true;
                    vCnt++;
                    break;
                }
            // case 2
            if (isVowel) {
                contiguousVcnt++;
                contiguousCcnt = 0;
            } else {
                contiguousCcnt++;
                contiguousVcnt = 0;
            }
            if (contiguousVcnt == 3 || contiguousCcnt == 3)
                return false;
            // case 3
            if (i != 0) {
                if (c != 'e' && c != 'o' && c == password.charAt(i - 1))
                    return false;
            }
        }
        if (vCnt == 0)
            return false;
        return true;
    }

}
