package BOJ.Implementation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_20413_MVP다이아몬드 {
	static int limit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 게임 플레이 개월 수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		limit = new int[4]; // b,s,g,p등급의 최대 과금액
		char grades[] = new char[N];
		for (int i = 0; i < 4; i++)
			limit[i] = Integer.parseInt(st.nextToken()) - 1;
		grades = br.readLine().toCharArray(); // N번째 달까지의 MVP등급

		int pre = grades[0] == 'D' ? limit[3] + 1 : calc(grades[0]);
		int sum = pre;

		for (int i = 1; i < N; i++) {
			char curGrade = grades[i];
			int cur = curGrade == 'D' ? limit[3] + 1 : calc(curGrade) - pre;
			sum += cur;
			pre = cur;
		}

		bw.write(Integer.toString(sum));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int calc(char curGrade) {
		if (curGrade == 'B') {
			return limit[0];
		} else if (curGrade == 'S') {
			return limit[1];
		} else if (curGrade == 'G') {
			return limit[2];
		} else {
			return limit[3];
		}
	}

}
