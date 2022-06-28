package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main_BJ_1038_감소하는수 {
    static int N;
    static ArrayList<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        long ans = 0;
        if (N <= 9)
            ans = N;
        else {
            list = new ArrayList<>();
            for (int i = 1; i <= 9; i++)
                getDecreaseNumber(i, 1);
            Collections.sort(list);
            if (N > list.size())
                ans = -1;
            else
                ans = list.get(N - 1);
        }

        bw.write(Long.toString(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void getDecreaseNumber(long number, int length) {
        if (length > 10)
            return;

        list.add(number);
        for (int i = 0; i < number % 10; i++) {
            getDecreaseNumber((number * 10) + i, length + 1);
        }
    }

}
