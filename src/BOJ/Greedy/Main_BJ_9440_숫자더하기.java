package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_9440_숫자더하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            if (st.countTokens() == 1)
                break;
            int N = Integer.parseInt(st.nextToken());
            int numbers[] = new int[N];
            int zeroCnt = 0;
            for (int i = 0; i < N; i++) {
                int number = Integer.parseInt(st.nextToken());
                numbers[i] = number;
                if (number == 0)
                    zeroCnt++;
            }

            Arrays.sort(numbers);
            StringBuilder num1 = new StringBuilder();
            StringBuilder num2 = new StringBuilder();
            num1.append(numbers[zeroCnt]); // 첫번째 자리 숫자 먼저 세팅
            num2.append(numbers[zeroCnt + 1]);

            int insertIdx = 1; // 수열에 0이 존재하면 두번째 자리에 삽입
            int copyZeroCnt = zeroCnt;
            while (copyZeroCnt > 0) {
                num1.insert(insertIdx, 0);
                copyZeroCnt--;
                if (copyZeroCnt > 0) {
                    num2.insert(insertIdx, 0);
                    copyZeroCnt--;
                }
            }

            for (int i = 2 + zeroCnt; i < N; i++) {
                if (i % 2 == 0)
                    num1.append(numbers[i]);
                else
                    num2.append(numbers[i]);
            }
            sb.append(num1).append("\n");
            sb.append(Integer.parseInt(num1.toString()) + Integer.parseInt(num2.toString())).append("\n");
        }
        System.out.println(sb.toString());

    }

}
