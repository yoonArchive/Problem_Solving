package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_5585_거스름돈_개선 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int change = 1000 - (Integer.parseInt(br.readLine())); // 받을 거스름돈
        int unit[] = {500, 100, 50, 10, 5, 1};
        int count = 0;
        int i = 0;
        while (change > 0) {
            while (change >= unit[i]) {
                change -= unit[i];
                count++;
            }
            i++;
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

}
