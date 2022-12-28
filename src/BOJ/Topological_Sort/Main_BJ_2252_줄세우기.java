package BOJ.Topological_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2252_줄세우기 {

    public static StringBuilder sb;
    public static int N, M;
    public static int[] smallerCounts;
    public static List<List<Integer>> tallerStudents;
    public static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        smallerCounts = new int[N + 1];
        tallerStudents = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tallerStudents.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            smallerCounts[taller]++;
            tallerStudents.get(smaller).add(taller);
        }
        queue = new LinkedList<>();
        lineUp();
        System.out.println(sb);
        br.close();
    }

    private static void lineUp() {
        for (int i = 1; i <= N; i++) {
            if (smallerCounts[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int number = queue.poll();
            sb.append(number).append(" ");
            for (int taller : tallerStudents.get(number)) {
                if (--smallerCounts[taller] == 0) {
                    queue.offer(taller);
                }
            }
        }
    }
}
