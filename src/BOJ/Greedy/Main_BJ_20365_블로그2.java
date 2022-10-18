package BOJ.Greedy;

import java.io.*;

public class Main_BJ_20365_블로그2 {

    public static final char BLUE = 'B';
    public static final char RED = 'R';

    public static int N;
    public static String colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        colors = br.readLine();
        int index = -1;
        int startWithBlue = 0, startWithRed = 0;
        while (++index < N) {
            if (colors.charAt(index) == BLUE) {
                startWithBlue++;
                index = moveIndex(index, BLUE);
            } else {
                startWithRed++;
                index = moveIndex(index, RED);
            }
        }
        bw.write(Integer.toString(Math.min(startWithBlue, startWithRed) + 1));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int moveIndex(int index, char color) {
        while (++index < N) {
            if (colors.charAt(index) != color) {
                break;
            }
        }
        return index - 1;
    }
}
