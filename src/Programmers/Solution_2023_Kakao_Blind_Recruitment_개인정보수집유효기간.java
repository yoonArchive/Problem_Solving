package Programmers;

import java.util.*;

class Solution_2023_Kakao_Blind_Recruitment_개인정보수집유효기간 {

    public static int t_year, t_month, t_day;
    public static Map<Character, Integer> termMap;

    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] todayArr = convertDateToIntArray(today);
        t_year = todayArr[0];
        t_month = todayArr[1];
        t_day = todayArr[2];
        termMap = new HashMap<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            char type = st.nextToken().charAt(0);
            int period = Integer.parseInt(st.nextToken());
            termMap.put(type, period);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0, len = privacies.length; i < len; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            char type = st.nextToken().charAt(0);
            if (isOutOfDate(date, type)) {
                list.add(i + 1);
            }
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private static boolean isOutOfDate(String dateStr, char type) {
        int[] date = convertDateToIntArray(dateStr);
        int date_year = date[0];
        int date_month = date[1] + termMap.get(type);
        int date_day = date[2] - 1;
        if (date_day <= 0) {
            date_month--;
            date_day = 28;
        }
        if (date_month > 12) {
            int plusYear = date_month / 12;
            date_year += plusYear;
            date_month -= (plusYear * 12);
        }
        if (date_month <= 0) {
            date_year--;
            date_month = 12;
        }
        return compare(date_year, date_month, date_day);
    }

    private static boolean compare(int d_year, int d_month, int d_day) {
        if (d_year < t_year) {
            return true;
        } else {
            if (d_year == t_year) {
                if (d_month < t_month) {
                    return true;
                } else {
                    if (d_month == t_month && d_day < t_day) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int[] convertDateToIntArray(String dateStr) {
        int[] date = new int[3];
        StringTokenizer st = new StringTokenizer(dateStr, ".");
        for (int i = 0; i <= 2; i++) {
            date[i] = Integer.parseInt(st.nextToken());
        }
        return date;
    }
}