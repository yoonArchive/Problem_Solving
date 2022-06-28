package BOJ.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1593_문자해독_success {
    static final int SIZE = 52;
    static int Wcnt[], Scnt[];
    static char S[];
    static boolean isSelected[];
    static int WLen, SLen, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        WLen = Integer.parseInt(st.nextToken());
        SLen = Integer.parseInt(st.nextToken());

        Wcnt = new int[SIZE];
        Scnt = new int[SIZE];

        String input = br.readLine();// W
        for (int i = 0; i < WLen; i++) {
            char c = input.charAt(i);
            putWord(c, Wcnt);
        }

        S = br.readLine().toCharArray();
        for (int i = 0; i < WLen; i++) {
            char c = S[i];
            putWord(c, Scnt);
        }

        // 슬라이딩윈도우
        int startIdx = 0;
        int lastIdx = WLen - 1;
        int count = 0;
        char start = ' ';
        while (true) {
            count += check();
            start = S[startIdx];
            if (isLarge(start)) Scnt[start - 'A']--;
            else Scnt[start - 'a' + 26]--;
            startIdx++;
            lastIdx++;
            if (lastIdx >= SLen)
                break;
            putWord(S[lastIdx], Scnt);
        }

        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void putWord(char c, int cnt[]) {
        if (isLarge(c)) cnt[c - 'A']++;
        else cnt[c - 'a' + 26]++;
    }

    public static boolean isLarge(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static int check() {
        for (int i = 0; i < SIZE; i++)
            if (Wcnt[i] != Scnt[i])
                return 0;
        return 1;
    }
}
