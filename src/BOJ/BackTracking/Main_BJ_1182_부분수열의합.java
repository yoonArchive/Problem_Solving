package BOJ.BackTracking;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1182_부분수열의합 {

    static int N, S;
    static int result;
    static int arr[];

    public static void choose(int count, int start, int chooseNum, int sum) {
        if (count == chooseNum) {
            if (sum == S) result++;
            return;
        }
        for (int i = start; i < N; i++) {
            sum += arr[i];
            choose(count + 1, i + 1, chooseNum, sum);
            sum -= arr[i];
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            choose(0, 0, i, 0);
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

}
