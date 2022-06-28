package Programmers;

public class Programmers_2020_Kakao_문자열압축 {

    public static void main(String[] args) {
        System.out.println(solution("abcabcabcabcdededededede"));
    }

    public static int solution(String s) {
        int length = s.length();
        int unit = length / 2;
        StringBuilder compressed = new StringBuilder();
        StringBuilder compare = new StringBuilder();

        int ans = length, idx = 0, count = 0;
        while (unit > 0) { // loop 한번 돌 때마다 압축단위를 1개씩 줄인다.
            compare.append(s.substring(idx, idx + unit));
            idx += unit; // idx: 시작 인덱스
            count = 1;

            // 압축 시도
            while (idx + unit <= length) {
                if (!s.substring(idx, idx + unit).equals(compare.toString())) { // 같지 않음
                    if (count > 1) // 반복되는 값이 1보다 큰 경우 count를 같이 append
                        compressed.append(count).append(compare);
                    else
                        compressed.append(compare);
                    compare.setLength(0);
                    compare.append(s.substring(idx, idx + unit));
                    count = 1;
                    idx += unit;
                } else {// 같음
                    idx += unit;
                    count++;
                }
            }

            if (count > 1)
                compressed.append(count).append(compare).append(s.substring(idx, length));
            else
                compressed.append(compare).append(s.substring(idx, length));

            // 최소 길이 갱신
            if (compressed.length() < ans)
                ans = compressed.length();

            // 다음 unit으로 넘어가기 전 reset
            idx = 0;
            compare.setLength(0);
            compressed.setLength(0);
            unit--;
        }
        return ans;
    }

}
