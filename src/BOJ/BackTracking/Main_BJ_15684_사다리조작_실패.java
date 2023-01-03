package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15684_사다리조작_실패 {
    static final int LINE = 1;
    static final int NOLINE = 0;
    static final int ADDEDLINE = 2;
    static int N, M, H;
    static boolean isConnected[][];
    static int state[][];
    static int selected[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 세로선
        M = Integer.parseInt(st.nextToken()); // 가로선
        H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수
        isConnected = new boolean[H + 1][N];
        state = new int[H + 1][N + 1];

        // 이미 가로선이 놓여있는 곳을 true로 변경, state 배열에 LINE 놓아주기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            isConnected[r][c] = true;
            state[r][c] = LINE;
            state[r][c + 1] = LINE;
        }

        for (int i = 0; i <= 3; i++) {
            selected = new int[i][2];
            setLine(0, 0, i);
        }
        System.out.println(-1);
        br.close();

    }

    private static boolean setLine(int start, int count, int limit) {
        if (count == limit) {
//			System.out.println("사다리 타기 시작 " + count);
//			print();
//			for (int i = 0; i < count; i++)
//				System.out.println(Arrays.toString(selected[i]));
            if (game(0, 1, 1, 1, count)) { // 사다리 게임을 해본다.
                return true;
            }
//			System.out.println("다른 가로선 놓기 시도");
            return false;
        }

        for (int i = start, length = (N - 1) * H; i < length; i++) {
            int r = i / (N - 1) + 1;
            int c = i % (N - 1) + 1;

            if (isConnected[r][c])
                continue;

            // 가로선 놓기
            selected[count][0] = r;
            selected[count][1] = c;
            if (state[r][c] == 0)
                state[r][c] = ADDEDLINE;
            if (state[r][c + 1] == 0)
                state[r][c + 1] = ADDEDLINE;
            isConnected[r][c] = true;

            // 다음 가로선 놓으러 가기
            setLine(i + 1, count + 1, limit);

            // 원래대로 되돌려 놓기
            if (state[r][c] == ADDEDLINE)
                state[r][c] = NOLINE;
            if (state[r][c + 1] == ADDEDLINE)
                state[r][c + 1] = NOLINE;
            isConnected[r][c] = false;
        }
        return false;
    }

//	private static void print() {
//		System.out.println();
//		for (int i = 0; i < state.length; i++) {
//			System.out.println(Arrays.toString(state[i]));
//		}
//		System.out.println();
//
//	}

    /**
     * 모든 i번 세로선의 결과가 i번이 나오면 true 반환
     *
     * @param vLine      처리한 세로선의 개수
     * @param vLineIdx   현재 처리하고 있는 세로선 인덱스
     * @param r          현재 행 값
     * @param c          현재 열 값
     * @param addedLine 현재 추가된 가로선의 개수
     */
    private static boolean game(int vLine, int vLineIdx, int r, int c, int addedLineNum) {

        // System.out.println(r + " " + c);

        if (vLine == N) { // 모든 세로선을 처리했다면
            System.out.println(addedLineNum);
            System.exit(0);
        }

        if (r >= H + 1) { // 맨 아래 도달했다면 현재 위치가 i인지 확인
            if (c == vLineIdx) {
//				System.out.println("세로선 하나 처리 완료");
                if (game(vLine + 1, vLineIdx + 1, 1, c + 1, addedLineNum))
                    return true;
                else
                    return false;
            } else {
//				System.out.println("목표 지점 i 아님");
                return false;
            }
        }

        if (state[r][c] != NOLINE) { // 현재 칸이 선의 일부일 때
            if (c - 1 >= 1 && state[r][c - 1] != NOLINE) { // 왼쪽도 선의 일부이면
                if (game(vLine, vLineIdx, r + 1, c - 1, addedLineNum))// 왼쪽+ 아래로 이동(stackOverflow 방지)
                    return true;
                else
                    return false;
            } else if (c + 1 < N + 1 && state[r][c + 1] != NOLINE) { // 오른쪽이 선의 일부이면
                if (game(vLine, vLineIdx, r + 1, c + 1, addedLineNum)) // 오른쪽+아래 이동
                    return true;
                else
                    return false;
            }
        } else { // 현재 칸이 N0LINE일 때
            if (game(vLine, vLineIdx, r + 1, c, addedLineNum)) // 아래로 이동
                return true;
            else
                return false;
        }
        return false;
    }

}
