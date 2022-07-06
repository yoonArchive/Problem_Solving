package BOJ.BinarySearch;

import java.io.*;

public class Main_BJ_2417_정수제곱근 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());
        long left = 0, mid = 0;
        long right = (long) Math.sqrt(n) + 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (mid * mid >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (n == 1) bw.write("1");
        else bw.write(Long.toString(mid));
        br.close();
        bw.flush();
        bw.close();
    }
}
