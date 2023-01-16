package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Main_BJ_1302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> soldBooks = new TreeMap<>();
        int max = 0;
        while (N-- > 0) {
            String book = br.readLine();
            soldBooks.put(book, soldBooks.getOrDefault(book, 0) + 1);
            max = Math.max(max, soldBooks.get(book));
        }
        for (Map.Entry<String, Integer> entry : soldBooks.entrySet()) {
            if (entry.getValue() == max) {
                bw.write(entry.getKey());
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
