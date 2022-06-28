package BOJ.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1593_문자해독_fail_시간초과 {
    static char W[], permuW[], S[];
    static boolean isSelected[];
    static int WLen, SLen, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        WLen = Integer.parseInt(st.nextToken());
        SLen = Integer.parseInt(st.nextToken());
        W = new char[WLen];
        permuW = new char[WLen];
        W = br.readLine().toCharArray();
        S = br.readLine().toCharArray();
        isSelected = new boolean[WLen];
        permutation(0);
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void permutation(int count) {
        if (count == WLen) {
            find();
            return;
        }
        for (int i = 0; i < WLen; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            permuW[count] = W[i];
            permutation(count + 1);
            isSelected[i] = false;
        }
    }

    private static void find() {
        int s = 0;
        while (s < SLen) {
            if (S[s] == permuW[0]) {
                for (int w = 1; w < WLen; w++) {
                    s++;
                    if (s >= SLen)
                        break;
                    if (S[s] != permuW[w]) {
                        s = s - w;
                        break;
                    }
                    if (w == WLen - 1)
                        cnt++;
                }
            }
            s++;
        }
    }

}
