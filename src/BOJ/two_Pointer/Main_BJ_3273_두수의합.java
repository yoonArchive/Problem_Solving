package BOJ.two_Pointer;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int numbers[] = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            numbers[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(numbers);
        int sum = Integer.parseInt(br.readLine());
        int count = 0, left = 0, right = n - 1;
        int leftNum = 0, rightNum = 0;
        for (left = 0; left < n; left++) {
            leftNum = numbers[left];
            while (right > left) {
                rightNum = numbers[right];
                if (leftNum + rightNum > sum)
                    right--;
                else if (leftNum + rightNum < sum)
                    break;
                else {
                    count++;
                    right--;
                    break;
                }
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
}
