package BOJ.Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_20207_달력 {

    public static final int SIZE = 366;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] schedules = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedules[i][0] = Integer.parseInt(st.nextToken());
            schedules[i][1] = Integer.parseInt(st.nextToken());
        }
        int counts[] = new int[SIZE];
        for (int i = 0; i < n; i++) {
            for (int day = schedules[i][0]; day <= schedules[i][1]; day++) {
                counts[day]++;
            }
        }
        int row, column, day = 0, area = 0;
        while (++day < SIZE) {
            row = 0;
            column = 0;
            if (counts[day] != 0) {
                while (true) {
                    row = Math.max(row, counts[day]);
                    column++;
                    if (++day >= SIZE || counts[day] == 0) {
                        break;
                    }
                }
            }
            area += (row * column);
        }
        bw.write(Integer.toString(area));
        br.close();
        bw.flush();
        bw.close();
    }
}
