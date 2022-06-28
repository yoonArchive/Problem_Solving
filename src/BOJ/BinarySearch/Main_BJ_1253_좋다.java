package BOJ.BinarySearch;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1253_좋다 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int numbers[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(numbers);

		int res = 0;
		for (int i = 0; i < N; i++) {
			int num = numbers[i];
			int left = 0;
			int right = N - 1;

			while (left < right) {
				if (numbers[left] + numbers[right] > num)
					right--;
				else if (numbers[left] + numbers[right] < num)
					left++;
				else {
					if (left == i)
						left++;
					else if (right == i)
						right--;
					else {
						res++;
						break;
					}
				}
			}
		}
		
		bw.write(Integer.toString(res));
		br.close();
		bw.flush();
		bw.close();
	}

}
