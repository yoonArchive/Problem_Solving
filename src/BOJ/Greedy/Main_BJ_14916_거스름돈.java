package BOJ.Greedy;

import java.io.*;

public class Main_BJ_14916_거스름돈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int change = Integer.parseInt(br.readLine());
        boolean isPossible = false;
        int remain = 0;
        int numberOf5won = change / 5;
        while (numberOf5won >= 0) {
            remain = change - (5 * numberOf5won);
            if (remain % 2 == 0) {
                isPossible = true;
                break;
            }
            numberOf5won--;
        }
        if (isPossible) {
            bw.write(Integer.toString(numberOf5won + remain / 2));
        } else {
            bw.write("-1");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
