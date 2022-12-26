package BOJ.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_1283_단축키지정 {

    public static final String OPEN_TAG = "[";
    public static final String CLOSE_TAG = "]";
    public static final String NEW_LINE = "\n";
    public static final char EMPTY = ' ';
    
    public static Set<Character> shortcuts;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        shortcuts = new HashSet<>();
        for (int i = 0; i < N; i++) {
            addShortCut(br.readLine());
            sb.append(NEW_LINE);
        }
        System.out.println(sb);
        br.close();
    }

    private static void addShortCut(String word) {
        st = new StringTokenizer(word);
        boolean findStatus = false;
        int pass = 0;
        char currentCharacter;
        // 단어의 첫 글자를 단축키로 지정하는 경우
        while (st.hasMoreTokens()) {
            String currentWord = st.nextToken();
            currentCharacter = currentWord.charAt(0);
            if (!shortcuts.contains(currentCharacter)) {
                findStatus = true;
                shortcuts.add(currentCharacter);
                shortcuts.add(currentCharacter < 'a' ? Character.toLowerCase(currentCharacter) : Character.toUpperCase(currentCharacter));
                if (pass == 0) {
                    word = word.replaceFirst(Character.toString(currentCharacter), OPEN_TAG + currentCharacter + CLOSE_TAG);
                } else {
                    word = word.replaceFirst(EMPTY + Character.toString(currentCharacter), EMPTY + OPEN_TAG + currentCharacter + CLOSE_TAG);
                }
                sb.append(word);
                break;
            }
            pass++;
        }
        //단어의 첫 글자를 단축키로 지정할 수 없는 경우 : 왼쪽에서부터 차례대로 알파벳 탐색하여 지정
        if (!findStatus) {
            for (int i = 0; i < word.length(); i++) {
                currentCharacter = word.charAt(i);
                if (currentCharacter == EMPTY) {
                    continue;
                }
                if (!shortcuts.contains(currentCharacter)) {
                    findStatus = true;
                    shortcuts.add(currentCharacter);
                    shortcuts.add(currentCharacter < 'a' ? Character.toLowerCase(currentCharacter) : Character.toUpperCase(currentCharacter));
                    sb.append(word.substring(0, i)).append(OPEN_TAG).append(currentCharacter).append(CLOSE_TAG).append(word.substring(i + 1, word.length()));
                    break;
                }
            }
        }
        // 어떠한 것도 단축키로 지정할 수 없는 경우
        if (!findStatus) {
            sb.append(word);
        }
    }
}
