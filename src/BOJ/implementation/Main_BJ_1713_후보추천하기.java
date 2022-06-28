package BOJ.implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BJ_1713_후보추천하기 {
    static int idx;

    public static class postInfo implements Comparable<postInfo> {
        int order;
        int studentNum;
        int count;

        public postInfo(int order, int studentNum, int count) {
            this.order = order;
            this.studentNum = studentNum;
            this.count = count;
        }

        public int getStudentNum() {
            return studentNum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int compareTo(postInfo o) {
            if (this.count == o.count)
                return this.order - o.order;
            return this.count - o.count;
        }

    }

    public static boolean isExist(ArrayList<postInfo> list, int num) {
        for (int i = 0, size = list.size(); i < size; i++) {
            if (list.get(i).studentNum == num) {
                idx = i;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int recommend = Integer.parseInt(br.readLine());
        int recommendList[] = new int[recommend];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < recommend; i++)
            recommendList[i] = Integer.parseInt(st.nextToken());

        ArrayList<postInfo> list = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i >= recommend) break;
            if (list.size() == N) Collections.sort(list);

            int num = recommendList[i];
            if (isExist(list, num)) { // 이미 게시되어 있는 경우
                list.get(idx).setCount(list.get(idx).getCount() + 1);
            } else if (list.size() == N) { // 게시되어 있지 않음+이미 사진틀 차있음
                list.remove(0);
                list.add(new postInfo(i, num, 0));
            } else
                list.add(new postInfo(i, num, 0)); // 게시되어 있지 않음 + 사진틀 남아있음
            i++;
        }

        list.stream().sorted(Comparator.comparing(postInfo::getStudentNum)).forEach(n -> sb.append(n.studentNum + " "));
        System.out.println(sb.toString());

    }

}
