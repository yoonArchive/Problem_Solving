package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_9466_텀프로젝트 {

    public static int N;
    public static int[] selects;
    public static boolean[] isChecked, isVisited;
    public static int teamStudents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            selects = new int[N + 1];
            isChecked = new boolean[N + 1];
            isVisited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                selects[i] = Integer.parseInt(st.nextToken());
            }
            teamStudents = 0;
            for (int i = 1; i <= N; i++) {
                if (!isChecked[i]) {
                    makeTeam(i);
                }
            }
            sb.append(N - teamStudents).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void makeTeam(int student) {
        // 이미 팀 구성 여부를 확인한 경우
        if (isChecked[student]) {
            return;
        }
        // 현재 탐색에서 싸이클을 이룸 -> 팀 구성
        if (isVisited[student]) {
            isChecked[student] = true;
            teamStudents++;
        }
        isVisited[student] = true;
        makeTeam(selects[student]);
        isChecked[student] = true;
        isVisited[student] = false;
    }
}
