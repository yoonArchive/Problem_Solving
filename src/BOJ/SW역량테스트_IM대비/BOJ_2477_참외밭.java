package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2477_참외밭 {
	static ArrayList<Integer> dirList;
	static ArrayList<Integer> lengthList;

	public static int find(int num) {
		return dirList.indexOf(num);
	}

	public static int getSmallArea(int index) {
		int area = 0;
		if (index < 3)
			area = lengthList.get(index + 2) * lengthList.get(index + 3);
		else if (index == 3)
			area = lengthList.get(0) * lengthList.get(5);
		else
			area = lengthList.get(index - 4) * lengthList.get(index - 3);
		return area;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int K = Integer.parseInt(br.readLine());
		int dirCount[] = new int[5];
		dirList = new ArrayList<>();
		lengthList = new ArrayList<>();
		int long_width = 0;
		int long_height = 0;
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			dirList.add(dir);
			lengthList.add(length);
			dirCount[dir]++;
			if (dir == 1 || dir == 2)
				long_width = Math.max(long_width, length);
			if (dir == 3 || dir == 4)
				long_height = Math.max(long_height, length);
		}

		int smallArea = 0;
		if (dirCount[1] == 2 && dirCount[3] == 2)
			smallArea = getSmallArea(find(2));
		else if (dirCount[1] == 2 && dirCount[4] == 2)
			smallArea = getSmallArea(find(3));
		else if (dirCount[2] == 2 && dirCount[4] == 2)
			smallArea = getSmallArea(find(1));
		else
			smallArea = getSmallArea(find(4));

		int bigArea = long_width * long_height;
		bw.write(Integer.toString((bigArea - smallArea) * K));

		br.close();
		bw.flush();
		bw.close();
	}

}
