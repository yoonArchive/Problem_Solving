package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_A형역량테스트_낚시터 {

    /*
     * 자리 잡는 절차 1. 하나의 출입구씩 선택하여 순차 입장 2. 출입구 선택시, 낚시꾼들은 가장 가까운 빈 낚시터 자리로 한 명씩 이동
     * 차례로 자리 잡음 - 출입구에서 낚시터까지 1미터 3. 출입구 맨 마지막 사람의 경우, 가장 가까운 빈 자리가 두 곳이라면 하나를 선택해야
     * 함 4. 해당 출입구 자리잡기 완료 시, 다음 출입구 자리잡기 반복
     *
     * 목적 - 낚시꾼들의 이동거리를 모두 더한 값이 최소가 되도록 자리 잡기 - 이동거리의 합 출력
     *
     * 게이트를 선택하는 순서를 모두 변경해보며 가장 최소가 되는 값을 출력
     */

    static int N;

    // fishing[1][0] - 2번 게이트위 위치
    // fishing[1][1] - 2번 게이트에서 대기중인 낚시꾼 수
    static int[][] fishing; // 낚시터 정보

    static boolean[] visited; // 낚시터 배치 여부
    static int[] selected; // 게이트 순서
    static boolean[] isSelected;
    static int minDistance; // 최소 이동 거리

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("낚시터.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N + 1]; // 낚시터는 1번부터 사용
            // 낚시터 정보 입력
            fishing = new int[3][2];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                fishing[i][0] = Integer.parseInt(st.nextToken());
                fishing[i][1] = Integer.parseInt(st.nextToken());
            }
            minDistance = Integer.MAX_VALUE; // 최대값으로 초기화

            selected = new int[3];
            isSelected = new boolean[3];
            permutation(0);
            sb.append("#" + tc + " " + minDistance).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static void permutation(int cnt) {
        // step 1. 게이트 순서 선택 완료 -> 낚시꾼 배치 시작
        if (cnt == 3) {
            // step 2. 낚시꾼 배치
            arrange(0, fishing[selected[0]][1], 0, 0);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            selected[cnt] = i;
            permutation(cnt + 1);
            isSelected[i] = false;
        }

    }

    /**
     * 낚시꾼 한명을 배치하고 다음 낚시꾼 배치는 재귀 호출
     *
     * @param cnt       처리한 게이트 수
     * @param remainNum 현재 배치 중인 게이트에서 배치해야 할 사람의 수 -> 배치를 할 때마다 줄어든다.
     * @param distance  현재 배치 중인 게이트 인덱스와의 거리
     * @param move      현재까지 배치되는데 낚시꾼들이 이동한 거리
     */
    private static void arrange(int cnt, int remainNum, int distance, int move) {
        // 현재 게이트에서 배치할 사람이 0인 경우 다음 게이트 낚시꾼들을 배치
        if (remainNum == 0) {
            // step 3. 모든 배치가 완료됐을 때, 낚시꾼 이동거리 최소라면 갱신
            if (cnt == 2) {
                minDistance = Math.min(minDistance, move);
                return;
            }
            arrange(cnt + 1, fishing[selected[cnt + 1]][1], 0, move);
            return;
        }

        int gateIdx = fishing[selected[cnt]][0];
        int left = gateIdx - distance; // 왼쪽 인덱스
        int right = gateIdx + distance; // 오른쪽 인덱스

        // 왼쪽 자리가 빈 경우 -> 오른쪽 자리 비었는지 확인 할 필요 없음!
        if (left >= 1 && !visited[left]) {
            // 왼쪽에 낚시꾼 배치
            visited[left] = true;
            // 다음 낚시꾼 배치
            arrange(cnt, remainNum - 1, distance, move + distance + 1);
            visited[left] = false;

            // 게이트의 마지막 사람의 배치이고 오른쪽이 비어있다면
            if (remainNum == 1 && right <= N && !visited[right]) {
                visited[right] = true;
                arrange(cnt, remainNum - 1, distance, move + distance + 1);
                visited[right] = false;
            }
        }

        // 오른쪽 자리가 빈 경우
        else if (right <= N && !visited[right]) {
            // 오른쪽에 낚시꾼 배치
            visited[right] = true;
            // 다음 낚시꾼 배치
            arrange(cnt, remainNum - 1, distance + 1, move + distance + 1);
            visited[right] = false;
        }

        // 둘 다 사용중인 경우(현재 거리에서는 배치를 할 수 없으니 거리를 1 늘린다.)
        else {
            arrange(cnt, remainNum, distance + 1, move);
        }
    }

}
