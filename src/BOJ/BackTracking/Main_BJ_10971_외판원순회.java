package BOJ.BackTracking;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_10971_외판원순회 {
	static int N;
	static int arr[][];
	static boolean isTraveled[];
	static int city[];
	static int min = Integer.MAX_VALUE;

	public static void travel(int count) {
		if (count == N) {
			int cost = 0;
			int now_cost = 0;
			for (int i = 0; i < N; i++) {
				if (i == N - 1)
					now_cost = arr[city[N - 1]][city[0]];
				else
					now_cost = arr[city[i]][city[i + 1]];

				if (now_cost == 0) //도시 간 이동이 불가능한 경우
					return;
				else
					cost += now_cost;
			}
			if (cost < min)
				min = cost;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isTraveled[i])
				continue;
			isTraveled[i] = true;
			city[count] = i;
			travel(count + 1);
			isTraveled[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		city = new int[N];
		isTraveled = new boolean[N];
		travel(0);
		bw.write(Integer.toString(min));
		br.close();
		bw.flush();
		bw.close();
	}

}
