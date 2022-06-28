package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BJ_7662_이중우선순위큐 {
    // Treemap의 getOrDefault , lastKey, firstKey

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                char command = st.nextToken().charAt(0);
                int number = Integer.parseInt(st.nextToken());
                if (command == 'I')
                    map.put(number, map.getOrDefault(number, 0) + 1);
                else {
                    if (map.size() == 0)
                        continue;
                    int removeNum;
                    if (number == 1)
                        removeNum = map.lastKey();
                    else
                        removeNum = map.firstKey();

                    int removeVal = map.get(removeNum);
                    if (removeVal == 1)
                        map.remove(removeNum);
                    else
                        map.put(removeNum, removeVal - 1);
                }
            }

            if (map.size() == 0)
                sb.append("EMPTY").append("\n");
            else
                sb.append(map.lastKey()).append(' ').append(map.firstKey()).append("\n");
        }
        System.out.println(sb.toString());
    }

}
