package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1213_팰린드롬만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        int length = name.length();
        int[] counts = new int['Z' - 'A' + 1];
        for (int i = 0; i < length; i++) {
            counts[name.charAt(i) - 'A']++;
        }
        StringBuilder palindrome = new StringBuilder();
        int count = 0;
        char c = 'A';
        while (c <= 'Z') {
            if (counts[c - 'A'] >= 2) {
                palindrome.append(c);
                counts[c - 'A'] -= 2;
                count += 2;
            } else {
                c++;
            }
        }
        StringBuilder copy = new StringBuilder(palindrome);
        for (c = 'A'; c <= 'Z'; c++) {
            if (counts[c - 'A'] == 1) {
                palindrome.append(c);
                counts[c - 'A'] -= 1;
                count += 1;
                break;
            }
        }
        if (count != length) {
            palindrome = new StringBuilder("I'm Sorry Hansoo");
        } else {
            palindrome.append(copy.reverse());
        }
        System.out.println(palindrome);
        br.close();
    }
}
