package BOJ.Graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_18405_경쟁적전염 {

    public static int N, K;
    public static int[][] map;
    public static int[][] deltas = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public static class Virus implements Comparable<Virus> {
        int number;
        int r;
        int c;
        int order;

        public Virus(int number, int r, int c, int order) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.order = order;
        }

        public int compareTo(Virus v) {
            if (this.order == v.order) {
                return this.number - v.number;
            }
            return this.order - v.order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        PriorityQueue<Virus> virusQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int currentVirus = Integer.parseInt(st.nextToken());
                if (currentVirus != 0) {
                    map[i][j] = currentVirus;
                    virusQueue.offer(new Virus(currentVirus, i, j, 0));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        for (int i = 0; i < S; i++) {
            spread(virusQueue);
        }
        int result = map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1];
        bw.write(Integer.toString(result));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void spread(PriorityQueue<Virus> virusQueue) {
        int size = virusQueue.size();
        while (--size > 0) {
            Virus virus = virusQueue.poll();
            int number = virus.number;
            int r = virus.r;
            int c = virus.c;
            int order = virus.order;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!inIn(nr, nc) || map[nr][nc] != 0) {
                    continue;
                }
                map[nr][nc] = number;
                virusQueue.offer(new Virus(number, nr, nc, order + 1));
            }
        }
    }

    private static boolean inIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
