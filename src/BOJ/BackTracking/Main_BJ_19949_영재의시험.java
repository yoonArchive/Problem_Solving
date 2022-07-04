package BOJ.BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_19949_영재의시험 {
    static final int PROBLEMS = 10;
    static int answers[];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        answers = new int[PROBLEMS];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < PROBLEMS; i++)
            answers[i] = Integer.parseInt(st.nextToken());
        solve(1, 0, 0, 0);
        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void solve(int continuous, int score, int count, int prev) {
        if (continuous == 3) return;
        if (count == PROBLEMS) {
            if (score >= 5) result++;
            return;
        }
        for (int i = 1; i <= 5; i++) {
            if (i == prev) {
                if (i == answers[count]) solve(continuous + 1, score + 1, count + 1, i);
                else solve(continuous + 1, score, count + 1, i);
            } else {
                if (i == answers[count]) solve(1, score + 1, count + 1, i);
                else solve(1, score, count + 1, i);
            }
        }
    }
}
