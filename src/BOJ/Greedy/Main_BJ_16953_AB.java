package BOJ.Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_16953_AB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int count = 0;
        while (B != A) {
            if (B < A) {
                count = -2;
                break;
            }
            if (B % 10 == 1) {
                B /= 10;
            } else if (B % 2 == 0) {
                B /= 2;
            } else {
                count = -2;
                break;
            }
            count++;
        }
        bw.write(Integer.toString(count + 1));
        br.close();
        bw.flush();
        bw.close();
    }
}
