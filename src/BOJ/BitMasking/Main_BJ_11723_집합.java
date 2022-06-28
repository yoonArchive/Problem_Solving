package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11723_집합 {
    static int set;
    static StringBuilder sb;

    public static void add(int num) {
        set = set | 1 << (num - 1);
    }

    public static void remove(int num) {
        set = set & ~(1 << (num - 1));
    }

    public static void check(int num) {
        if ((set & 1 << (num - 1)) != 0)
            sb.append("1" + "\n");
        else
            sb.append("0" + "\n");
    }

    public static void toggle(int num) {
        set = set ^ (1 << (num - 1));
    }

    public static void all() {
        for (int i = 0; i < 20; i++)
            set = set | 1 << i;
    }

    public static void empty() {
        set &= 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String operation = st.nextToken();
            int num;
            switch (operation) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    add(num);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    remove(num);
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    check(num);
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    toggle(num);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

}
