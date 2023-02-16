package BOJ.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_5635_생일 {

    public static class Birthday implements Comparable<Birthday> {
        String name;
        int day;
        int month;
        int year;

        public Birthday(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int compareTo(Birthday b) {
            if (this.year == b.year) {
                if (this.month == b.month) {
                    return this.day - b.day;
                }
                return this.month - b.month;
            }
            return this.year - b.year;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Birthday[] birthdays = new Birthday[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            birthdays[i] = new Birthday(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(birthdays);
        sb.append(birthdays[N - 1].name).append("\n").append(birthdays[0].name);
        System.out.println(sb);
        br.close();
    }
}
