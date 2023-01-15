package BOJ.BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_16401_과자나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] snacks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snacks);
        int left = 0;
        int right = snacks[N - 1];
        int index, count, mid;
        boolean findFlag;
        while (left <= right) {
            index = N - 1;
            count = 0;
            findFlag = false;
            mid = (left + right) / 2;
            if (mid == 0) {
                left = mid + 1;
                continue;
            }
            while (index >= 0) {
                count += (snacks[index] / mid);
                if (count >= M) {
                    findFlag = true;
                    break;
                }
                index--;
            }
            if (findFlag) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        bw.write(Integer.toString(right));
        br.close();
        bw.flush();
        bw.close();
    }
}
