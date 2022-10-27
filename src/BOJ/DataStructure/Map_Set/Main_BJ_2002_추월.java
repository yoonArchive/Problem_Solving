package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main_BJ_2002_추월 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> entranceOrder = new HashMap<>();
        for (int i = 0; i < N; i++) {
            entranceOrder.put(br.readLine(), i);
        }
        int[] exitOrder = new int[N];
        for (int i = 0; i < N; i++) {
            exitOrder[i] = entranceOrder.get(br.readLine());
        }
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (exitOrder[i] > exitOrder[j]) {
                    count++;
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
