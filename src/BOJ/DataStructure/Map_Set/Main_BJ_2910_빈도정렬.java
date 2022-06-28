package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BJ_2910_빈도정렬 {

    public static class NumberInfo implements Comparable<NumberInfo> {
        int order, count;

        public NumberInfo(int order, int count) {
            super();
            this.order = order;
            this.count = count;
        }

        @Override
        public int compareTo(NumberInfo o) {
            if (o.count == this.count)
                return this.order - o.order;
            return o.count - this.count;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        Integer.parseInt(st.nextToken());
        TreeMap<Integer, NumberInfo> numberMap = new TreeMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int curNum = Integer.parseInt(st.nextToken());
            NumberInfo info = numberMap.get(curNum);
            if (info == null) {
                numberMap.put(curNum, new NumberInfo(i, 1));
            } else {
                numberMap.put(curNum, new NumberInfo(info.order, info.count + 1));
            }
        }

        List<Entry<Integer, NumberInfo>> entryList = new ArrayList<>(numberMap.entrySet());
        entryList.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        for (int i = 0; i < entryList.size(); i++) {
            Entry<Integer, NumberInfo> entry = entryList.get(i);
            int count = ((NumberInfo) entry.getValue()).count;
            int curNum = (int) entry.getKey();
            while (count-- > 0)
                sb.append(curNum).append(" ");
        }

        System.out.println(sb.toString());
    }

}
