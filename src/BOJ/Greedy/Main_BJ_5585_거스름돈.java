package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_5585_거스름돈 {
	static int count;

	public static int calc(int remain, int unit) {
		int num = remain / unit;
		count += num;
		remain -= unit * num;
		return remain;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int change = 1000 - (Integer.parseInt(br.readLine())); // 받을 거스름돈
		count = 0;
		while (change != 0) {
			if (change / 500 >= 1)
				change=calc(change, 500);
			else if (change / 100 >= 1)
				change=calc(change, 100);
			else if (change / 50 >= 1)
				change=calc(change, 50);
			else if (change / 10 >= 1)
				change=calc(change, 10);
			else if (change / 5 >= 1)
				change=calc(change, 5);
			else {
				count += change;
				break;
			}
		}
		bw.write(Integer.toString(count));
		br.close();
		bw.flush();
		bw.close();
	}

}
