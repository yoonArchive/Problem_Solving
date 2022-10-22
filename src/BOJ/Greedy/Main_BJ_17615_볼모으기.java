package BOJ.Greedy;

import java.io.*;

public class Main_BJ_17615_볼모으기 {

    public static final char RED = 'R';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String balls = br.readLine();
        int redBalls = 0, blueBalls = 0;
        char ball;
        for (int i = 0; i < N; i++) {
            ball = balls.charAt(i);
            if (ball == RED) {
                redBalls++;
            } else {
                blueBalls++;
            }
        }
        int lessBallCount = Math.min(redBalls, blueBalls);
        if (lessBallCount == 0) {
            bw.write(Integer.toString(lessBallCount));
        } else {
            char color = balls.charAt(0);
            int index = 0, continuousCountFromLeft = 1, continuousCountFromRight = 1;
            while (++index < N) {
                if (balls.charAt(index) != color) {
                    break;
                }
                continuousCountFromLeft++;
            }
            int moveToLeft = color == RED ? redBalls - continuousCountFromLeft : blueBalls - continuousCountFromLeft;
            color = balls.charAt(N - 1);
            index = N - 1;
            while (--index >= 0) {
                if (balls.charAt(index) != color) {
                    break;
                }
                continuousCountFromRight++;
            }
            int moveToRight = color == RED ? redBalls - continuousCountFromRight : blueBalls - continuousCountFromRight;
            bw.write(Integer.toString(Math.min(Math.min(moveToLeft, moveToRight), lessBallCount)));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
