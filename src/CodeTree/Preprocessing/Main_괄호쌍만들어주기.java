package CodeTree.Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_괄호쌍만들어주기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String A = br.readLine();
        int length = A.length();
        int count[] = new int[length]; // count[k]= A[k...N] 연속한 닫는 괄호의 갯수
        for (int i = length - 2; i >= 0; i--) {
            if (A.charAt(i) == ')' && A.charAt(i + 1) == ')')
                count[i] = count[i + 1] + 1;
            else
                count[i] = count[i + 1];
        }
        long cnt = 0;
        for (int i = 0; i < length - 2; i++) {
            if (A.charAt(i) == '(' && A.charAt(i + 1) == '(')
                cnt += count[i + 2];
        }
        bw.write(Long.toString(cnt));
        br.close();
        bw.flush();
        bw.close();

    }

}
