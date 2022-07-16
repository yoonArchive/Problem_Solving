package Programmers;

import java.io.IOException;
import java.util.Arrays;

public class Programmers_2021_Dev_Matching_로또의최고순위와최저순위 {
    static final int MAX_NUMBER = 45;
    static boolean isSelected[];

    public static void main(String[] args) throws IOException {
        int[] lottos = {0, 0, 0, 0, 0, 0};
        int[] win_nums = {38, 19, 20, 40, 15, 25};
        System.out.println(Arrays.toString(solution(lottos, win_nums)));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {6, 1};
        isSelected = new boolean[MAX_NUMBER + 1];
        int number = 0, zeroCnt = 0;
        for (int i = 0; i < 6; i++) {
            number = lottos[i];
            if (number == 0) zeroCnt++;
            else isSelected[number] = true;
        }
        if (zeroCnt == 0) {
            int rank = getRank(win_nums);
            answer[0] = answer[1] = rank;
        } else selectNumber(answer, win_nums, 0, zeroCnt, 0);
        return answer;
    }

    private static void selectNumber(int[] answer, int[] win_nums, int start, int zeroCnt, int selectCnt) {
        if (selectCnt == zeroCnt) {
            int rank = getRank(win_nums);
            if (answer[0] > rank) answer[0] = rank;
            else if (answer[1] < rank) answer[1] = rank;
            return;
        }
        for (int i = start; i <= MAX_NUMBER; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            selectNumber(answer, win_nums, i + 1, zeroCnt, selectCnt + 1);
            isSelected[i] = false;
        }
    }

    private static int getRank(int[] win_nums) {
        int rank = 7;
        for (int i = 0; i < 6; i++) {
            if (isSelected[win_nums[i]]) rank--;
        }
        if (rank == 7) rank = 6;
        return rank;
    }

}
