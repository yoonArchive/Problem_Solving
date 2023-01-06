package Programmers;

public class Solution_크키가작은부분문자열 {
    public int solution(String t, String p) {
        int answer = 0;
        int pLen = p.length(), tLen = t.length();
        long numP = Long.parseLong(p);
        for (int i = 0; i <= tLen - pLen; i++) {
            if (t.charAt(i) < p.charAt(0)) {
                answer++;
                continue;
            }
            if (Long.parseLong(t.substring(i, i + pLen)) <= numP) {
                answer++;
            }
        }
        return answer;
    }
}
