package BOJ.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_11663_선분위의점 {
    static int coordinates[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        coordinates = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            coordinates[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(coordinates);
        int start = 0, end = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            sb.append(search(start, end, N) + 1).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int search(int start, int end, int n) {
        int left = 0, mid = 0, right = n - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (start > coordinates[mid]) left = mid + 1;
            else right = mid - 1;
        }
        int startIdx = left;
        left = 0;
        right = n - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (end < coordinates[mid]) right = mid - 1;
            else left = mid + 1;
        }
        int endIdx = right;
        return endIdx - startIdx;
    }
}
