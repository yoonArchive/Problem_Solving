package BOJ.BinarySearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_10816_숫자카드2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(cards);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int number = Integer.parseInt(st.nextToken());
			int result = findEndIdx(cards, N, number) - findStartIdx(cards, N, number);
			sb.append(result - 1).append(" ");
		}
		System.out.println(sb.toString());
		br.close();

	}

	private static int findStartIdx(int cards[], int N, int number) {
		int left = 0;
		int right = N - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (cards[mid] < number)
				left = mid + 1;
			else if (cards[mid] >= number) {
				right = mid - 1;
			}
		}
		return right;
	}

	private static int findEndIdx(int cards[], int N, int number) {
		int left = 0;
		int right = N - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (cards[mid] <= number)
				left = mid + 1;
			else if (cards[mid] > number) {
				right = mid - 1;
			}
		}
		return left;
	}

}
