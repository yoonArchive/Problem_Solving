package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_14889_스타트와링크 {
    static int stat[][];
    static int startTeam[];
    static int linkTeam[];
    static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int peopleNum = Integer.parseInt(br.readLine());
        stat = new int[peopleNum + 1][peopleNum + 1];
        startTeam = new int[peopleNum / 2];
        linkTeam = new int[peopleNum / 2];

        StringTokenizer st = null;
        for (int i = 1; i <= peopleNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= peopleNum; j++) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minDiff = Integer.MAX_VALUE;
        selectStartTeam(1, 0, 0, peopleNum);
        bw.write(Integer.toString(minDiff));
        br.close();
        bw.flush();
        bw.close();

    }

    public static void selectStartTeam(int start, int count, int startTeamFlag, int peopleNum) {
        if (count == peopleNum / 2) {
            int linkTeamCount = 0;
            for (int i = 1; i <= peopleNum; i++) {
                if ((startTeamFlag & 1 << i) == 0)
                    linkTeam[linkTeamCount++] = i;
            }
            int startTeamStat = makeTeamStat(startTeam);
            int linkTeamStat = makeTeamStat(linkTeam);
            int curDiff = Math.abs(startTeamStat - linkTeamStat);
            minDiff = Math.min(minDiff, curDiff);
            return;
        }
        for (int i = start; i <= peopleNum; i++) {
            startTeam[count] = i;
            selectStartTeam(i + 1, count + 1, startTeamFlag | 1 << i, peopleNum);
        }
    }

    public static int makeTeamStat(int[] team) {
        int totalStat = 0;
        for (int i = 0, memberNum = team.length; i < memberNum - 1; i++) {
            for (int j = i + 1; j < memberNum; j++) {
                int pre = team[i];
                int next = team[j];
                totalStat += (stat[pre][next] + stat[next][pre]);
            }
        }
        return totalStat;
    }
}
