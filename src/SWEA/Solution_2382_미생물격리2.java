package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리2 {
    static int N, M, K;
    static Micro map[][];
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};
    static PriorityQueue<Micro> pQueue;

    static class Micro implements Comparable<Micro> {
        int r, c, cnt, dir; // 행, 열, 군집 크기, 이동 방향

        public Micro(int r, int c, int cnt, int dir) {
            super();
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Micro o) {
            return o.cnt - this.cnt; // 내림차순, 최대힙
        }

    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("2382.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new Micro[N][N]; // 매시간마다 각 셀에 이동한 미생물 정보
            pQueue = new PriorityQueue<>(); // 살아있는 미생물 담고 있는 pQueue

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                pQueue.add(new Micro(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            sb.append("#" + tc + " " + move()).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    /**
     * 주어진 M 시간 동안 미생물 이동 처리
     *
     * @return
     */
    private static int move() {
        int time = M, nr, nc, remainCnt = 0; // 필요한 변수들 set

        while (time-- > 0) {
            // 군집 리스트에서 군집들을 하나씩 모두 꺼내어 처리
            while (!pQueue.isEmpty()) {
                Micro m = pQueue.poll();

                nr = m.r += dr[m.dir]; // m의 r값을 갱신하고 nr에 대입
                nc = m.c += dc[m.dir];

                if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) { // 약품이 칠해진 셀(가장자리)
                    if ((m.cnt = m.cnt / 2) == 0) // 크기 줄인 후 0이면 소멸. 소멸된 애들은 더이상 처리할 필요 없이 다음 군집 넘어가면 된다.
                        continue;
                    // 방향 반대로 턴
                    if (m.dir % 2 == 1)
                        m.dir++;
                    else
                        m.dir--;
                }

                if (map[nr][nc] == null) { // 해당 자리에 처음 이동한 미생물 군집이면 그 자리에 세팅
                    map[nr][nc] = m;
                } else {// 해당 자리에 처음 이동한 미생물 군집이 아니면 기존 군집에 합치기(cnt가 큰 애부터 poll하므로 무조건 합칠 수 있다!)
                    map[nr][nc].cnt += m.cnt;
                }
            }
            // 1시간 처리
            // pQueue가 empty인 상태이므로 map을 탐색하며 살아있는 군집들을 pQueue에 넣어준다
            // + map을 재사용하기 위해 pQueue에 넣었다면 null 처리
            remainCnt = reset();
        }
        return remainCnt;
    }

    /**
     * 매시간마다 필요한 정리, 초기화 작업
     *
     * @return
     */
    private static int reset() {
        int total = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != null) { // 살아있는 미생물이 있으면
                    pQueue.offer(map[r][c]);
                    total += map[r][c].cnt; // 살아있는 미생물 군집의 크기 누적
                    map[r][c] = null;
                }
            }
        }
        return total;
    }

}
