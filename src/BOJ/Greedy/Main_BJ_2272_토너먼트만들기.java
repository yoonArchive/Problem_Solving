package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_2272_토너먼트만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> ranking = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            ranking.add(Integer.parseInt(st.nextToken()));

        int maxRank = n;
        int minDiff = 0;

        while (maxRank != 1) {
            int mIdx = ranking.indexOf(maxRank);
            int lRank = mIdx == 0 ? 0 : ranking.get(mIdx - 1);
            int rRank = mIdx == ranking.size() - 1 ? 0 : ranking.get(mIdx + 1);
            minDiff += lRank > rRank ? maxRank - lRank : maxRank - rRank;
            ranking.remove(mIdx);
            maxRank--;
        }

        bw.write(Integer.toString(minDiff));
        br.close();
        bw.flush();
        bw.close();
    }
}
