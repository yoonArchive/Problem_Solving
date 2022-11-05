package BOJ.DataStructure.Map_Set;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_19583_싸이버개강총회 {

    public static int startHour, startMinute, endHour, endMinute, streamingEndHour, streamingEndMinute, count;
    public static Set<String> nicknameSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " :");
        startHour = Integer.parseInt(st.nextToken());
        startMinute = Integer.parseInt(st.nextToken());
        endHour = Integer.parseInt(st.nextToken());
        endMinute = Integer.parseInt(st.nextToken());
        streamingEndHour = Integer.parseInt(st.nextToken());
        streamingEndMinute = Integer.parseInt(st.nextToken());
        nicknameSet = new HashSet<>();
        String chatting;
        while ((chatting = br.readLine()) != null) {
            st = new StringTokenizer(chatting, ": ");
            if (!st.hasMoreTokens()) {
                break;
            }
            chat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void chat(int hour, int minute, String nickname) {
        if (enterBeforeStart(hour, minute)) {
            nicknameSet.add(nickname);
        } else if (exitBetweenEndAndStreamingEnd(hour, minute)) {
            if (nicknameSet.contains(nickname)) {
                nicknameSet.remove(nickname);
                count++;
            }
        }
    }

    private static boolean enterBeforeStart(int hour, int minute) {
        if (hour < startHour || (hour == startHour && minute <= startMinute)) {
            return true;
        }
        return false;
    }

    private static boolean exitBetweenEndAndStreamingEnd(int hour, int minute) {
        if (hour > endHour || (hour == endHour && minute >= endMinute)) {
            if (hour < streamingEndHour || (hour == streamingEndHour && minute <= streamingEndMinute)) {
                return true;
            }
        }
        return false;
    }
}
