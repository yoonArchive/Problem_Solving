package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_20055_컨베이어벨트위의로봇 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] belt = new int[2 * N];
        boolean[] robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++)
            belt[i] = Integer.parseInt(st.nextToken());

        int step = 1;
        while (true) {
            // step 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            int tmp = belt[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--)
                belt[i] = belt[i - 1];
            belt[0] = tmp;

            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[N - 1] = false;

            // step 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
            for (int i = N - 1; i > 0; i--) {
                if (belt[i] > 0 && !robot[i] && robot[i - 1]) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    belt[i]--;
                }
            }

            // step 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (belt[0] > 0 && !robot[0]) {
                robot[0] = true;
                belt[0]--;
            }

            // step 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
            int count = 0;
            for (int i = 0; i < 2 * N; i++) {
                if (belt[i] == 0)
                    count++;
            }
            if (count >= K)
                break;

            step++;
        }

        bw.write(Integer.toString(step));
        br.close();
        bw.flush();
        bw.close();
    }
}