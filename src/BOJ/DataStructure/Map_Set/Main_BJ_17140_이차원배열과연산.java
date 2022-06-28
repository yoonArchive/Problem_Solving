package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BJ_17140_이차원배열과연산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arr[][] = new int[100][100];

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

        int rowSize = 3, colSize = 3;
        int nextRowSize = 3, nextColSize = 3;

        int time;
        for (time = 0; time <= 100; time++) {
            if (arr[r - 1][c - 1] == k) // 종료 조건
                break;

            rowSize = nextRowSize;
            colSize = nextColSize;

            if (rowSize >= colSize) { // R 연산 수행
                nextColSize = 0; // 다음 연산을 위해 현재 연산 결과에서의 최대 열 크기를 저장하기 위한 변수
                for (int row = 0; row < rowSize; row++) {
                    for (int col = 0, length = colSize; col < length; col++) {
                        int cur = arr[row][col];
                        if (cur == 0)
                            continue;

                        if (map.get(cur) == null)
                            map.put(cur, 1); // map에 해당 숫자를 Key로 갖는 값이 없다면 -> 최초 카운트
                        else
                            map.put(cur, map.get(cur) + 1); // 있다면 현재 count값 + 1

                        arr[row][col] = 0; // 숫자 카운트 마쳤다면 0으로 바꿔주기
                    }
                    // 한 행의 모든 열 탐색을 마치고 나면 해당 행 값을 연산 결과에 맞게 갱신한다.
                    List<Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
                    entryList.sort((v1, v2) -> v1.getValue().compareTo(v2.getValue())); // Value값(숫자 count) 기준 정렬
                    int size = 0;
                    for (int i = 0, length = entryList.size(); i < length; i++) {
                        Entry<Integer, Integer> entry = entryList.get(i);
                        int curNum = entry.getKey();
                        int count = entry.getValue();
                        arr[row][size++] = curNum;
                        arr[row][size++] = count;
                    }
                    nextColSize = Math.max(nextColSize, size);
                    map.clear(); // 다음 행 연산을 위해 map을 비운다.
                }
            } else { // C 연산 수행
                nextRowSize = 0;
                for (int col = 0; col < colSize; col++) {
                    for (int row = 0; row < rowSize; row++) {
                        int cur = arr[row][col];
                        if (cur == 0)
                            continue;

                        if (map.get(cur) == null)
                            map.put(cur, 1);
                        else
                            map.put(cur, map.get(cur) + 1);

                        arr[row][col] = 0;
                    }
                    List<Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
                    entryList.sort((v1, v2) -> v1.getValue().compareTo(v2.getValue()));
                    int size = 0;
                    for (int i = 0, length = entryList.size(); i < length; i++) {
                        Entry<Integer, Integer> entry = entryList.get(i);
                        int curNum = entry.getKey();
                        int count = entry.getValue();
                        arr[size++][col] = curNum;
                        arr[size++][col] = count;
                    }
                    nextRowSize = Math.max(nextRowSize, size);
                    map.clear();
                }
            }
        }
        if (time > 100)
            bw.write("-1");
        else
            bw.write(Integer.toString(time));
        br.close();
        bw.flush();
        bw.close();

    }

}
