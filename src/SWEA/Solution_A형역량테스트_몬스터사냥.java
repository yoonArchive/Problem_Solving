package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_A형역량테스트_몬스터사냥 {
    /*
     * 몬스터 사냥 헌터 - 상하좌우 움직이며 몬스터 사냥 고객은 헌터에게 몬스터 사냥을 요청 - 몬스터가 처리된 것을 확인하면 작업 완료 몬스터
     * 처리 순서 상관 x 고객들에게 확인시켜주는 순서 상관 x 몬스터를 처리한 후 바로 확인 시켜줄 필요 없음
     *
     * 헌터 이동 - 1시간에 한 칸씩 상하좌우로 움직임
     *
     * 몬스터를 처치 - 헌터 몬스터가 있는 위치로 이동
     *
     * 고객이 몬스터 처리 확인 - 헌터가 고객의 위치로 이동
     *
     * 헌터의 초기 위치 - (1,1)
     *
     * 몬스터와 고객의 위치 중복 x 몬스터와 고객은 (1,1)에 위치할 수 있음
     *
     * 고객 음수, 몬스터 양수(-1번 고객은 1번 몬스터의 처리를 요청)
     */

    /*
     * 구현 과정 고객, 몬스터들을 가지고 순열을 만든다 - 이 때, 고객을 고르기 위해서는 고객이 요청한 몬스터가 처리된 상태여야 한다. -
     * 순열을 만드는 동시에 시간 누적
     */

    static int N; // 맵 크기
    static int minTime; // 최소 시간을 담을 값(정답)
    static List<Creature> creatures = new ArrayList<>(); // 괴물, 사람 정보
    static boolean[] visited; // 처리했는지 여부

    static class Creature implements Comparable<Creature> {
        int r, c; // 좌표
        int number; // 번호
        boolean isCustomer = false; // true: 고객, false: 괴물

        public Creature(int r, int c, int number, boolean isCustomer) {
            super();
            this.r = r;
            this.c = c;
            this.number = number;
            this.isCustomer = isCustomer;
        }

        @Override
        public int compareTo(Creature o) { // 숫자 기준 오름차순 정렬 - 고객에 대칭하는 몬스터 쉽게 찾기 위해
            return this.number - o.number;
        }

    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream(".txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    // 몬스터나 고객의 경우 list에 담아놓기
                    if (value != 0) {
                        // 몬스터의 경우
                        if (value > 0)
                            creatures.add(new Creature(i, j, value, false));
                            // 고객의 경우
                        else
                            creatures.add(new Creature(i, j, value, true));
                    }
                }
            }
            Collections.sort(creatures);// 숫자값 기준 오름차순 정렬
            visited = new boolean[creatures.size()];
            minTime = Integer.MAX_VALUE;
            hunting(0, 0, 1, 1);
            sb.append("#" + tc + " " + minTime).append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * 몬스터 또는 고객을 하나 처리하고 다음 요소 처리는 재귀로 넘김
     *
     * @param cnt  현재 처리한 개수
     * @param time 현재까지 소요된 시간
     * @param r    헌터의 좌표 중 행값
     * @param c    헌터의 좌표 중 열값
     */
    private static void hunting(int cnt, int time, int r, int c) {
        // step 01. 모든 고객과 몬스터를 처리했다면
        if (cnt == creatures.size()) {
            // step 02. 현재까지 걸린 시간이 최소 시간이라면 갱신
            minTime = Math.min(minTime, time);
            return;
        }

        // 순열
        for (int i = 0; i < creatures.size(); i++) {
            if (visited[i])
                continue; // 이미 처리했다면 넘기기

            // 일반적인 순열이 아님
            // 고객의 경우, 그에 해당하는 몬스터를 처리한 경우에만 고객이 확인 가능
            Creature p = creatures.get(i);

            // 해당 사람이 고객인 경우
            if (p.isCustomer) {
                int monsterIdx = creatures.size() - 1 - i; // 해당 고객의 몬스터 인덱스
                // 고객이 요청한 몬스터를 처리하지 못한 상태라면 다른 요소를 고려
                if (!visited[monsterIdx])
                    continue;
            }

            visited[i] = true; // 방문 처리
            hunting(cnt + 1, time + Math.abs(r - p.r) + Math.abs(c - p.c), p.r, p.c);
            visited[i] = false; // 다음 처리를 위해 되돌리기
        }

    }

}
