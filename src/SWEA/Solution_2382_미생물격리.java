package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리 {
    static final int BORDER = -1;
    static int[] dr = {0, -1, 1, 0, 0}; // 제자리 상 하 좌 우
    static int[] dc = {0, 0, 0, -1, 1};
    static int N, M, K;
    static Group cell[][];
    static ArrayList<Group> list;

    public static class Group {
        int id, r, c, cnt, dir, moveNum;

        public Group(int id, int r, int c, int cnt, int dir, int moveNum) {
            super();
            this.id = id;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
            this.moveNum = moveNum;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            Group other = (Group) obj;
            if (id == other.id)
                return true;
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("2382.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= 1; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cell = new Group[N][N];
            list = new ArrayList<>();

            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                Group group = new Group(i, r, c, cnt, dir, 0);
                list.add(group);
                cell[r][c] = group;
            }
            sb.append("#" + tc + " " + move()).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int move() {
        ArrayList<Group> newList = new ArrayList<>();
        while (M-- > 0) {
            Group newCell[][] = new Group[N][N];
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                Group g = list.get(i);
                int id = g.id;
                int r = g.r;
                int c = g.c;
                int cnt = g.cnt;
                int dir = g.dir;
                int moveNum = g.moveNum;

                int nr = r + dr[dir];
                int nc = c + dc[dir];
                int nMoveNum = moveNum + 1;

                if (isBorder(nr, nc)) {// 다음 넘어갈 곳이 약품이 칠해져 있는 곳이라면
                    cnt /= 2; // 미생물 수를 반으로 줄이고
                    dir = dir % 2 == 0 ? dir - 1 : dir + 1; // 이동 방향을 반대로 바꾼다.
                }

                if (newCell[nr][nc] != null) { // 다음 넘어갈 셀에 다른 군집이 있다면
                    Group difG = newCell[nr][nc]; // 해당 셀에 존재하는 군집 가져오기
                    int idx = list.indexOf(difG); // 리스트에서의 인덱스 값 가져오기
                    difG = list.get(idx);
                    if (difG.cnt > cnt) { // 현재 군집보다 다른 군집에 있는 미생물의 수가 많으면
                        Group n = new Group(difG.id, difG.r, difG.c, difG.cnt + cnt, difG.dir, difG.moveNum);
                        list.set(idx, n);// 합쳐지고 현재 군집은 사라짐
                        newCell[nr][nc] = n;
                        continue;
                    } else { // 현재 군집의 미생물의 수가 더 많으면
                        cnt += difG.cnt;
                        Group n = new Group(difG.id, difG.r, difG.c, cnt, difG.dir, difG.moveNum);
                        newCell[nr][nc] = n;
                    }
                }
                newCell[nr][nc] = new Group(id, nr, nc, cnt, dir, nMoveNum);
                newList.add(new Group(id, nr, nc, cnt, dir, nMoveNum));
            }

            // 출력해보기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newCell[i][j] == null)
                        System.out.print(0 + " ");
                    else
                        System.out.print(newCell[i][j].cnt + " ");
                }
                System.out.println();
            }
            System.out.println();

            list = (ArrayList<Group>) newList.clone();
            newList.clear();

        }

        // M 시간 후 남아있는 미생물 수 계산
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cell[i][j] != null)
                    res += cell[i][j].cnt;
            }
        }
        return res;
    }

    private static boolean isBorder(int r, int c) {
        return r == 0 || c == 0 || r == N - 1 || c == N - 1;
    }

}
