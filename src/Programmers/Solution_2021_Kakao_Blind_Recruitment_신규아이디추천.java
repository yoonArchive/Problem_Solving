package Programmers;

public class Solution_2021_Kakao_Blind_Recruitment_신규아이디추천 {

    public static void main(String[] args) {
        System.out.println(solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {
        // step 01. 대문자를 대응되는 소문자로 치환
        new_id = new_id.toLowerCase();

        // step 02. 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표 제외 모든 문자 제거
        StringBuilder id = new StringBuilder();
        int size = new_id.length();
        for (int i = 0; i < size; i++) {
            char c = new_id.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.')
                id.append(c);
        }

        // step 03. 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환
        new_id = id.toString();
        while (new_id.contains(".."))
            new_id = new_id.replace("..", ".");

        // step 04. 마침표가 처음이나 끝에 위치하면 제거
        id.setLength(0);
        id.append(new_id);
        if (id.charAt(0) == '.')
            id.deleteCharAt(0);
        size = id.length();
        if (size > 0) {
            if (id.charAt(size - 1) == '.')
                id.setLength(size - 1);
        }

        // step 05. new_id가 빈 문자열이라면 "a" 대입
        if (id.length() == 0)
            id.append('a');

        // step 06. new_id의 길이가 16자 이상이면 첫 15개 문자 제외 모두 제거.
        if (id.length() > 15)
            id.setLength(15);
        // 제거 후 마침표가 마지막에 위치하면 제거
        size = id.length();
        if (id.charAt(size - 1) == '.')
            id.setLength(size - 1);

        // step 07. 2자 이하이면 길이가 3이 될때 까지 마지막 문자 끝에 붙이기
        size = id.length();
        char last = id.charAt(size - 1);
        while (++size < 4)
            id.append(last);

        return id.toString();
    }

}
