package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1062_가르침_ver_bitMasking {
    static final int SIZE = 'z' - 'a' + 1;
    static int N, K, maxCnt;
    static int words[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int ans = 0;
        if (K < 5)
            ans = 0;
        else if (K == 26)
            ans = N;
        else {
            int flag = 0;
            words = new int[N];
            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                for (int j = 0, len = word.length(); j < len; j++)
                    words[i] |= (1 << word.charAt(j) - 'a');
            }
            flag |= (1 << 'a' - 'a');
            flag |= (1 << 'n' - 'a');
            flag |= (1 << 't' - 'a');
            flag |= (1 << 'i' - 'a');
            flag |= (1 << 'c' - 'a');
            K -= 5;
            teach(0, 0, flag);
            ans = maxCnt;
        }
        bw.write(Integer.toString(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void teach(int start, int count, int flag) {
        if (count == K) {
            maxCnt = Math.max(maxCnt, countReadableWords(flag));
            return;
        }
        for (int i = start; i <= SIZE; i++) {
            if ((flag & (1 << i)) != 0)
                continue;
            teach(i + 1, count + 1, flag | (1 << i));
        }
    }

    private static int countReadableWords(int flag) {
        int count = 0;
        for (int word : words) {
            if ((flag & word) == word) {
                count++;
            }
        }
        return count;
    }

}
