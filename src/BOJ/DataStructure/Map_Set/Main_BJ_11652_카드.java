package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main_BJ_11652_카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        while (N-- > 0) {
            String number = br.readLine();
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        String maxCountNumber = Long.toString(Long.MAX_VALUE);
        int maxCount = 0;
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            int count = (int) entry.getValue();
            if (count >= maxCount) {
                if (maxCount == count) {
                    if (Long.parseLong(maxCountNumber) > Long.parseLong(key)) {
                        maxCountNumber = key;
                        maxCount = count;
                    }
                } else {
                    maxCountNumber = key;
                    maxCount = count;
                }
            }
        }
        bw.write(maxCountNumber);
        br.close();
        bw.flush();
        bw.close();
    }
}
