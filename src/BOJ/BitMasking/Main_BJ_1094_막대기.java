package BOJ.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1094_막대기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int X = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 0; i < 8; i++) { // 이진법 표현에서 1의 갯수 구하기
            if ((X & 1 << i) != 0)
                count++;
        }
        sb.append(count);
        System.out.println(sb.toString());

    }

}
