package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_19637_IF문좀대신써줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] name = new String[N];
        int[] power = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            name[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, mid = 0, right = N - 1, curPower = 0;
        for (int i = 0; i < M; i++) {
            curPower = Integer.parseInt(br.readLine());
            while (left < right) {
                mid = (left + right) / 2;
                if (curPower <= power[mid])
                    right = mid;
                else left = mid + 1;
            }
            sb.append(name[left]).append("\n");
            left = 0;
            right = N - 1;
        }
        System.out.println(sb.toString());
        br.close();
    }
}

