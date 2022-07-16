package BOJ.BruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_1107_리모컨 {
    static int goal, goalLen, brokenCnt, minCnt;
    static boolean isBroken[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        goal = Integer.parseInt(br.readLine());
        goalLen = Integer.toString(goal).length();
        brokenCnt = Integer.parseInt(br.readLine());
        isBroken = new boolean[10];
        if (brokenCnt != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < brokenCnt; i++)
                isBroken[Integer.parseInt(st.nextToken())] = true;
        }
        minCnt = Integer.MAX_VALUE;
        int start = 100;
        if (start == goal)
            bw.write("0");
        else {
            moveChannel(start, false, 0);
            bw.write(Integer.toString(minCnt));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static void moveChannel(int currentChannel, boolean isPressedChannel, int count) {
        if (currentChannel < 0 || count > goalLen + 1) return;

        if (currentChannel > goal) minCnt = Math.min(minCnt, count + (currentChannel - goal));
        else minCnt = Math.min(minCnt, count + (goal - currentChannel));

        // 직접 채널 입력
        if (count <= goalLen) { // <이걸로해서 틀렸습니다 여러번~
            for (int i = 0; i <= 9; i++) {
                if (isBroken[i]) continue;
                if (!isPressedChannel) moveChannel(i, true, count + 1); // 이전에 입력한 채널이 없는 경우
                else moveChannel(currentChannel * 10 + i, true, count + 1); // 이전에 입력한 채널이 있는 경우
            }
        }
    }
}