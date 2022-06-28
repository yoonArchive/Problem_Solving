package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_21608_상어초등학교 {

    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int classroom[][], like[][];
    static PriorityQueue<Seat> pq;
    static int N, studentsNum, likeCnt, emptyCnt;

    public static class Seat implements Comparable<Seat> {
        int r, c, likeCnt, emptyCnt;

        public Seat(int r, int c, int likeCnt, int emptyCnt) {
            super();
            this.r = r;
            this.c = c;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Seat o) { // likeCnt -> emptyCnt -> r-> c
            if (this.likeCnt == o.likeCnt) {
                if (this.emptyCnt == o.emptyCnt) {
                    if (this.r == o.r) {
                        return this.c - o.c;
                    } else {
                        return this.r - o.r;
                    }
                } else {
                    return o.emptyCnt - this.emptyCnt;
                }
            } else {
                return o.likeCnt - this.likeCnt;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        studentsNum = N * N;
        like = new int[studentsNum + 1][4]; // 좋아하는 학생 번호
        classroom = new int[N][N]; // 자리 배치 현황
        pq = new PriorityQueue<Seat>();

        StringTokenizer st = null;
        for (int n = 1; n <= studentsNum; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int studentNum = Integer.parseInt(st.nextToken()); // 배치될 학생 번호
            // 좋아하는 학생 4명의 번호 저장
            for (int i = 0; i < 4; i++) {
                like[studentNum][i] = Integer.parseInt(st.nextToken());
            }
            // 자리 배치
            arrange(studentNum);
            pq.clear();
        }
        // 자리 배치 끝난 후 만족도 구하기
        int answer = 0;
        bw.write(Integer.toString(getScore(answer)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void arrange(int studentNum) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                // 이미 학생이 배치된 곳은 x
                if (classroom[r][c] > 0)
                    continue;

                emptyCnt = likeCnt = 0;

                // 인접 자리 탐색
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!isIn(nr, nc))
                        continue;

                    if (classroom[nr][nc] == 0) // 인접한 칸이 비어있는지 확인
                        emptyCnt++;
                    else { // 비어있지 않으면 좋아하는 학생 있는지 확인
                        for (int i = 0; i < 4; i++) {
                            if (classroom[nr][nc] == like[studentNum][i]) {
                                likeCnt++;
                                break;
                            }
                        }
                    }
                }
                pq.add(new Seat(r, c, likeCnt, emptyCnt));
            }
        }
        Seat seat = pq.poll(); // 가장 우선 순위가 높은 위치
        classroom[seat.r][seat.c] = studentNum; // 그 위치에 현재 학생 배치
    }

    private static int getScore(int score) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int studentNum = classroom[r][c]; // 현재 자리에 배치된 학생 번호
                likeCnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!isIn(nr, nc))
                        continue;

                    for (int k = 0; k < 4; k++) {
                        if (classroom[nr][nc] == like[studentNum][k]) {
                            likeCnt++;
                            break;
                        }
                    }
                }
                score += Math.pow(10, likeCnt - 1);
            }
        }
        return score;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}