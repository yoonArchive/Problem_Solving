package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main_BJ_20291_파일정리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> treeMap = new TreeMap();
        while (N-- > 0) {
            String extension = br.readLine().split("\\.")[1];
            if (!treeMap.containsKey(extension)) {
                treeMap.put(extension, 1);
            } else {
                treeMap.put(extension, treeMap.get(extension) + 1);
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
