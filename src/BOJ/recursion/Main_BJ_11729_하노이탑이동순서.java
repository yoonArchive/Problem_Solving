package BOJ.recursion;

import java.util.Scanner;

public class Main_BJ_11729_하노이탑이동순서 {
	static StringBuilder sb;
	static int count=0;
	
	public static void hanoi(int n, int from, int tmp, int to) {
		if (n == 0)
			return;
		hanoi(n - 1, from, to, tmp);
		sb.append(from + " " + to + "\n");
		count++;
		hanoi(n - 1, tmp, from, to);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		int n = sc.nextInt();
		hanoi(n, 1, 2, 3);
		System.out.printf("%d%n%s",count,sb.toString());
		sc.close();
	}
}
