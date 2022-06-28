package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		int cut = Integer.parseInt(br.readLine());
		ArrayList<Integer> rowList = new ArrayList<>();
		ArrayList<Integer> colList = new ArrayList<>();

		rowList.add(0);
		rowList.add(height);
		colList.add(0);
		colList.add(width);

		for (int i = 0; i < cut; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (dir == 0)
				rowList.add(num);
			else
				colList.add(num);
		}

		Collections.sort(rowList);
		Collections.sort(colList);

		int rSize = rowList.size();
		int cSize = colList.size();
		int hmax = Integer.MIN_VALUE;
		int wmax = Integer.MIN_VALUE;
		
		for (int i = 1; i < cSize; i++) {
			int h = colList.get(i) - colList.get(i - 1);
			hmax = Math.max(hmax, h);
		}
		for (int j = 1; j < rSize; j++) {
			int w = rowList.get(j) - rowList.get(j - 1);
			wmax = Math.max(wmax, w);
		}

		bw.write(Integer.toString(hmax * wmax));
		br.close();
		bw.flush();
		bw.close();
	}
}