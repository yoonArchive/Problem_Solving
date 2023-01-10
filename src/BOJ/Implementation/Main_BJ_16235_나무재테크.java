package BOJ.Implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16235_나무재테크 {

    public static int N, M, K;
    public static int[][] A, nutriment;
    public static PriorityQueue<Integer>[][] ages;
    public static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        nutriment = new int[N][N];
        ages = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutriment[i][j] = 5;
                ages[i][j] = new PriorityQueue<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ages[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()));
        }
        while (K-- > 0) {
            springAndSummerFunc();
            fallFunc();
            winterFunc();
        }
        bw.write(Integer.toString(M));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void springAndSummerFunc() {
        PriorityQueue<Integer> pq;
        Queue<Integer> queue = new LinkedList<>();
        int size, deadTreeAges = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pq = ages[i][j];
                size = pq.size();
                while (size-- > 0) {
                    int age = pq.poll();
                    if (nutriment[i][j] >= age) {
                        nutriment[i][j] -= age;
                        queue.offer(age + 1);
                    } else {
                        deadTreeAges += (age / 2);
                        M--;
                    }
                }
                while (!queue.isEmpty()) {
                    ages[i][j].offer(queue.poll());
                }
                nutriment[i][j] += deadTreeAges;
                deadTreeAges = 0;
            }
        }
    }

    private static void fallFunc() {
        PriorityQueue<Integer> pq;
        Queue<Integer> queue = new LinkedList<>();
        int[][] plusCounts = new int[N][N];
        int size;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pq = ages[i][j];
                size = pq.size();
                while (size-- > 0) {
                    int age = pq.poll();
                    queue.offer(age);
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + deltas[d][0];
                            int nc = j + deltas[d][1];
                            if (!isIn(nr, nc)) {
                                continue;
                            }
                            plusCounts[nr][nc]++;
                            M++;
                        }
                    }
                }
                while (!queue.isEmpty()) {
                    ages[i][j].offer(queue.poll());
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < plusCounts[i][j]; k++) {
                    ages[i][j].offer(1);
                }
            }
        }
    }

    private static void winterFunc() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutriment[i][j] += A[i][j];
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
