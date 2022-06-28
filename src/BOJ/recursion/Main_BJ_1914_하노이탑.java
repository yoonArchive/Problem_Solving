package BOJ.recursion;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_BJ_1914_하노이탑 {
	static StringBuilder sb;
	
	static void hanoi(int N, int from, int temp, int to) {
		if (N == 1) {
			sb.append(from+" "+to+"\n");
			return;
		} else {
			hanoi(N - 1, from, to, temp);
			sb.append(from+" "+to+"\n");
			hanoi(N - 1, temp, from, to);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb=new StringBuilder();
		int N = sc.nextInt();
		BigInteger result = new BigInteger("1");
		if (N == 1)
			sb.append("1");
		else {
			result=new BigInteger("2").pow(N); //2^N
			result = result.subtract(new BigInteger("1")); //-1
			sb.append(result+"\n");
		}
		if (N <= 20) {
			hanoi(N, 1, 2, 3);
		}
		System.out.println(sb.toString());
		sc.close();
	}

}
