package BOJ.문자열;

import java.io.*;

public class Main_BJ_1543_문서검색 {

    public static char[] document, findWord;
    public static int documentLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        document = br.readLine().toCharArray();
        findWord = br.readLine().toCharArray();
        documentLength = document.length;
        int documentIdx = 0, wordIdx = 0, count = 0;
        while (documentIdx < documentLength) {
            if (find(documentIdx, wordIdx)) {
                count++;
                documentIdx += findWord.length;
            } else {
                documentIdx++;
            }
            wordIdx = 0;
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean find(int documentIdx, int wordIdx) {
        if (wordIdx == findWord.length) {
            return true;
        }
        boolean flag = false;
        if (documentIdx < documentLength && document[documentIdx] == findWord[wordIdx]) {
            flag = find(documentIdx + 1, wordIdx + 1);
        }
        return flag;
    }
}
