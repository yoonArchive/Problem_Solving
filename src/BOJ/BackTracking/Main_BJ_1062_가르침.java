package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1062_가르침 {
    static final int SIZE = 'z' - 'a' + 1;
    static int N, K, maxCnt;
    static boolean[] isTeached;
    static String words[];

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
            isTeached = new boolean[SIZE];
            words = new String[N];
            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                words[i] = word.substring(4, word.length() - 4);
            }
            isTeached['a' - 'a'] = true;
            isTeached['n' - 'a'] = true;
            isTeached['t' - 'a'] = true;
            isTeached['i' - 'a'] = true;
            isTeached['c' - 'a'] = true;
            K -= 5;
            teach(0, 0);
            ans = maxCnt;
        }
        bw.write(Integer.toString(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void teach(int start, int count) {
        if (count == K) {
            maxCnt = Math.max(maxCnt, countReadableWords());
            return;
        }
        for (int i = start; i < isTeached.length; i++) {
            if (isTeached[i])
                continue;
            isTeached[i] = true;
            teach(i + 1, count + 1);
            isTeached[i] = false;
        }
    }

    private static int countReadableWords() {
        int count = N;
        for (String word : words) {
            for (int i = 0, length = word.length(); i < length; i++) {
                if (!isTeached[word.charAt(i) - 'a']) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

}
