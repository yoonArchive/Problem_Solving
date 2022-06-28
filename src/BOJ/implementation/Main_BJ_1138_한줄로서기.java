package BOJ.implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_1138_한줄로서기 {
    public static class Person implements Comparable<Person> {
        int order;
        int leftPeople;

        public Person(int order, int leftPeople) {
            this.order = order;
            this.leftPeople = leftPeople;
        }

        @Override
        public int compareTo(Person o) {
            if (this.leftPeople == o.leftPeople)
                return o.order - this.order;
            return this.leftPeople - o.leftPeople;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Person> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            list.add(new Person(i + 1, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        ArrayList<Person> lineUpList = new ArrayList<>();
        lineUpList.add(list.get(0));
        for (int i = 1; i < N; i++) {
            int nowOrder = list.get(i).order;
            int nowLeft = list.get(i).leftPeople;

            if (nowOrder > lineUpList.get(i - 1).order) {
                lineUpList.add(list.get(i));
                continue;
            }
            int count = 0;
            int j;
            for (j = 0; j < i; j++) {
                if (count == nowLeft) break;
                if (nowOrder < lineUpList.get(j).order) count++;
            }
            lineUpList.add(j, list.get(i));
        }

        lineUpList.forEach(n -> sb.append(n.order + " "));
        System.out.println(sb);
        br.close();

    }

}
