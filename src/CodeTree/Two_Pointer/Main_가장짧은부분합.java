package CodeTree.Two_Pointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_가장짧은부분합 {
    // A[i] + ... + A[j] >= S
    // A[i] + ... + A[j-1] < S
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int arr[] = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int j = 0, minLen = n + 1, sum = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && sum < s) {
                sum += arr[j];
                j++;
            }
            if (sum < s)
                break;
            minLen = Math.min(minLen, j - i);
            sum -= arr[i];
        }
        if (minLen == n + 1)
            minLen = -1;
        bw.write(Integer.toString(minLen));
        br.close();
        bw.flush();
        bw.close();
    }

}
