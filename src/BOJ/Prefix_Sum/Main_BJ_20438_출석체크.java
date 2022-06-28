package BOJ.Prefix_Sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_20438_출석체크 {
	static final int ABSENT = 1;
	static final int ATTENDANCE = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int K = Integer.parseInt(st.nextToken()); // 졸고있는 학생 수
		int Q = Integer.parseInt(st.nextToken()); // 출석코드 받는 학생 수
		int M = Integer.parseInt(st.nextToken());// 구간 갯수
		int[] students = new int[N + 3];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++)
			students[Integer.parseInt(st.nextToken())] = ABSENT;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			int student = Integer.parseInt(st.nextToken());
			if (students[student] == ABSENT)
				continue;
			for (int j = student; j <= N + 2; j += student) {
				if (students[j] != ABSENT)
					students[j] = ATTENDANCE;
			}
		}
		for (int i = 3; i <= N + 2; i++) {
			if (students[i] == ATTENDANCE)
				students[i] = 0;
			else if (students[i] == 0)
				students[i] = ABSENT;
			students[i] += students[i - 1];
		}
		int start = 0, end = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			sb.append(students[end] - students[start - 1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
