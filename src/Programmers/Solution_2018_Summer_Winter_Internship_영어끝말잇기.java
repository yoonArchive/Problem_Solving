package Programmers;

import java.util.HashSet;

public class Solution_2018_Summer_Winter_Internship_영어끝말잇기 {
    public static void main(String[] args) {
        int n = 2;
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        solution(n, words);
    }

    public static int[] solution(int n, String[] words) {
        HashSet<String> wordSet = new HashSet<>();
        int[] answer = new int[2];
        int totalRound = (int) (Math.ceil((double) words.length / n));
        int round = 0;
        String word = "";
        char lastChar = words[0].charAt(0);
        outer: while (round++ < totalRound) {
            for (int i = 0; i < n; i++) {
                int turn = n * (round - 1) + i;
                if (turn >= words.length) break;
                word = words[turn];
                if (wordSet.contains(word) || word.charAt(0) != lastChar) {
                    answer[0] = i + 1;
                    answer[1] = round;
                    break outer;
                }
                wordSet.add(word);
                lastChar = word.charAt(word.length() - 1);
            }
        }
        return answer;
    }

}
