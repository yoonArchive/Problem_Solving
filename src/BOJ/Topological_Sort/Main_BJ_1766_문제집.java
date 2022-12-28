package BOJ.Topological_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1766_문제집 {

    public static StringBuilder sb;
    public static int N, M;
    public static int[] preSolveCount;
    public static List<List<Integer>> preSolveInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        preSolveCount = new int[N + 1];
        preSolveInfo = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            preSolveInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            preSolveCount[B]++;
            preSolveInfo.get(A).add(B);
        }
        solve();
        System.out.println(sb);
        br.close();
    }

    private static void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (preSolveCount[i] == 0) {
                pq.offer(i);
            }
        }
        while (!pq.isEmpty()) {
            int problem = pq.poll();
            sb.append(problem).append(" ");
            for (int nextProblem : preSolveInfo.get(problem)) {
                if (--preSolveCount[nextProblem] == 0) {
                    pq.offer(nextProblem);
                }
            }
        }
    }
}
