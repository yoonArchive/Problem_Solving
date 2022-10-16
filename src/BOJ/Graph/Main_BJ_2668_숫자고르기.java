package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main_BJ_2668_숫자고르기 {

    public static int N;
    public static int[] arr;
    public static boolean[] isVisited;
    public static TreeSet<Integer> treeSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        isVisited = new boolean[N + 1];
        treeSet = new TreeSet<>();
        int current = 0;
        for (int i = 1; i <= N; i++) {
            current = Integer.parseInt(br.readLine());
            arr[i] = current;
            if (i == current) {
                isVisited[i] = true;
                treeSet.add(i);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i]) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                selectNumber(i, list);
            }
        }
        sb.append(treeSet.size()).append("\n");
        for (int number : treeSet) {
            sb.append(number).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void selectNumber(int firstLineNumber, List<Integer> list) {
        isVisited[firstLineNumber] = true;
        int secondLineNumber = arr[firstLineNumber];
        if (list.contains(secondLineNumber)) {
            int startIdx = list.indexOf(secondLineNumber);
            for (int i = startIdx, size = list.size(); i < size; i++) {
                treeSet.add(list.get(i));
            }
            return;
        }
        if (!isVisited[secondLineNumber]) {
            list.add(secondLineNumber);
            selectNumber(secondLineNumber, list);
        }
    }
}
