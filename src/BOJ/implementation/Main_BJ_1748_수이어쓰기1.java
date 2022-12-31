package BOJ.implementation;

import java.io.*;

public class Main_BJ_1748_수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int size = 0, currentSize = 1;
        while (true) {
            if (N >= Math.pow(10, currentSize)) {
                size += currentSize * (Math.pow(10, currentSize) - Math.pow(10, currentSize - 1));
            } else {
                size -= (Math.pow(10, currentSize - 1) - N - 1) * currentSize;
                break;
            }
            currentSize++;
        }
        bw.write(Integer.toString(size));
        br.close();
        bw.flush();
        bw.close();
    }
}
